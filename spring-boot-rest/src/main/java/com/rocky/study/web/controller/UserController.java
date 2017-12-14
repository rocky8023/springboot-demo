package com.rocky.study.web.controller;

import com.rocky.study.dao.UserDao;
import com.rocky.study.exception.UsernameIsExitedException;
import com.rocky.study.model.User;
import com.rocky.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangpeng32 on 2017/12/14.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("liu");
        user.setAge(20);
        user = userService.getUserById(id);
        return user != null ? user.toString() : "没有数据";
    }

    @RequestMapping(value = "/add/{username}", method = RequestMethod.GET)
    public Long addUser(@PathVariable("username") String username){
        User user = new User();
        user.setUsername(username);
        user.setPassword("123456");
        user.setAge(20);
        Long id  = userService.insert(user);
        return id;
    }

        /*public UserController(UserRepository myUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = myUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }*/

    @RequestMapping("/userList")
    @ResponseBody
    public Map<String, Object> userList(){
        List<User> myUsers = userService.findAll();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("users",myUsers);
        return map;
    }

    /**
     * 该方法是注册用户的方法，默认放开访问控制
     * @param user
     */
    @PostMapping("/signup")
    public void signUp(@RequestBody User user) {
        User user1 = userService.findByUsername(user.getUsername());
        if(null != user1){
            throw new UsernameIsExitedException("用户已经存在~");
        }
        user.setPassword(DigestUtils.md5DigestAsHex((user.getPassword()).getBytes()));
        userService.insert(user);
    }
}
