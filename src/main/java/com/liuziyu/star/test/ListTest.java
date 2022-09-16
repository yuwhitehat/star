package com.liuziyu.star.test;

import com.liuziyu.star.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 有关list的坑
 *
 * @author LiuZiyu
 * @date 2022/09/13 14:48
 */
@Slf4j
public class ListTest {

    /**
     * Arrays.asList()将int数组转为list集合
     * 坑1之不能直接使用Arrays.asList()来转换基本类型数组
     * 错误示例
     * 示例结果：INFO com.liuziyu.star.test.ListTest - list:[[1,2,3]] size:1 class:class [I
     * 从结果中可以看出list中只有一个元素[1,2,3]，是一个整数数组，不是我们想要的三个整数
     * 原因：只能把int装箱为Integer，不可能把int数组装箱为Integer数组 ，
     * Arrays.asList 方法传入的是一个泛型 T 类型可变参数，最终 int 数组整体作为了一个对象成为了泛型类型 T
     */
    @Test
    public void errorExample1() {

        int[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
        log.info("list:{} size:{} class:{}", JsonUtil.toString(list), list.size(), list.get(0).getClass());
    }


    /**
     * Arrays.asList()将int数组转为list集合
     * 正确使用方式
     * 结果：INFO com.liuziyu.star.test.ListTest - list:[1,2,3] size:3 class:class java.lang.Integer
     */
    @Test
    public void correctExample1() {

        // 第一种方式：将int数组改成包装类型Integer类型
        Integer[] arr = {1, 2, 3};
        List list = Arrays.asList(arr);
        log.info("list:{} size:{} class:{}", JsonUtil.toString(list), list.size(), list.get(0).getClass());

        // 第二种方式
        int[] arr1 = {1, 2, 3};
        // box()的作用是将int类型的stream转成Integer类型的stream
        List list1 = Arrays.stream(arr1).boxed().collect(Collectors.toList());
        log.info("list1:{} size:{} class:{}", JsonUtil.toString(list1), list.size(), list.get(0).getClass());
    }

    /**
     * Arrays.asList()将String类型数组转为list集合
     * 坑2之Arrays.asList()转化后的list不支持增改操作
     * 现象1：抛出java.lang.UnsupportedOperationException 异常
     * 原因1：Arrays.asList 返回的 List 并不是我们期望的 java.util.ArrayList，而是 Arrays 的内部类 ArrayList。
     * ArrayList 内部类继承自 AbstractList 类，并没有覆写父类的 add 方法，而父类中 add 方法的实现，就是抛出 UnsupportedOperationException。
     * <p>
     * 坑3之对原始数组的修改会影响到Arrays.asList()转为的list
     * 现象2：且将arr中第二个元素改为4后，list中的第二个元素也变成4了
     * 原因2：因为ArrayList直接使用了原始数组
     */
    @Test
    public void errorExample2() {

        String[] arr = {"1", "2", "3"};
        List list = Arrays.asList(arr);
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("arr:{} list:{}", Arrays.toString(arr), list);
    }

    /**
     * 正确使用方式
     * 重新 new 一个 ArrayList 初始化 Arrays.asList 返回的 List
     * 这样和原始数组不会相互影响，add也不会抛异常
     * 日志结果：arr:[1, 4, 3] list:[1, 2, 3, 5]
     */
    @Test
    public void correctExample2() {

        String[] arr = {"1", "2", "3"};
        // 这个list是真正的ArrayList，
        List list = new ArrayList(Arrays.asList(arr));
        arr[1] = "4";
        try {
            list.add("5");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        log.info("arr:{} list:{}", Arrays.toString(arr), list);
    }

    /**
     * 调用类型是 Integer 的 ArrayList 的 remove 方法删除元素，传入一个 Integer包装类的数字和传入一个 int 基本类型的数字,结果是不一样的
     * 原因：当传入的参数是Integer类型的，调用的是boolean remove(Object o);这个方法是删除list中的一个元素
     * 当传入的参数是int类型的，调用的E remove(int index);这个方法，index是索引，所以删除的是相对应的索引位置的元素
     */
    @Test
    public void example3() {

        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        log.info("原始list:{}", JsonUtil.toString(list));
        // 当e为包装类型 时，list结果为[0,3,4]
        Integer e = 2;
        // 当e为基本类型时，list结果为[0,2,4]
        //int e = 2;
        list.remove(e);
        log.info("remove操作后的list：{}", JsonUtil.toString(list));
    }

    /**
     * 循环遍历 List，调用 remove 方法删除元素，会遇到 ConcurrentModificationException 异常
     * debug发现，开始循环遍历时，expectedModCount会初始化为modCount(modCount=这个集合修改的次数)，在循环过程中会比较expectedModCount和modCount是否相等，不相等就会抛异常
     * 在remove()方法里面会调用fastRemove(),fastRemove()会对modCount++，导致了expectedModCount和modCount不相等，就会抛出异常
     */
    @Test
    public void errorExample4() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        log.info("原始list:{}", JsonUtil.toString(list));
        try {
            list.forEach(e -> {
                list.remove(e);
            });
            /*for (Integer e : list) {
                list.remove(e);
            }*/
            /*for (int i = 0; i < list.size(); i++) {
                list.remove(i);
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("循环remove操作后的list：{}", JsonUtil.toString(list));
    }

    /**
     * 正确使用方式
     * 1.使用list内部的迭代器，用迭代器的remove()方法会手动让expectedModCount=modCount
     * 2.使用java1.8的removeIf()
     */
    @Test
    public void correctExample4() {
        List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        log.info("原始list:{}", JsonUtil.toString(list));
        try {
            Iterator<Integer> it = list.iterator();
            while (it.hasNext()) {
                // 使用it.next()是为了锁定每次要操作的元素，如果没有这一步，就会抛异常
                it.next();// 为什么要加这一步
                it.remove();

            }

            //list.removeIf(e -> e >1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("循环remove操作后的list：{}", JsonUtil.toString(list));

        // todo 动态扩容可以了解一下
        List<String> list1 = new ArrayList<>(100);
    }


}
