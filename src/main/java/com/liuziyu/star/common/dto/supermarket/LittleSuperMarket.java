package com.liuziyu.star.common.dto.supermarket;

/**
 * 小超市
 *
 * @author LiuZiyu
 * @date 2022/07/30 9:31
 */
public class LittleSuperMarket {

    /**
     * 超市名称
     */
    public String superMarketName;

    /**
     * 地址
     */
    public String address;

    /**
     * 停车位的数量
     */
    public int parkingCount;

    /**
     * 收入总和
     */
    public double incomingSum;

    /**
     * 商品列表
     */
    public Merchandise[] merchandises;

    /**
     * 某个商品买出的个数
     */
    public int[] merchandiseSold;
}
