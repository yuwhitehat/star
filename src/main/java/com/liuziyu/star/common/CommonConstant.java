package com.liuziyu.star.common;

/**
 * 公用常量
 *
 * @author 宋昊
 * @date 2021/6/16
 */
public class CommonConstant {

    /**
     * 系统三字码
     */
    public static final String ARB = "ARB";


    /**
     * 系统操作人
     */
    public static final String SYSTEM_USER = "system";

    /**
     * 24小时
     */
    public static final Integer ONE_DAY = 24;


    /**
     * 默认分页每页大小
     */
    public final static Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 分页每页大小最大值
     */
    public final static Integer MAX_PAGE_SIZE = 500;


    /**
     * excel导入的最大行数
     */
    public final static Integer EXECUTE_RETURN_MAX_SIZE = 50;


    /**
     * excel导入的最大行数
     */
    public final static Long EXCEL_MAX_ROWS = 2000L;

    /**
     * 导入退仓单excel的最大size为2M
     */
    public final static Integer EXCEL_IMPORT_MAX_SIZE = 2;

    /**
     * 导入退仓单excel的最大行
     */
    public final static Integer EXCEL_IMPORT_MAX_ROWS = 2000;

    /**
     * 导入退仓单excel的最大列
     */
    public final static Integer EXCEL_IMPORT_MAX_COLUMNS = 2;


    /**
     * 批量搜素阀值
     */
    public final static Integer BATCH_QUERY_COUNT = 200;


    /**
     * 批量调用ALS搜素阀值
     */
    public final static Integer MAX_QUERY_ALS_SIZE = 20;


    /**
     * 该MAC对应的资产不存在，请检查是否录入错误
     */
    public static final String MAC_ASSET_NOT_EXIST = "该MAC对应的资产不存在，请检查是否录入错误";


    /**
     * 非柜机设备，请检查后重试
     */
    public static final String MAC_NOT_CABINET = "非柜机设备，请检查后重试";


    /**
     * 非柜机设备，请检查后重试
     */
    public static final String ORDER_DETAIL_MAC_NOT_END = "该MAC已存在未完结的退仓申请单";


    /**
     * 状态不符合退仓要求提示信息
     */
    public static final String BREAKDOWN_STATUS_ILLEGAL = "只能对状态是正常或报修的设备进行退库申请";

    /**
     * 状态不符合退仓要求提示信息
     */
    public static final String NORMAL_STATUS_ILLEGAL = "只能对状态是正常的设备进行退库申请";

    /**
     * 位置不符合退仓要求提示信息
     */
    public static final String HOLDER_ILLEGAL = "只能对位置在本人的设备进行退库申请";

    /**
     * 位置不符合退仓要求提示信息
     */
    public static final String BUSINESS_TYPE_ILLEGAL = "该业务属性的资产不支持退回到所选仓库";

    /**
     * 柜机中含有在桩宝信息，请走故障退
     */
    public static final String HAS_BAT_IN_CAB = "有在桩宝，请通电上线后将宝弹出，无法上线或依旧存在在桩宝，请走故障退库";


    /**
     * 柜机中含有在桩宝信息，请走故障退
     */
    public static final String CABINET_BREAK_DOWN = "资产存在系统识别故障，请走故障设备退库";

    /**
     * 7天
     */
    public static final Integer SEVEN_DAYS = 7;

    /**
     * 14天
     */
    public static final Integer FOURTEEN_DAYS = 14;

    /**
     * 21天
     */
    public static final Integer TWENTY_ONE_DAYS = 21;

    /**
     * 28天
     */
    public static final Integer TWENTY_EIGHT_DAYS = 28;

    /**
     * 29天
     */
    public static final Integer TWENTY_NINE_DAYS = 29;

    public final static String SCRAP_HOLDER_ERROR = "资产当前位置为%s/%s/%s，请核实资产位置情况。";


}
