package com.TiHom.studentmanager.view;

import com.TiHom.studentmanager.controller.TeacherController;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Teacher;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/1 14:43
 * 老师界面
 * @author TiHom
 */
public class TeacherView {
    //创建控制层对象
    private TeacherController teacherController = BeanFactory.getInstance("teacherController",TeacherController.class);
    Scanner scanner = new Scanner(System.in);

    /**
     * 主页
     */
    public void indexView(Teacher teacher) throws SQLException {
        //循环判定标记
        int exit = 0;
        do {
            System.out.println("--------------------欢迎进入教师主页-----------------");
            System.out.println("1.查看个人信息");
            System.out.println("2.学生信息");
            System.out.println("3.班级信息");
            System.out.println("4.课程信息");
            System.out.println("5.学生成绩");
            System.out.println("6.校园消息");
            System.out.println("7.退出");
            System.out.print("选择：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //查看个人信息
                    teacherController.showPerosnInfo(teacher);
                    break;
                case 2:
                    //学生信息
                    teacherController.showStudentInfo(teacher);
                    break;
                case 3:
                    //班级信息
                    teacherController.showClasses(teacher.getId());
                    break;
                case 4:
                    //课程信息
                    teacherController.showSubjects(teacher);
                    break;
                case 5:
                    //学生成绩
                    teacherController.showStudentScore(teacher);
                    break;
                case 6:
                    //校园消息
                    teacherController.watchMessage();
                    break;
                case 7:
                    exit = 1;
                    break;
                default:
                    System.out.println("乱选那就回主页吧！");
                    break;
            }
        }while (exit!=1);
    }
}
