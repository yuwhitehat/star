package com.liuziyu.star.mapper;

import com.liuziyu.star.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * desc
 *
 * @author LiuZiyu
 * @date 2022/07/14 10:59
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
}
