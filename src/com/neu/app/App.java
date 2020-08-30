package com.neu.app;

import com.neu.view.UserView;

import static com.neu.util.Read.getIn;

public class App {

    public static void main(String[] args) {
        menu();
    }

    /**
     * 菜单
     */
    public static void menu(){
        System.out.println("欢迎使用账目管理系统!\n1.登录\t2.注册\n请输入您的选择:");
        int nextInt = getIn().nextInt();
        UserView userView = new UserView();
        switch (nextInt){
            case 1:
                userView.login();confirm();
                break;
            case 2:
                userView.register();confirm();
                break;
                default:
                    System.out.println("输入有误!");confirm();
                    break;
        }
    }

    public static void confirm(){
        System.out.println("是否返回登录界面?(1.返回登录界面\t2.退出)");
        int nextInt = getIn().nextInt();
        switch (nextInt){
            case 1:
                menu();
                break;
            case 2:
                System.exit(1);
                break;
                default:
                    confirm();
                    break;
        }
    }
}
