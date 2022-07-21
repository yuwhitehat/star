package com.liuziyu.star.test;

import com.google.common.collect.Lists;
import com.liuziyu.star.common.dto.ActLostCustomerParamDTO;
import com.liuziyu.star.common.CommonConstant;
import com.liuziyu.star.common.enums.DateFormatEnum;
import com.liuziyu.star.common.dto.UserInfo;
import com.liuziyu.star.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        int rangeStart = 30;
        int rangeEnd = 60;

        if (rangeStart < 0 || rangeEnd < 0) {
            System.out.println("数据范围要为非负数！");
        }
        int mod = rangeEnd - rangeStart;
        if (mod <= 1) {
            System.out.println("非法的数字范围");
        }
        Scanner inPlayCount = new Scanner(System.in);
        Scanner inGuessNum = new Scanner(System.in);
        // 是否退出游戏
        boolean isExit = false;

        // 共计玩了多少次
        int totalCount = 0;
        // 猜对的次数
        int successCount = 0;
        while (!isExit) {
            // 生成随机数
            int bigRandom = (int) (Math.random() * (rangeEnd * 100));
            int numberToGuess = (bigRandom % mod) + rangeStart;
            if (numberToGuess <= rangeStart) {
                numberToGuess = rangeStart + 1;
            }
            if (numberToGuess >= rangeEnd) {
                numberToGuess = rangeEnd - 1;
            }

            // 判断用户是否一直猜错
            boolean isAlwaysFail = true;

            // 猜测次数
            System.out.println("游戏开始，请输入猜测次数：");
            int playCount = inPlayCount.nextInt();
            if (playCount <= 0) {
                System.out.println("猜测次数必须为正整数！请重新输入：");
                playCount = inPlayCount.nextInt();
            }

            while (playCount > 0) {
                System.out.println("如果想要退出游戏，请输入-1。");
                System.out.println("您有" + playCount + "次机会, 请输入一个" + rangeStart + "到" + rangeEnd + "之间的整数：");
                int guessNum = inGuessNum.nextInt();

                // 判断用户是否想要退出游戏
                if (guessNum < 0) {
                    System.out.println("您已退出游戏");
                    isExit = true;
                    break;
                }

                // 结果判断
                if (guessNum > numberToGuess) {
                    System.out.println("啊哦~猜测错误~");
                    System.out.println("猜测的数字比正确结果大");
                } else if (guessNum < numberToGuess) {
                    System.out.println("啊哦~猜测错误~");
                    System.out.println("猜测的数字比正确结果小");
                } else if (guessNum == numberToGuess) {
                    System.out.println("恭喜你，猜测正确！");
                    isAlwaysFail = false;
                    successCount++;
                    break;
                }
                playCount--;
            }
            // 如果用户一直猜错，告诉用户正确答案
            if (isAlwaysFail) {
                System.out.println("本轮正确结果为：" + numberToGuess);
            }
            if (!isExit) {
                totalCount++;
            }
            System.out.println("您已经玩了" + totalCount + "次，猜对了" + successCount + "次");
        }

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
            System.out.println(num + "\t" + ((char) (num++)));
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

    /**
     * switch 语句
     * 1.case语句里必须有break，否则会一直执行下去直到遇到break语句或者default
     * 2.default语句可选
     * 3.同一个switch代码块里不能声明名称相同的变量
     */
    @Test
    public void test8() {
        int n = 1;
        String str = n + "对应的中文数字是：";

        switch (n) {
            case 1:
                str += "壹";
                break;
            case 2:
                str += "贰";
            default:
                System.out.println("错误的值");

        }
    }

    /**
     * 一个猜数字的小游戏
     * 每次生成一个固定范围的随机数，选择猜测的次数
     * 注：junit单元测试不支持控制台输入，故需要写在main()方法里，最新版在上面的main()方法里
     */
    @Test
    public void playGame() {

        int rangeStart = 30;
        int rangeEnd = 60;

        if (rangeStart < 0 || rangeEnd < 0) {
            System.out.println("数据范围要为非负数！");
        }
        int mod = rangeEnd - rangeStart;
        if (mod <= 1) {
            System.out.println("非法的数字范围");
        }
        Scanner inPlayCount = new Scanner(System.in);
        Scanner inGuessNum = new Scanner(System.in);
        while (true) {
            int bigRandom = (int) (Math.random() * (rangeEnd * 100));
            int numberToGuess = (bigRandom % mod) + rangeStart;
            if (numberToGuess <= rangeStart) {
                numberToGuess = rangeStart + 1;
            }
            if (numberToGuess >= rangeEnd) {
                numberToGuess = rangeEnd - 1;
            }
            // 猜测次数
            System.out.println("请输入猜测次数：");
            int playCount = inPlayCount.nextInt();

            while (playCount > 0) {
                System.out.println("剩余次数为" + playCount + "次, 请输入一个" + rangeStart + "到" + rangeEnd + "之间的整数：");
                int guessNum = inGuessNum.nextInt();

                if (guessNum > numberToGuess) {
                    System.out.println("啊哦~猜测错误~");
                    System.out.println("猜测的数字比正确结果大");
                } else if (guessNum < numberToGuess) {
                    System.out.println("啊哦~猜测错误~");
                    System.out.println("猜测的数字比正确结果小");
                } else if (guessNum == numberToGuess) {
                    System.out.println("恭喜你，猜测正确！");
                    break;
                }

                playCount--;
            }
        }
    }

    /**
     * 数组之找出最大成绩
     */
    @Test
    public void test10() {
        int yuwen = 0;
        int shuxuan = 1;
        int yingyu = 2;
        int wuli = 3;
        int huaxue = 4;
        int shengwu = 5;

        int totalScore = 6;
        double[] scores = new double[totalScore];

        String[] scoreNames = new String[totalScore];
        scoreNames[yuwen] = "语文";
        scoreNames[shuxuan] = "数学";
        scoreNames[yingyu] = "英语";
        scoreNames[wuli] = "物理";
        scoreNames[huaxue] = "化学";
        scoreNames[shengwu] = "生物";

        // 考试成绩赋值
        for (int i = 0; i < totalScore; i++) {
            // 成绩随机，范围在80到100之间
            double score = 80 + Math.random() * 20;
            scores[i] = score;
            System.out.println(scoreNames[i] + "成绩为：" + scores[i]);
        }
        // 记录最大成绩
        double maxScore = 0;
        // 记录下标值
        int maxScoreIndex = 0;
        // 对成绩进行遍历，找出最大成绩
        for (int i = 0; i < totalScore; i++) {
            if (scores[i] > maxScore) {
                maxScore = scores[i];
                maxScoreIndex = i;
            }
        }
        System.out.println(scoreNames[maxScoreIndex] + "成绩最高，为" + maxScore);
    }

    @Test
    public void test11() {
        List<String> goods = new ArrayList<>();
        /*goods.add("货物1");
        goods.add("货物2");*/

        List<String> names = new ArrayList<>();
        names.add("巧心脆");
        /*names.add("碧根果");
        names.add("矿泉水");*/

        List<String> foods = new ArrayList<>();

        if (CollectionUtils.isEmpty(names)) {
            return;
        }
        names.forEach(item -> {
            if (!CollectionUtils.isEmpty(goods)) {
                goods.forEach(good -> {
                    foods.add(item);
                });
            } else {
                foods.add(item);
            }
        });
        System.out.println("foods=" + foods);
        String name = StringUtils.join(names.toArray(), '/');
        System.out.println("list转String结果：" + name);
    }


}
