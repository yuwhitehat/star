package com.liuziyu.star.test;

import com.liuziyu.star.entity.extendtest.Animal;
import com.liuziyu.star.entity.extendtest.Cat;
import com.liuziyu.star.entity.extendtest.Dog;
import org.junit.Test;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2023/03/29 14:35
 */
public class ExtendsTest {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();  // Animal 对象
        Animal myDog = new Dog();  // Dog 对象
        Animal myCat = new Cat();  // Cat 对象

        myAnimal.makeSound();
        myDog.makeSound();
        myCat.makeSound();
    }

    /**
     * instanceof判断某个实例是否是某个类的，或者是否是其子类的
     */
    @Test
    public void test() {
        Animal animal = new Cat();
        if (animal instanceof Cat) {
            System.out.println("animal is an instance of Cat");
        } else {
            System.out.println("animal is not an instance of Cat");
        }
    }
}
