package com.liuziyu.star.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/07/21 10:13
 */
@Data
@AllArgsConstructor
public class Parent<A, B> {

    private A key;

    private B value;
}
