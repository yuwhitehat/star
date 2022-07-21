package com.liuziyu.star.test;

import com.google.common.collect.Lists;
import com.liuziyu.star.common.dto.Parent;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 关于java8新特性 stream流
 *
 * @author LiuZiyu
 * @date 2022/07/21 10:01
 */
public class StreamTest {
    public static void main(String[] args) {

    }

    @Test
    public void test() {
        List<Parent<String, String>> datas = Lists.newArrayList();
        datas.add(new Parent<>("Alice", "89"));
        datas.add(new Parent<>("Alice", "90"));

        datas.add(new Parent<>("Bob", "90"));
        datas.add(new Parent<>("Bob", "78"));

        // 使用groupingBy对key进行分组
        Map<String, List<Parent<String, String>>> resultMap = datas.stream()
                .collect(Collectors.groupingBy(Parent::getKey, Collectors.toList()));
        resultMap.forEach((key, item) -> {
            System.out.println("key:" + key + " Object:" + item);
        });

    }


}
