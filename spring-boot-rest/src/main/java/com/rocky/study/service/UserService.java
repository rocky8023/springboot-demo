package com.rocky.study.service;

import com.rocky.study.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangpeng32 on 2017/12/14.
 */
public interface UserService {
    public User getUserById(@Param("id") Long id);

    public Long insert(User user);

    public User findByUsername(@Param("username") String username);

    public List<User> findAll();
}
