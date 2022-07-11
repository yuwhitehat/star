package com.liuziyu.star.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.List;
import java.util.Map;

import static java.time.temporal.ChronoField.*;

/**
 * JSON工具类
 *
 * @Author Wang Lin(王霖)
 * @Date 2017/8/1
 * @Time 17:07
 */
public class JsonUtil {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final DateTimeFormatter MY_DATE_TIME;
    private static final DateTimeFormatter MY_DATE;

    static {
        MY_DATE_TIME = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .appendLiteral(' ')
                .appendValue(HOUR_OF_DAY, 2)
                .appendLiteral(':')
                .appendValue(MINUTE_OF_HOUR, 2)
                .optionalStart()
                .appendLiteral(':')
                .appendValue(SECOND_OF_MINUTE, 2)
                .toFormatter();
    }

    static {
        MY_DATE = new DateTimeFormatterBuilder()
                .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
                .appendLiteral('-')
                .appendValue(MONTH_OF_YEAR, 2)
                .appendLiteral('-')
                .appendValue(DAY_OF_MONTH, 2)
                .toFormatter();
    }

    static {
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        //LocalDateTime类型转换
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(MY_DATE_TIME));
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(MY_DATE_TIME));

        //LocalDate类型转换
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(MY_DATE));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(MY_DATE));

        OBJECT_MAPPER.registerModule(javaTimeModule);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 推荐使用LocalDateTime和LocalDate时间日期类型，而不是Date类型
        // Date类型默认转换成 yyyy-MM-dd HH:mm:ss 格式，需要其他格式的如yyyy-MM-dd 手动添加注解
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    }

    private static String writeValue(Object object) {
        if (object == null) {
            return null;
        } else if (object instanceof String) {
            return (String) object;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T readValue(String json, Class<T> t) {
        if (json == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T readValue(String json, TypeReference<T> t) {
        if (json == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * java对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return writeValue(object);
    }


    /**
     * json转换为java对象
     *
     * @param json json
     * @param t    对象类型
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, Class<T> t) {
        return readValue(json, t);
    }


    /**
     * json转换为List对象
     *
     * @param json json
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json) {
        return readValue(json, new TypeReference<List<T>>() {
        });
    }


    /**
     * json转换为Map对象
     *
     * @param json json
     * @return
     */
    public static <K, V> Map<K, V> toMap(String json) {
        return readValue(json, new TypeReference<Map<K, V>>() {
        });
    }


    /**
     * json转换为java对象
     *
     * @param json json
     * @param t    对象类型
     * @param <T>
     * @return
     */
    public static <T> T toBean(String json, TypeReference<T> t) {
        return readValue(json, t);
    }

}
