package com.neu.view;

import com.neu.dao.AccountDao;
import com.neu.dao.UserDao;
import com.neu.dao.impl.AccountDaoImpl;
import com.neu.dao.impl.UserDaoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.neu.util.Read.getIn;

public class AccountView {

    private static AccountDao accountDao = new AccountDaoImpl();
    /**
     * 添加账目
     */
    protected static void addAccountMenu() {
        Date date = null;
        UserView userView = new UserView();
        String user_id = userView.getMap().get("user_id").toString();
        SimpleDateFormat mydate = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("请输入您购买的商品:");
        String name = getIn().next();
        System.out.println("请输入您购买的数量:");
        String count = getIn().next();
        System.out.println("请输入您购买的该商品的单品金额:");
        String price = getIn().next();
        System.out.println("请输入您购买商品的日期(yyyy-MM-dd):");
        String dateStr = getIn().next();
        try {
            //把输入的日期字符串转成日期类型
            date = mydate.parse(dateStr);
            //把日期类型转成字符串方便后面与数据库的交互
            dateStr = mydate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("是否确认添加?(1.确认\t2.取消");
        int confirm = getIn().nextInt();
        switch (confirm){
            case 1:
                accountDao.addAccount(name,count,price,dateStr,user_id);
                break;
            case 2:
                break;
            default:
                System.out.println("输入有误!");
                break;
        }
    }
}
