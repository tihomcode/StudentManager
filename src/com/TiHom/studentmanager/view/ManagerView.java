package com.TiHom.studentmanager.view;

import com.TiHom.studentmanager.controller.ManagerController;
import com.TiHom.studentmanager.factory.BeanFactory;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/4 20:43
 *
 * @author TiHom
 */
public class ManagerView {
    private ManagerController managerController = BeanFactory
            .getInstance("managerController",ManagerController.class);
    Scanner scanner = new Scanner(System.in);

    /**
     * 管理员主页
     */
    public void indexView() throws SQLException {
        int exit = 0;
        do {
            System.out.println("-----------大哥欢迎你来-------------");
            System.out.println("1.查看校园消息");
            System.out.println("2.查看操作日志");
            System.out.println("3.增加学校");
            System.out.println("4.换校长");
            System.out.println("5.退出");
            System.out.print("输入您的选择：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //查看校园消息
                    managerController.watchEvething("Message.txt");
                    break;
                case 2:
                    //查看操作日志
                    managerController.watchEvething("log.log");
                    break;
                case 3:
                    //增加学校
                    managerController.insertSchool();
                    break;
                case 4:
                    //换校长
                    managerController.editSchoolHead();
                    break;
                case 5:
                    //退出
                    exit = 1;
                    break;
                default:
                    exit = 1;
                    break;
            }
        }while (exit!=1);
    }
}
