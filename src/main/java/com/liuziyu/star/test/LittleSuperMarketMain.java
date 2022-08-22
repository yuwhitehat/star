package com.liuziyu.star.test;

import com.liuziyu.star.common.dto.person.Customer;
import com.liuziyu.star.common.dto.supermarket.LittleSuperMarket;
import com.liuziyu.star.common.dto.supermarket.Merchandise;

import java.util.Scanner;

/**
 * 小超市的运行
 *
 * 调试：1.执行任意代码之Evaluate Expression:在对话框输入代码，直接执行看结果值
 * 2.条件断点：给断点设置条件，只有满足条件时，程序才会在该断点停住
 * 3.step into 进入到方法内部 step out回到方法调用的地方
 *
 * @author LiuZiyu
 * @date 2022/07/30 9:39
 */
public class LittleSuperMarketMain {
    public static void main(String[] args) {
        // 初始化小超市
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.address = "康庄大道999号";
        littleSuperMarket.superMarketName = "任我行";
        littleSuperMarket.parkingCount = 200;
        littleSuperMarket.merchandises = new Merchandise[200];
        littleSuperMarket.merchandiseSold = new int[littleSuperMarket.merchandises.length];

        // 初始化商品
        // 赋值 方便操作
        Merchandise[] all = littleSuperMarket.merchandises;
        for (int i = 0; i < all.length; i++) {
            Merchandise m = new Merchandise();
            m.count = 200;
            m.id = "ID" + i;
            m.name = "商品" + i;
            m.purchasePrice = Math.random() * 200;
            m.soldPrice = (1 + Math.random()) * 200;
            all[i] = m;
        }

        System.out.println("超市开门啦！");

        boolean open = true;
        Scanner in = new Scanner(System.in);
        while (open) {
            System.out.println("本店叫做" + littleSuperMarket.superMarketName);
            System.out.println("本店地址" + littleSuperMarket.address);
            System.out.println("本店共有停车位" + littleSuperMarket.parkingCount + "个");
            System.out.println("今天的营业额为" + littleSuperMarket.incomingSum + "$");
            System.out.println("共有商品" + littleSuperMarket.merchandises.length + "种");

            // 顾客
            Customer customer = new Customer();
            customer.name = "顾客编号" + ((int) (Math.random() * 10000));
            customer.money = (1 + Math.random()) * 10000;
            customer.isDrivingCar = Math.random() > 0.5;

            System.out.println(customer.name + "进店，带了" + customer.money + "$");
            // 判断车位够不够
            if (customer.isDrivingCar) {
                if (littleSuperMarket.parkingCount > 0) {
                    System.out.println("欢迎" + customer.name + "，车位编号为" + littleSuperMarket.parkingCount);
                    littleSuperMarket.parkingCount--;
                } else {
                    System.out.println("不好意思，本店车位已满，欢迎下次再来");
                    continue;
                }
            } else {
                System.out.println("欢迎光临~");
            }

            // 用于计算顾客购买的总价
            double totalCost = 0;
            while (true) {
                System.out.println("本店提供" + all.length + "种商品，欢迎选购。若要退出选购可输入-1，请输入商品编号：");
                int index = in.nextInt();
                if (index < 0) {
                    break;
                }
                if (index >= all.length) {
                    System.out.println("本店没有这个商品编号，请输入编号为0到" + (all.length - 1) + "之内的商品编号。");
                }

                Merchandise m = all[index];
                System.out.println("你选购的商品名称为" + m.name + "单价为" + m.soldPrice + "，请问要购买的数量：");
                int buyNum = in.nextInt();

                if (buyNum <= 0) {
                    System.out.println("不买看看也好，欢迎继续选购~");
                    continue;
                }
                if (buyNum > m.count) {
                    System.out.println("此商品已没有库存，欢迎继续选购其他商品~");
                    continue;
                }
                if (buyNum * m.soldPrice + totalCost > customer.money) {
                    System.out.println("您的钱不足以支付，看看其他商品吧~");
                    continue;
                }
                totalCost += buyNum * m.soldPrice;

                // 减库存
                m.count -= buyNum;
                littleSuperMarket.merchandiseSold[index] += buyNum;
            }
            customer.money -= totalCost;

            if (customer.isDrivingCar) {
                littleSuperMarket.parkingCount++;
            }
            System.out.println(customer.name + "共消费了" + totalCost + "$");
            littleSuperMarket.incomingSum += totalCost;
            System.out.println("是否继续营业?");
            open = in.nextBoolean();
        }
        System.out.println("超市今天停止营业啦");
        System.out.println("今天的总营业额为" + littleSuperMarket.incomingSum + "营业情况如下：");

        for (int i = 0; i < littleSuperMarket.merchandiseSold.length; i++) {
            Merchandise m = all[i];
            int numSold = littleSuperMarket.merchandiseSold[i];
            // 如果这个商品卖出的数量大于0
            if (numSold > 0) {
                double incoming = m.soldPrice * numSold;
                double netIncoming = (m.soldPrice - m.purchasePrice) * numSold;
                System.out.println(m.name + "售出了" + numSold + "个，销售额为" + incoming + "。净利润为" + netIncoming + "$");
            }
        }
    }
}
