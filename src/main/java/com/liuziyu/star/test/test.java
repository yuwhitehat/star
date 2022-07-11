package com.liuziyu.star.test;

import com.google.common.collect.Lists;
import com.liuziyu.star.common.ActLostCustomerParamDTO;
import com.liuziyu.star.common.CommonConstant;
import com.liuziyu.star.common.DateFormatEnum;
import com.liuziyu.star.common.UserInfo;
import com.liuziyu.star.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
       /* String[] names = {"ABC", "XYZ", "zoo"};
        String s = names[1];
        names[1] = "cat";
        System.out.println(s); // s是"XYZ"还是"cat"?*/
        /*int a = 2;
        switch (a) {
            case 0:
            case 1:
                System.out.println("dddd");
                break;
            case 2:
                System.out.println("dddwwd");
                break;
            case 3:
                System.out.println("ddddyy");
                break;
            default:
                System.out.println("ddd22d");
                break;
        }*/
        /*int[] ns = { 28, 12, 89, 73, 65, 18, 96, 50, 8, 36 };
        // 排序前:
        System.out.println(Arrays.toString(ns));
        for (int i = 0; i < ns.length - 1; i++) {
            for (int j = 0; j < ns.length - i - 1; j++) {
                if (ns[j] > ns[j+1]) {
                    // 交换ns[j]和ns[j+1]:
                    int tmp = ns[j];
                    ns[j] = ns[j+1];
                    ns[j+1] = tmp;
                }
            }
        }
        // 排序后:
        System.out.println(Arrays.toString(ns));*/

    }

    @Test
    public void test() {
        List<Integer> dayList = Arrays.asList(CommonConstant.SEVEN_DAYS, CommonConstant.FOURTEEN_DAYS, CommonConstant.TWENTY_ONE_DAYS,
                CommonConstant.TWENTY_EIGHT_DAYS, CommonConstant.TWENTY_NINE_DAYS, 30);
        System.out.println(dayList.toString());
        for (Integer day : dayList) {
            this.timeOutPushAppAndDing(day);
        }

    }

    @Test
    public void test1() {
        int min = 100;
        int max = 200;
        /*for (; ;) {
            min = min + 5;
            boolean isTrue = processData(min, max);
            if (!isTrue) {
                break;
            }
        }*/
        boolean isTrue = true;
        while (isTrue) {
            min = min + 5;
            isTrue = processData(min, max);
        }
    }

    private boolean processData(int min, int max) {

        if (min > max) {
            System.out.println("已达到最大值" + min);
            return false;
        }
        System.out.println(min);
        return true;
    }


    private void timeOutPushAppAndDing(Integer days) {
        // TKMX20210917
        String localDate = formatLocalDateTimeToString(LocalDateTime.now().minusDays(days), DateFormatEnum.YYYYMMDD.getCode());
        System.out.println(localDate);
    }

    public static String formatLocalDateTimeToString(final LocalDateTime localDateTime, final String format) {
        return Optional.ofNullable(localDateTime)
                .map(localDatetime -> localDateTime.format(DateTimeFormatter.ofPattern(format)))
                .orElse(StringUtils.EMPTY);
    }

    @Test
    public void test2() {

//        String assetItemNo = "GS622494780293148672";
//        System.out.println(Math.abs(assetItemNo.hashCode()) % 14 + 1);
        Map<String, Object> params = new HashMap<>();
        ActLostCustomerParamDTO actLostCustomerParamDTO = new ActLostCustomerParamDTO();
        actLostCustomerParamDTO.setAreaId("2,333,564");
        actLostCustomerParamDTO.setJumpFlag(1);
        params.put("areaId", actLostCustomerParamDTO.getAreaId());
        System.out.println(params);
        System.out.println(JsonUtil.toMap(JsonUtil.toString(actLostCustomerParamDTO)));

    }

    @Test
    public void test3() {

        List<String> bdCodeList = new ArrayList<>();
        bdCodeList.add("123");

        List<String> codeList = new ArrayList<>();
        codeList.add("123");
        codeList.add("345");
        codeList.add("234");
        codeList.add("234");

        bdCodeList.addAll(codeList);
        System.out.println(JsonUtil.toString(bdCodeList));
        bdCodeList = bdCodeList.stream().distinct().collect(Collectors.toList());


        System.out.println(JsonUtil.toString(bdCodeList));

    }

    @Test
    public void test4() {
        // list1中的数据有重复的
        List<String> list1 = new ArrayList<>();
        list1.add("上架1");
        list1.add("上架1");
        list1.add("上架2");
        list1.add("上架3");

        // list2中的数据不重复
        List<String> list2 = new ArrayList<>();
        list2.add("上架1");
        list2.add("上架2");
        list2.add("上架3");
        list2.add("上架4");

        // 留下list1中重复的数据
        List<String> list3 = list1.stream().filter(item -> {
            if (!list2.contains(item)) {
                return true;
            }
            list2.remove(item);
            return false;
        }).collect(Collectors.toList());
        System.out.println("list1:" + JsonUtil.toString(list1));
        System.out.println("list2:" + JsonUtil.toString(list2));
        System.out.println("list3:" + JsonUtil.toString(list3));

        List<String> list4 = list1.stream().filter(item -> !item.equals("上架1")).sorted().collect(Collectors.toList());
        System.out.println("list1:" + JsonUtil.toString(list1));
        System.out.println("去重list1:" + JsonUtil.toString(list1.stream().distinct().collect(Collectors.toList())));
        System.out.println("list4:" + JsonUtil.toString(list4));
    }

    @Test
    public void test5() {
        // 测试stream流的filter、sorted操作是否会改变原集合
        List<UserInfo> userInfos = Lists.newArrayList();
        userInfos.add(new UserInfo("lili", 23));
        userInfos.add(new UserInfo("nana", 24));
        userInfos.add(new UserInfo("neinei", 21));
        List<UserInfo> userInfos1 = userInfos.stream()
                .filter(item -> item.getAge() > 23)
                .sorted(Comparator.comparing(UserInfo::getAge))
                .collect(Collectors.toList());
        System.out.println("userInfos:" + userInfos);
        System.out.println("userInfos1:" + userInfos1);
    }

    @Test
    public void test6() {
        char ch = 'A';
        // 自动类型转换 char->int
        int num = ch;
        // 打印26个大写字母 ++i和i++是一样的，因为并不需要用到i的值
        for (int i = 0; i < 26; i++) {
            // 1、(char)num++强制类型转换 2、num++ 自增 先使用num本身的值再加1
            System.out.println(num + "\t" + ((char)(num++)));
        }
    }

    /**
     * while 语句
     */
    @Test
    public void test7() {
        int n = 10;
        int dividend = 100;
        int divisor = 89;

        int found = 0;
        // 从100开始找出n个能够被89整除的数
        while (found < n) {
            if (dividend % divisor == 0) {
                found++;
                System.out.println(dividend + "可以整除" + divisor + ".商为" + (dividend / divisor));
            }
            dividend++;
        }

        // do-while语句 至少会执行一次
        do {
            System.out.println("do-while语句 至少会执行一次");
        } while (false);
    }



}
