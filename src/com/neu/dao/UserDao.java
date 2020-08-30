package com.neu.dao;

import java.util.Map;

public interface UserDao {

    Map<String,Object> login(String name,String password);

    int addUser(String name,String password);
}
