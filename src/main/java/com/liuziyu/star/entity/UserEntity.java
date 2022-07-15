package com.liuziyu.star.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/07/14 10:57
 */
@Data
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    public UserEntity() {
    }

    public UserEntity(String name) {
        this.name = name;
    }
}
