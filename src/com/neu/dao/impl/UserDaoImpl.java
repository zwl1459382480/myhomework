package com.neu.dao.impl;

import com.neu.dao.BaseDao;
import com.neu.dao.UserDao;

import java.util.List;
import java.util.Map;

public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 查询用户登录信息
     * @param name
     * @param password
     * @return
     */
    @Override
    public Map<String, Object> login(String name, String password) {
        Map<String,Object> map = null;
        List<Map<String,Object>> list = null;
        String sql = "select * from tbl_user where user_name = ? and user_pwd = MD5(?)";
        list = executeQuery(sql,name,password);
        if (list.size()>0){
            map = list.get(0);
        }
        return map;
    }

    /**
     * 添加用户
     * @param name
     * @param password
     * @return
     */
    @Override
    public int addUser(String name, String password) {
        int row = 0;
        String sql = "insert into tbl_user(user_name,user_pwd) values(?,MD5(?)) ";
        row = super.executeUpdate(sql,name,password);
        return row;
    }
}
