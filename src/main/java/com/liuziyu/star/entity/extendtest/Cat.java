package com.liuziyu.star.entity.extendtest;

/**
 * 猫
 *
 * @author LiuZiyu
 * @date 2023/03/29 14:27
 */
public class Cat extends Animal{
    @Override
    public void makeSound() {
        System.out.println("The cat meows");
    }

}
