package com.liuziyu.star.common;

import com.liuziyu.star.common.enums.DateFormatEnum;
import com.liuziyu.star.test.DateFormatUtil;
import lombok.Getter;
import org.junit.Test;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 一些常用的字典
 *
 * @author wudi
 * @Date 2021/5/13
 */

@Component
@Getter
public class DataDictionaryMap {


    private final static HashMap<String, DateFormatEnum> DATE_FORMAT_ENUM_HASH_MAP = new HashMap<>();


    @PostConstruct
    public void initDataDictionaryMap() {
        //初始化

        Arrays.stream(DateFormatEnum.values()).forEach(dateFormatEnum -> {
            DATE_FORMAT_ENUM_HASH_MAP.put(dateFormatEnum.getCode(), dateFormatEnum);
        });

    }

    public static HashMap<String, DateFormatEnum> getDateFormatEnumMap() {
        return DATE_FORMAT_ENUM_HASH_MAP;
    }

    @Test
    public void test() {
        Arrays.stream(DateFormatEnum.values()).forEach(dateFormatEnum -> {
            DATE_FORMAT_ENUM_HASH_MAP.put(dateFormatEnum.getCode(), dateFormatEnum);
        });
        String time = "1970-01-01 00:00:00";
        LocalDateTime localDateTime = DateFormatUtil.parseStringToLocalDateTime(time, DateFormatEnum.YYYYMMDD_HHMMSS_LINE.getCode());
        System.out.println(localDateTime);

        LocalDateTime localDateTime1 = LocalDateTime.of(1970,1,1,0,0,0);
        System.out.println(localDateTime1);
    }


}
