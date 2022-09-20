package com.liuziyu.star.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/09/16 16:35
 */
@Data
@AllArgsConstructor
public class AuthInfo {

    /**
     * 审批工号
     */
    private String uId;

    /**
     * 审批单号
     */
    private String orderId;

    /**
     * 审批状态
     */
    private String status;

    /**
     * 审批人
     */
    private String name;
}
