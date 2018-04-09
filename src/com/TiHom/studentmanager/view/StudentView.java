package com.TiHom.studentmanager.view;


import com.TiHom.studentmanager.controller.StudentController;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Student;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/1 14:43
 * 学生界面
 * @author TiHom
 */
public class StudentView {
    //创建控制层对象
    private StudentController studentController = BeanFactory.getInstance
            ("studentController",StudentController.class);
    Scanner scanner = new Scanner(System.in);


    /**
     * 主页
     */
    public void indexView(Student student) throws SQLException {
        //循环判定标记
        int exit = 0;
        do {
            System.out.println("----------------欢迎进入学生主页----------------");
            System.out.println("1.查看个人信息");
            System.out.println("2.查看课程成绩");
            System.out.println("3.退出");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //查看个人信息
                    studentController.showPersonInfo(student);
                    break;
                case 2:
                    //查看课程成绩
                    studentController.showScore(student);
                    break;
                case 3:
                    //退出系统
                    exit = 1;
                    break;
                default:
                    //输入错误
                    System.out.println("请重新输入正确选项，别乱来！！！");
                    break;
            }
        }while(exit!=1); //退出
    }

}
