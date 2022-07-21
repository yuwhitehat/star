package com.liuziyu.star.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangyi
 * @date 2021/11/25
 * @description: 报失对接审批流 自定义参数DTO
 */
@Data
public class ActLostCustomerParamDTO implements Serializable {

    /**
     * 报失审批流 分支  1：渠道运营审批   2：渠道部
     */
    private Integer jumpFlag;

    /**
     * 渠道经理code  提交人渠道商 审批人渠道部门 用于查找渠道部
     */
    private String channelManager;

    /**
     * 仓库所在区ID
     */
    private String areaId;
}
