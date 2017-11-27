package com.py.entity;

import java.io.Serializable;

/**
 * Created by py on 2017/10/17.
 */
public class User implements Serializable{
    private static final long serialVersionUID = 3148176768559230888L;

    private  String userName;
    private int age;
    private int sex;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
