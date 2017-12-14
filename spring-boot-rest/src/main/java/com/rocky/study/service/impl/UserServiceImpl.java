package com.rocky.study.service.impl;

import com.rocky.study.dao.UserDao;
import com.rocky.study.model.User;
import com.rocky.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangpeng32 on 2017/12/14.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(@Param("id") Long id){
        return  userDao.getUserById(id);
    }

    @Override
    public Long insert(User user){
        return userDao.insert(user);
    }

    @Override
    public User findByUsername(@org.apache.ibatis.annotations.Param("username") String username){
        return  userDao.loadUserByUsername(username);
    }

    @Override
    public List<User> findAll(){
        return  userDao.list();
    }
}
