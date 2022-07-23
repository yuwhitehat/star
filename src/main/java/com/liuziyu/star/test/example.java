package com.liuziyu.star.test;

import java.util.Scanner;

/**
 * 理解多维数组
 *
 * @author LiuZiyu
 * @date 2022/07/23 10:33
 */
public class example {
    /*public static void main(String[] args) {
        int yuwen = 0;
        int shuxuan = 1;
        int yingyu = 2;
        int wuli = 3;
        int huaxue = 4;
        int shengwu = 5;

        String[] scoreNames = new String[6];
        scoreNames[yuwen] = "语文";
        scoreNames[shuxuan] = "数学";
        scoreNames[yingyu] = "英语";
        scoreNames[wuli] = "物理";
        scoreNames[huaxue] = "化学";
        scoreNames[shengwu] = "生物";

        Scanner in = new Scanner(System.in);
        System.out.println("请问要保存几年的成绩：");
        int yearToStore = in.nextInt();

        // 用一个二维数组表示第几年的哪门课的成绩是多少 yearToStore表示第几年；totalScore表示是哪一门课
        double[][] scores = new double[yearToStore][scoreNames.length];
        for (int i = 0; i < yearToStore; i++) {
            for (int j = 0; j < scoreNames.length; j++) {
                // 给第i年的课随机赋值一个成绩
                scores[i][j] = 80 + Math.random() * 20;
            }
        }
        System.out.println("请问您想查看第几年的成绩：");
        int year = in.nextInt() - 1;
        System.out.println("请输入要查看的课程编号：");
        int classIndex = in.nextInt() - 1;

        System.out.println("第" + (year + 1) + "年的" + scoreNames[classIndex] + "成绩是" + scores[year][classIndex]);

    }*/

    /**
     * 用多维数组实现
     * 1.求某年的最好成绩
     * 2.求某年的平均成绩
     * 3.求所有年份最好成绩
     * 4.求某门课历年最好成绩
     *
     * @param args
     */
    public static void main(String[] args) {
        int yuwen = 0;
        int shuxuan = 1;
        int yingyu = 2;
        int wuli = 3;
        int huaxue = 4;
        int shengwu = 5;

        String[] scoreNames = new String[6];
        scoreNames[yuwen] = "语文";
        scoreNames[shuxuan] = "数学";
        scoreNames[yingyu] = "英语";
        scoreNames[wuli] = "物理";
        scoreNames[huaxue] = "化学";
        scoreNames[shengwu] = "生物";

        Scanner in = new Scanner(System.in);
        System.out.println("请问要保存几年的成绩：");
        int yearCount = in.nextInt();

        // 用一个二维数组表示第几年的哪门课的成绩是多少 yearToStore表示第几年；totalScore表示是哪一门课
        double[][] scores = new double[yearCount][scoreNames.length];
        for (int i = 0; i < yearCount; i++) {
            for (int j = 0; j < scoreNames.length; j++) {
                // 给第i年的课随机赋值一个成绩
                scores[i][j] = 80 + Math.random() * 20;
                System.out.println("第" + (i + 1) + "年的" + scoreNames[j] + "成绩为" + scores[i][j]);
            }
        }

        boolean cont = true;
        while (cont) {
            System.out.println("1.求某年的最好成绩\n" + "2.求某年的平均成绩\n" + "3.求历年来的最好成绩\n" + "4.求某门课历年最好成绩");
            System.out.println("请输入您的选择：");
            int choose = in.nextInt();

            int year = 0;
            switch (choose) {
                case 1:
                    System.out.println("请输入您要求的是哪一年：");
                    year = in.nextInt() - 1;// 减1是因为year要作为索引
                    if (year < 0 || year > yearCount) {
                        System.out.println("非法的年份，请重新输入:");
                        year = in.nextInt() - 1;
                    }
                    int bestScoreIndex = 0;
                    for (int i = 0; i < scores[year].length; i++) {
                        if (scores[year][i] > scores[year][bestScoreIndex]) {
                            bestScoreIndex = i;
                        }
                    }
                    System.out.println("第" + (year + 1) + "年的最好成绩是" + scoreNames[bestScoreIndex] + ":" + scores[year][bestScoreIndex]);
                    break;
                case 2:
                    System.out.println("请输入您要求的是哪一年：");
                    year = in.nextInt() - 1;// 减1是因为year要作为索引
                    if (year < 0 || year > yearCount) {
                        System.out.println("非法的年份，请重新输入:");
                        year = in.nextInt() - 1;
                    }
                    int totalScore = 0;
                    for (int i = 0; i < scores[year].length; i++) {
                        totalScore += scores[year][i];
                    }
                    System.out.println("第" + (year + 1) + "年的平均成绩是" + (totalScore / scoreNames.length));
                    break;
                case 3:
                    int bestYearIndex = 0;
                    int bestYearScoreNameIndex = 0;
                    for (int i = 0; i < yearCount; i++) {
                        for (int j = 0; j < scoreNames.length; j++) {
                            if (scores[i][j] > scores[bestYearIndex][bestYearScoreNameIndex]) {
                                bestYearIndex = i;
                                bestYearScoreNameIndex = j;
                            }
                        }
                    }
                    System.out.println("历年来中，第" + (bestYearIndex + 1) + "年的最好成绩是" + scoreNames[bestYearScoreNameIndex] + ":" + scores[bestYearIndex][bestYearScoreNameIndex]);
                    break;
                case 4:
                    System.out.println("请输入要查询的课程号：");
                    int classIndex = in.nextInt() - 1;
                    year = 0;
                    for (int i = 0; i < yearCount; i++) {
                        if (scores[i][classIndex] > scores[year][classIndex]) {
                            year = i;
                        }
                    }
                    System.out.println(scoreNames[classIndex] + "历年最好成绩为第" + (year + 1) + "年，成绩为：" + scores[year][classIndex]);
                    break;
                default:
                    System.out.println("退出程序");
                    cont = false;
            }
        }

    }

}
