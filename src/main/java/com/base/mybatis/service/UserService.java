package com.base.mybatis.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.mybatis.dao.UserDao;
import com.base.mybatis.dto.User;

@Service
public class UserService {

	@Autowired //连接到UserDao Bean
    private UserDao userDao;

    public String show() {
        return "Hello World!";
    }

    public List<User> showDao(int age) {
        return userDao.get(age);
    }

    public String insert(String name, int age) { //插入一条记录
        User user = new User();
        user.setName(name);
        user.setAge(age);
        userDao.insert(user);
        return "Insert ( \""+name+"\", age"+age+") OK!";
    }
}
