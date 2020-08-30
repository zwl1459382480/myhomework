package com.neu.dao;

import java.util.Date;

public interface AccountDao {

    int addAccount(String name, String count, String price, String date,String user_id);
}
