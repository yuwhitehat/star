package com.liuziyu.star.common.enums;

import lombok.Getter;

/**
 * 时间格式化枚举
 *
 * @author DavidYun (贠书谦)
 * @date 2018/7/2
 */
@Getter
public enum DateFormatEnum {

    YYYY("YYYY"),
    MM("MM"),
    DD("DD"),
    YYYYMM("yyyyMM"),
    YYYYMMDD_LINE("yyyy-MM-dd"),
    YYYYMMDD_HHMMSS_LINE("yyyy-MM-dd HH:mm:ss"),
    YYYYMMDD("yyyyMMdd"),
    YYYY_YEAR_MONTH_M_DAY("yyyy年MM月dd日 HH:mm:ss"),
    YYYY_YEAR_MONTH("yyyy年MM月dd日"),
    YYYYMMDD_POINT("yyyy.MM.dd"),
    YYYYMMDD_SALSH("yyyy/MM/dd"),
    YYYYMMDD_HHMMSS_NO("yyyyMMdd HH:mm:ss"),
    YYYYMMDD_HHMMSS_SALSH("yyyy/MM/dd HH:mm:ss"),
    YYYYMMDDHHMMSS_LINE("yyyy-MM-ddHH:mm:ss"),
    YYYYMMDDHHMMSS("yyyyMMddHHmmss"),
    YYYY_MM("YYYY-MM")
    ;


    private String code;

    DateFormatEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
