package com.py.service.read.impl;

import com.py.entity.User;
import com.py.mapper.UserMapper;
import com.py.service.read.ReadUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by py on 2017/10/16.
 */
@Service
public class ReadUserServiceImpl implements ReadUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String findUserNmae() {
        List<User> userList = null;
        try {
            userList = userMapper.findAllUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList.get(0).getUserName();
    }

    @Override
    public Object getAllUser() {
        return  userMapper.getAllUser();
    }
}
