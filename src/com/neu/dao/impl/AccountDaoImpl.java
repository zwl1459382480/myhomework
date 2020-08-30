package com.neu.dao.impl;

import com.neu.dao.AccountDao;
import com.neu.dao.BaseDao;

import java.util.Date;

public class AccountDaoImpl extends BaseDao implements AccountDao {
    /**
     * 添加账目
     * @param name
     * @param count
     * @param price
     * @param date
     * @return
     */
    @Override
    public int addAccount(String name, String count, String price, String date,String user_id) {
        int row = 0;
        String sql = "insert into tbl_account(goods_name,goods_count,goods_price,goods_date,goods_user_id) values(?,?,?,?,?)";
        row = executeUpdate(sql,name,count,price,date,user_id);
        return row;
    }
}
