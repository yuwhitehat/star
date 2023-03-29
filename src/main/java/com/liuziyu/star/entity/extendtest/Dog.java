package com.liuziyu.star.entity.extendtest;

/**
 * 狗
 *
 * @author LiuZiyu
 * @date 2023/03/29 14:26
 */
public class Dog extends Animal{
    @Override
    public void makeSound() {
        System.out.println("The dog barks");
    }
}
