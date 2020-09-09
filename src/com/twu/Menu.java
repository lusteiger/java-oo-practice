package com.twu;


import java.util.List;
import java.util.Scanner;

public class Menu {

    static public void MainMenu(List<Event> list) {
        System.out.println("欢迎来到热搜排行榜，你是？");
        System.out.println("1. 用户");
        System.out.println("2. 管理员");
        System.out.println("3. 退出");
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.next());
        if (number == 1) {
            System.out.println("请输入用户姓名");
            Scanner scanName = new Scanner(System.in);
            String userName = scanName.next();
            User user = new User(userName);
            userMenu(user,list);
        }


        if (number == 2) {
            System.out.println("请输入管理员姓名");
            Scanner scanName = new Scanner(System.in);
            String adminName = scanName.next();
            System.out.println("请输入管理员密码");
            Scanner scanPwd = new Scanner(System.in);
            String adminPwd = scanPwd.next();
            Administrator administrator = new Administrator(adminName, adminPwd);
            AdminMenu(administrator,list);
        }
        if (number == 3){
            System.out.println("退出系统");
            System.exit(0);
        }
    }

    static public void userMenu(User user, List<Event> list) {

        System.out.println("你好," +user.getUserName() + ",你可以:");
        System.out.println("1. 查看热搜排行榜");
        System.out.println("2. 给热搜事件投票");
        System.out.println("3. 购买热搜");
        System.out.println("4. 添加热搜");
        System.out.println("5. 退出");

        Scanner chosse = new Scanner(System.in);
        int userChosse = Integer.parseInt(chosse.next());


        switch (userChosse) {
            case 1:
                user.QueryList(user,list);
                break;
            case 2:
                user.VoteEvent(user,list);
                break;
            case 3:
                user.PurchaseEvent(user,list);
                break;
            case 4:
                user.AddEvent(user, list);
                break;
            case 5:
                System.out.println("用户退出");
                MainMenu(list);
            default:
                System.out.println("输入错误请重试");
                userMenu(user, list);

        }
    }
    static public void AdminMenu(Administrator administrator, List<Event> list){

        System.out.println("你好," + administrator.getAdminName() + ",你可以");
        System.out.println("1. 查看热搜排行榜");
        System.out.println("2. 添加热搜");
        System.out.println("3. 添加超级热搜");
        System.out.println("4. 退出");

        Scanner Chosse = new Scanner(System.in);
        int adminChosse = Integer.parseInt(Chosse.next());
        switch (adminChosse) {
            case 1:
                administrator.QueryList(administrator,list);
                break;
            case 2:
                administrator.AddEvent(administrator,list);
                break;
            case 3:
                administrator.AddSuperEvent(administrator,list);
                break;
            case 4:
                System.out.println("管理员退出");
                 MainMenu(list);
            default:
                System.out.println("输入错误请重试");
                AdminMenu(administrator,list);
        }
    }
}
