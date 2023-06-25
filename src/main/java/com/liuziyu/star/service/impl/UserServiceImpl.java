package com.liuziyu.star.service.impl;

import com.liuziyu.star.entity.UserEntity;
import com.liuziyu.star.mapper.UserRepository;
import com.liuziyu.star.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/07/15 15:22
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;


    /**
     * Spring事务生效测试3
     * <p>
     * 结果：当用户名称不合法时抛出异常并没有回滚
     * 原因：事务生效了---但异常被catch住了，无法进行后续事务处理
     *
     * @param name
     * @return
     */
    @Override
    @Transactional
    public int createUserWrong1(String name) {
        try {
            //this.createUserPrivate(new UserEntity(name));
            //this.createUserPublic(new UserEntity(name));
            this.createUser(new UserEntity(name));
        } catch (Exception ex) {
            log.error("create user failed because {}", ex.getMessage());
        }
        return getUserCount(name);
    }

    /**
     * Spring事务生效正确方法
     *
     * @param name
     * @return
     */
    @Override
    @Transactional
    public int createUserRight1(String name) {
        try {
            this.createUser(new UserEntity(name));
        } catch (Exception exception) {
            log.error("create user failed because {}", exception.getMessage());
            // 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return getUserCount(name);
    }

    /**
     * @param userEntity
     */
    private void createUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username!");
        }
    }


    /**
     * Spring事务生效测试1
     * 结果：当用户名称不合法时抛出异常并没有回滚
     * 原因：事务没有生效----只有定义在public方法上的事务才会生效
     *
     * @param userEntity
     */
    @Transactional
    private void createUserPrivate(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username!");
        }
    }

    /**
     * Spring事务生效测试2
     * 结果：当用户名称不合法时抛出异常并没有回滚
     * 原因：事务没有生效----必须通过代理过的类从外部调用目标方法才能生效
     * 问题: todo 什么是代理过的类？？？？
     *
     * @param userEntity
     */
    @Transactional
    public void createUserPublic(UserEntity userEntity) {
        userRepository.save(userEntity);
        if (userEntity.getName().contains("test")) {
            throw new RuntimeException("invalid username!");
        }
    }


    public int getUserCount(String name) {
        return userRepository.findByName(name).size();
    }
}

