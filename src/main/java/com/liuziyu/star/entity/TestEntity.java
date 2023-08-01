package com.liuziyu.star.entity;

import lombok.Data;

import java.util.List;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2023/07/20 16:47
 */
@Data
public class TestEntity<E> {

    private List<E> qq;

    private Integer count;

    @Data
    public static class Param<T> {
        private List<T> paramList;
    }
}
