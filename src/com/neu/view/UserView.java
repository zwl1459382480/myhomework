package com.neu.view;

import com.neu.app.App;
import com.neu.dao.UserDao;
import com.neu.dao.impl.UserDaoImpl;

import java.util.Map;

import static com.neu.app.App.menu;
import static com.neu.util.Read.getIn;

public class UserView {

    private Map<String,Object> map ;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    private static UserDao userDao = new UserDaoImpl();
    /**
     * 用户注册
     */
    public static void register() {
        int row = 0;
        System.out.println("欢迎您的注册!");
        System.out.println("请输入用户名:");
        String name = getIn().next();
        System.out.println("请输入密码:");
        String password = getIn().next();
        row = userDao.addUser(name, password);
        if (row>0){
            System.out.println("注册成功!");
        }else {
            System.out.println("注册失败!");
        }
    }

    /**
     * 用户登录
     */
    public static void login(){
        UserView userView = new UserView();
        Map<String, Object> userAll = null;
        System.out.println("欢迎您的登录!");
        System.out.println("请输入用户名:");
        String name = getIn().next();
        System.out.println("请输入密码:");
        String password = getIn().next();
        userAll = userDao.login(name, password);
        if (userAll!=null&&userAll.size()>0) {
            userView.setMap(userAll);
            user();
        }else {
            System.out.println("用户可能不存在!");
            loginfault();
        }
    }

    /**
     * 登录失败
     */
    public static void loginfault(){
        System.out.println("登录失败!");
        System.out.println("是否重新登录?\n(1.重新登录\t2.返回主菜单)");
        int nextInt = getIn().nextInt();
        switch (nextInt){
            case 1:
                login();
                break;
            case 2:
                menu();
                break;
                default:
                    App.confirm();
                    break;
        }
    }

    /**
     * 用户操作
     */
    private static void user(){
        AccountView accountView = new AccountView();
        String[] a = {"1.添加账目信息","2.修改账目信息","3.查询账目信息","4.删除账目信息"};
        for (int i = 0 ;i<a.length;i++){
            System.out.print(a[i]+"\t");
        }
        System.out.println();
        System.out.print("请输入您的选择:");
        int nextInt = getIn().nextInt();
        switch (nextInt){
            case 1:
                accountView.addAccountMenu();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("输入错误!");menu();
                break;
        }
    }
}
