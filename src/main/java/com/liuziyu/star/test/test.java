package com.liuziyu.star.test;

import com.google.common.collect.Lists;
import com.liuziyu.star.common.CommonConstant;
import com.liuziyu.star.common.dto.ActLostCustomerParamDTO;
import com.liuziyu.star.common.dto.UserInfo;
import com.liuziyu.star.common.enums.DateFormatEnum;
import com.liuziyu.star.entity.TestEntity;
import com.liuziyu.star.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
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
        names.add("碧根果");
        names.add("矿泉水");

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
        List<String> names2 = new ArrayList<>();
        String name = StringUtils.join(names2.toArray(), "/");
        System.out.println("list转String结果：" + name);

        String stringTest = "成品";
        String stringTest2 = "充电博；小柜机";
        List<String> stringList = Arrays.asList(stringTest.split("；"));
        List<String> stringList2 = Arrays.asList(stringTest2.split("；"));
        System.out.println("String转list结果1：" + JsonUtil.toString(stringList));
        System.out.println("String转list结果2：" + JsonUtil.toString(stringList2));
    }

    @Test
    public void test12() {
        List<String> orderNoList = new ArrayList<>();
        orderNoList.add("NO123");
        orderNoList.add("NO123");
        orderNoList.add("NO124");
        orderNoList.add("NO135");

        orderNoList.stream().distinct().forEach(orderNo -> {
            System.out.println(orderNo);
        });
        System.out.println("---------");
        orderNoList.forEach(orderNo -> {
            System.out.println(orderNo);
        });
    }

    @Test
    public void test13() {

        String time = "1970-01-01 00:00:00";
        LocalDateTime localDateTime = DateFormatUtil.parseStringToLocalDateTime(time, DateFormatEnum.YYYYMMDD_HHMMSS_LINE.getCode());
        System.out.println(localDateTime);
    }

    /**
     * 关于在for循环内部捕获异常并抛出是否会导致for循环停止的测试
     * 结果：
     * 1.捕获了异常不抛出 for循环会继续执行
     * 2.捕获了异常再抛出 for循环会直接结束
     */
    @Test
    public void test14() {
        print(6);
    }

    public void print(int limit) {
        try {
            for (int i = 0; i < 10; i++) {
                try {
                    if (i > limit) {
                        throw new Exception();
                    }
                    System.out.println("打印" + i);
                } catch (Exception e) {
                    System.out.println("打印失败" + i);
                    throw e;
                }
            }
            System.out.println("循环结束");
        } catch (Exception e) {
            System.out.println("再次捕获");
        }
    }

    @Test
    public void test15() {
        String code = "[" + 1002 + "," + 1003 + "]";
        List<String> codeList = JsonUtil.toBean(code, new com.fasterxml.jackson.core.type.TypeReference<List<String>>() {
        });
        System.out.println(codeList);
    }

    @Test
    public void test16() {
        List<UserInfo> userInfoList = new ArrayList<>();
        /*userInfoList.add(new UserInfo("lil", 23));
        userInfoList.add(new UserInfo("wewe", 21));*/
        Map<String, UserInfo> map = userInfoList.stream().collect(Collectors.toMap(UserInfo::getName, Function.identity(), (a1, a2) -> a1));
        List<String> stringList = new ArrayList<>();
        stringList.add("lili");
        stringList.forEach(s -> {
            if (map.get(s) == null) {
                UserInfo userInfo = new UserInfo(s, 23);
                map.put(s, userInfo);
            }
        });
        System.out.println(JsonUtil.toString(map));
    }

    @Test
    public void test17() {
        /*String time = LocalDateTime.of(2022, 11, 1, 10, 0,0).format(DateTimeFormatter.ofPattern(DateFormatEnum.YYYYMMDD_LINE.getCode()));
        LocalDateTime time1 = LocalDate.parse(time, DateTimeFormatter.ofPattern(DateFormatEnum.YYYYMMDD_LINE.getCode())).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();
        Long days = Duration.between(time1, now).toDays();
        System.out.println(days);*/

   /*     LocalDateTime l1 = LocalDateTime.of(2022,11, 2, 10, 0, 0);
        LocalDate localDate1 = l1.toLocalDate();
        LocalDate localDate2 = LocalDateTime.now().toLocalDate();
        Long days = ChronoUnit.DAYS.between(localDate1, localDate2);
        System.out.println("localDate1:"+ localDate1 +  days);*/

        LocalDate nowDate = LocalDate.now();
        LocalDate date1 = LocalDateTime.parse("2022-11-02 00:00:00", DateTimeFormatter.ofPattern(DateFormatEnum.YYYYMMDD_HHMMSS_LINE.getCode())).toLocalDate();
        System.out.println(nowDate + "---" + date1);
        if (nowDate.equals(date1)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
        LocalDate lastBizFullDate = LocalDate.parse("2022-11-01");
        System.out.println(lastBizFullDate);
        if (lastBizFullDate.equals(nowDate.minusDays(1L))) {
            System.out.println("业务满仓");
        } else {
            System.out.println("非业务满仓");
        }
    }

    @Test
    public void test18() {
        /*String orderDateJson = "[{\"date\":\"2022-10-30\",\"orderOrdCnt1d\":\"1\"},{\"date\":\"2022-10-15\",\"orderOrdCnt1d\":\"1\"},{\"date\":\"2022-10-28\",\"orderOrdCnt1d\":\"2\"},{\"date\":\"2022-10-29\",\"orderOrdCnt1d\":\"1\"},{\"date\":\"2022-10-24\",\"orderOrdCnt1d\":\"2\"},{\"date\":\"2022-10-31\",\"orderOrdCnt1d\":\"0\"},{\"date\":\"2022-10-21\",\"orderOrdCnt1d\":\"1\"},{\"date\":\"2022-10-27\",\"orderOrdCnt1d\":\"2\"},{\"date\":\"2022-11-01\",\"orderOrdCnt1d\":\"1\"},{\"date\":\"2022-10-25\",\"orderOrdCnt1d\":\"1\"}]";
        List<OrderCountDateDTO> returnOrderCountDateList5Week = JsonUtil.toBean(JsonUtil.toString(orderDateJson), new com.fasterxml.jackson.core.type.TypeReference<List<OrderCountDateDTO>>() {
        });
        System.out.println(returnOrderCountDateList5Week);
        List<OrderCountDateDTO> sorted = returnOrderCountDateList5Week.stream().sorted(Comparator.comparing(s -> LocalDate.parse(s.getDate()))).collect(Collectors.toList());
        System.out.println("排序后" + sorted);

        LocalDateTime init = LocalDateTime.of(1970, 1,1,0, 0, 0);
        if (!init.equals(LocalDateTime.now())) {
            System.out.println("不是默认时间");
        }*/

        String test = "";
        if (StringUtils.isNotBlank(test)) {
            System.out.println("你对了！");
        } else {
            System.out.println("你还是对了哈哈哈");
        }
    }

    @Test
    public void test19() {

        String str = "2022-11-02 00:00:00";
        String result = str.substring(0, str.indexOf('-', 5));
        System.out.println(result);
    }

    @Test
    public void test20() {
        String str = "0.4450";
        String result = new BigDecimal(str).multiply(new BigDecimal("100")).setScale(2, RoundingMode.HALF_UP).toPlainString() + "%";
        String result1 = new BigDecimal(str).setScale(2, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).toPlainString() + "%";
        System.out.println(result);
        System.out.println(result1);
    }

    /**
     * java 前缀匹配
     */
    @Test
    public void test21() {
        String warehouseWholeId = "10008-12333-111111-3333333-22222" + "-";
        List<String> list = Lists.newArrayList();
        list.add("10008-12333");
        list.add("10008-12333-111222");
        list.add("10008-12333-11111");
        list.add("10008-12333-111111-3333333-22222");
        list.add("10008-12333-111111-3333333-22222-11001");
        list.forEach(peopleWholeId -> {
            if (warehouseWholeId.startsWith(peopleWholeId + "-")) {
                System.out.println(peopleWholeId);
            }
        });
    }

    /**
     * java 截取字符串
     */
    @Test
    public void test22() {
        String warehouseWholeId = "10008-12333-111111-3333333-22222";
        String subStr = warehouseWholeId.substring(warehouseWholeId.lastIndexOf('-') + 1);
        System.out.println(subStr);
    }

    @Test
    public void test23() {
        Set<String> sets = new HashSet<>();
        sets.add("222");
        sets.add("3455");
        sets.add("3456");


        sets.remove("222");
        System.out.println(sets);
    }

    /**
     * 为啥list1指向了list后，修改list1，list也会变啊？？？？ 是啥原理
     * chatGPT给的答案：
     * 这段代码中，发生了引用赋值的操作。当你执行 List list1 = list; 这行代码时，
     * 实际上是将 list 的引用赋值给了 list1。这意味着 list 和 list1 引用的是同一个对象，在内存中指向同一个位置。
     * 因此，当你对 list1 执行 list1.add(new Object()); 这行代码时，实际上是对同一个对象进行修改。
     * 由于 list 和 list1 引用的是同一个对象，所以无论你通过哪个引用进行修改，都会影响到这个对象本身。
     * 因此，你会发现 list 和 list1 打印出来的结果都包含了新添加的元素。
     *
     * 如果你想要创建一个新的对象而不是共享同一个对象，可以使用 List list1 = new ArrayList<>(list); 来创建 list1。
     * 这样就会复制 list 中的元素到一个新的 ArrayList 对象中，而不是共享同一个对象。这样修改 list1 就不会影响到 list。
     */
    @Test
    public void test24() {
        List<String> list = new ArrayList<>();
        list.add("222");
        list.add("3455");
        list.add("3456");
        System.out.println(list);
        List list1 = list;
        // 为啥list1指向了list后，修改list1，list也会变啊？？？？ 是啥原理
        list1.add(new Object());
        System.out.println(list);
        System.out.println(list1);
    }

    @Test
    public void test25() {
        // 创建一个包含一些元素的列表
        List<String> dataList = new ArrayList<>();
        dataList.add("元素1");
        dataList.add("元素2");
        dataList.add("元素3");
        dataList.add("元素4");
        dataList.add("元素5");
        dataList.add("元素6");
        dataList.add("元素7");
        dataList.add("元素8");
        dataList.add("元素9");
        dataList.add("元素10");

        // 指定每个批次的大小
        int batchSize = 3;

        // 获取迭代器
        Iterator<String> iterator = dataList.iterator();

        // 处理分批数据
        while (iterator.hasNext()) {
            List<String> batch = new ArrayList<>();

            // 从迭代器中获取指定数量的元素
            for (int i = 0; i < batchSize && iterator.hasNext(); i++) {
                batch.add(iterator.next());
            }

            // 处理当前批次的数据
            processBatch(batch);
        }
    }

    private void processBatch(List<String> batch) {
        System.out.println("开始处理----");
    }

    @Test
    public void test26() {
        List<String> dateList = Lists.newArrayList();
        dateList.add("2021-12");
        dateList.add("2022-03");
        dateList.add("2021-10");
        dateList.add("2022-07");
        dateList.add("2019-11");
        dateList.add("2022-05");
        dateList.add("2022-04");
        dateList.add("2022-04");
        dateList.add("2019-11");
        dateList.add("2019-11");
        dateList.add("2022-05");
        dateList.add("2023-05");
        dateList.add("2023-05");

        Map<String, Long> yearCountMap = dateList.stream()
                .map(date -> date.split("-")[0])  // 获取"-"前面的字符串作为分组依据
                .collect(Collectors.groupingBy(prefix -> prefix, Collectors.counting()));

        Map<String, List<String>> groupedMap = dateList.stream()
                .collect(Collectors.groupingBy(date -> date.split("-")[0]));  // 根据前缀分组

        Map<String, Map<String, Long>> monthCountMap = dateList.stream()
                .collect(Collectors.groupingBy(date -> date.split("-")[0],  // 外层分组：前缀
                        Collectors.groupingBy(date -> date.split("-")[1],  // 内层分组：后缀
                                Collectors.counting())));  // 统计数量
        List<Long> countList = monthCountMap.values().stream().flatMap(innerMap -> innerMap.values().stream()).collect(Collectors.toList());

        int n = BigDecimal.valueOf(countList.size()).multiply(BigDecimal.valueOf(0.2)).setScale(0, RoundingMode.HALF_UP).intValue();
        List<Long> countOfFirstNList = getCountOfFirstNList(n, countList);

        //System.out.println(yearCountMap);
        //System.out.println(groupedMap);
        System.out.println(JsonUtil.toString(monthCountMap));
        System.out.println(JsonUtil.toString(countOfFirstNList));
    }

    /**
     * 组装前N个数值字段名称
     * @param n =所有有值的月份格子总数*20%后取整数（四舍五入）
     * @param countList 所有数值
     */
    private List<Long> getCountOfFirstNList(int n, List<Long> countList) {
        System.out.println("n的值为" + n);
        if(n == 0) {
            return Lists.newArrayList();
        }
        // 按照从大到小排序
        List<Long> sortedList = countList.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println("倒序排序后的数值集合:" + JsonUtil.toString(sortedList));
        /*if (n == 1) {
            List<Long> tempList = Lists.newArrayList();
            tempList.add(sortedList.get(0));
            int index = 1;
            while (sortedList.size() > index && sortedList.get(index).equals(sortedList.get(index - 1))) {
                tempList.add(sortedList.get(index));
                index++;
            }
            if (tempList.size() > 2) {
                return Lists.newArrayList();
            } else {
                return tempList;
            }
        }*/
        List<Long> resultList = sortedList.stream().limit(n).collect(Collectors.toList());
        System.out.println("倒序排序后取前N个数值集合:" + JsonUtil.toString(resultList));
        // 如果第N个值和第N+1个值相等，则N+1也要；如果和第N个值相等的值大于1个，则第N个数值也不要
        Long countOfN = resultList.get(n - 1);
        int equalNCount = (int) sortedList.stream().filter(s -> s.equals(countOfN)).count();
        if (equalNCount > 2) {
            return resultList.stream().filter(r -> !r.equals(countOfN)).collect(Collectors.toList());
        }
        return resultList;
    }

    @Test
    public void test27() {
        List<UserInfo> userInfoList = new ArrayList<>();
        userInfoList.add(new UserInfo("ww", 22));
        userInfoList.add(new UserInfo("er", 44));
        TestEntity test = new TestEntity();
        test.setCount(2);
        TestEntity.Param param = new TestEntity.Param();
        param.setParamList(userInfoList);
        System.out.println(JsonUtil.toString(param));
    }

}
