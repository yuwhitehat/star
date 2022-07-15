package com.liuziyu.star.controller;

import com.liuziyu.star.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/07/15 15:31
 */
@Slf4j
@RestController
@RequestMapping("api/test/v1/user/")
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("wrong1")
    public int wrong1(@RequestParam("name") String name) {
        return userService.createUserWrong1(name);
    }

    @GetMapping("wrong2")
    public int wrong2(@RequestParam("name") String name) {
        return userService.createUserRight1(name);
    }
}
