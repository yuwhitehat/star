package com.liuziyu.star.common.dto;

import lombok.Data;

/**
 * 资产每日订单数
 *
 * @author LiuZiyu
 * @date 2022/11/03 16:40
 */
@Data
public class OrderCountDateDTO {

    /**
     * 日期
     */
    private String date;

    /**
     * 订单数
     */
    private String orderOrdCnt1d;
}
