package com.liuziyu.star.entity.streamtest;

import lombok.*;

/**
 * 产品
 *
 * @author LiuZiyu
 * @date 2023/03/18 18:21
 */
@AllArgsConstructor
@NoArgsConstructor
public class Product3 {
    private Integer id;
    private String name;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
