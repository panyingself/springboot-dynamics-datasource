package com.py.service.write.impl;

import com.py.entity.User;
import com.py.mapper.UserMapper;
import com.py.service.write.WriteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by py on 2017/11/24.
 */
@Service
public class WriteUserServiceImpl implements WriteUserService{
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
