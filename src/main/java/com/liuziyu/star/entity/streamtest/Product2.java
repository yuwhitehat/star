package com.liuziyu.star.entity.streamtest;

import com.liuziyu.star.entity.BaseEntity;
import lombok.*;

/**
 * 产品
 *
 * @author LiuZiyu
 * @date 2023/03/18 18:21
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product2 extends BaseEntity {
    private String name;
    private Double price;
    private Product product;

    public Product2(Long id, String name, Double price, Product product) {
        super(id);
        this.name = name;
        this.price = price;
        this.product = product;
    }
}
