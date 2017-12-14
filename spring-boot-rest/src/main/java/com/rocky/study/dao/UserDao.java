package com.rocky.study.dao;

import com.rocky.study.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangpeng32 on 2017/12/14.
 */
@Mapper
public interface  UserDao{
    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select id, username,password, age from user where id=#{id} ")
    public  User getUserById(@Param("id") Long id);

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @Insert(" insert into user (username, password, age) values (#{username}, #{password}, #{age})")
    public Long insert(User user);

    @Select("select id, username,password, age from user where username=#{username} ")
    public User loadUserByUsername(@org.apache.ibatis.annotations.Param("username") String username);

    @Select("select id, username,password, age from user ")
    public List<User> list();
}
