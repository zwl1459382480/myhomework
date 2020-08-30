package com.neu.dao;

import java.util.Map;

public interface UserDao {
    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    Map<String,Object> login(String name,String password);

    /**
     * 注册
     * @param name
     * @param password
     * @return
     */
    int addUser(String name,String password);
}
