package com.TiHom.studentmanager.test;

import com.TiHom.studentmanager.view.LoginAndRegistView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * 2018/3/29 16:28
 *
 * @author TiHom
 */
public class RegistAndLoginTest {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> loginMap = new HashMap<String, String>();
        //全局登录状态
        LoginAndRegistView loginAndRegistView = new LoginAndRegistView();
        //进入登录界面
        int exit = 0;
        do {
            loginAndRegistView.loginView(loginMap);
            System.out.println("退出系统吗？");
            System.out.println("1.退出   2.登录");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    exit = 1;
                    break;
                case 2:
                    break;
                default:
                    break;
            }
        }while (exit!=1);
    }
}
