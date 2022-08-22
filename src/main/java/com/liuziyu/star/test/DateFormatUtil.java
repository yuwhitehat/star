package com.liuziyu.star.test;

import com.liuziyu.star.common.DataDictionaryMap;
import com.liuziyu.star.common.enums.DateFormatEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wudi
 * @Date 2021/5/6
 */
@Component
@Slf4j
public class DateFormatUtil {


    /**
     * string -> LocalDateTime
     *
     * @param value
     * @param dataFormat
     * @return
     */
    public static LocalDateTime parseStringToLocalDateTime(String value, String dataFormat) {
        if (StringUtils.isEmpty(value) || StringUtils.isEmpty(dataFormat)) {
            return null;
        }
        DateFormatEnum byValue = DataDictionaryMap.getDateFormatEnumMap().get(dataFormat);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        switch (byValue) {
            case YYYYMMDD_HHMMSS_LINE:
            case YYYY_YEAR_MONTH_M_DAY:
            case YYYYMMDD_HHMMSS_NO:
            case YYYYMMDD_HHMMSS_SALSH:
            case YYYYMMDDHHMMSS_LINE:
            case YYYYMMDDHHMMSS:
                return LocalDateTime.parse(value, timeFormatter);
            case YYYY:
            case MM:
            case DD:
            case YYYYMM:
            case YYYYMMDD_LINE:
            case YYYY_YEAR_MONTH:
            case YYYYMMDD:
            case YYYYMMDD_SALSH:
            case YYYYMMDD_POINT:
                log.warn("parseStringToLocalDateTime, [dataFormat is LocalDate] value {},format {} ", value, dataFormat);
                return LocalDate.parse(value, timeFormatter).atStartOfDay();
            default:
                log.error("parseStringToLocalDateTime,[DateFormatEnum miss] value {},format {} ", value, dataFormat);
                return LocalDateTime.parse(value, timeFormatter);
        }
    }

    /**
     * string -> localDate
     *
     * @param value
     * @param dataFormat
     * @return
     */
    public static LocalDate parseStringToLocalDate(String value, String dataFormat) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(dataFormat);
        return LocalDate.parse(value, timeFormatter);
    }

    /**
     * string ->Date
     *
     * @param value
     * @param dataFormat
     * @return
     */
    public static Date parseStringTDate(String value, String dataFormat) {
        LocalDate localDate = parseStringToLocalDate(value, dataFormat);
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
    }

}
