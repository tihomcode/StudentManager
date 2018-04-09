package com.TiHom.studentmanager.view;

import com.TiHom.studentmanager.controller.PraepostorController;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/1 14:44
 * 级长界面
 * @author TiHom
 */
public class PraepostorView {
    //创建控制层对象
    private PraepostorController praepostorController = BeanFactory.getInstance("praepostorController",PraepostorController.class);
    Scanner scanner = new Scanner(System.in);


    /**
     * 主页
     */
    public void indexView(Teacher teacher) throws SQLException {
        //循环判定标记
        int exit = 0;
        do{
            System.out.println("--------------------欢迎进入级长主页-----------------");
            System.out.println("1.班级情况");
            System.out.println("2.老师情况");
            System.out.println("3.校内消息");
            System.out.println("4.退出");
            System.out.print("选择：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //班级情况
                    praepostorController.showAllClasses(teacher);
                    break;
                case 2:
                    //老师情况
                    System.out.println("1.查看所有老师");
                    System.out.println("2.查看本年级老师");
                    int input = scanner.nextInt();
                    switch (input){
                        case 1:
                            praepostorController.showAllTeachers();
                            break;
                        case 2:
                            praepostorController.showGradeTeacher(teacher.getGradeId());
                            break;
                        default:
                            //返回
                            break;
                    }
                    break;
                case 3:
                    //校内情况
                    praepostorController.watchMessage();
                    break;
                case 4:
                    //退出
                    exit = 1;
                    break;
                default:
                    System.out.println("乱选那就回主页吧！");
                    break;
            }
        }while (exit!=1);
    }
}
