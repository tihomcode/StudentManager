package com.TiHom.studentmanager.view;

import com.TiHom.studentmanager.controller.HeadTeacherController;
import com.TiHom.studentmanager.controller.TeacherController;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.utils.LogUtil;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/1 14:43
 * 校长界面
 * @author TiHom
 */
public class HeadTeacherView {
    private HeadTeacherController headTeacherController = BeanFactory.getInstance("headTeacherController",HeadTeacherController.class);
    private TeacherController teacherController = BeanFactory.getInstance("teacherController",TeacherController.class);

    Scanner scanner = new Scanner(System.in);

    /**
     * 主页
     */
    public void indexView(Teacher teacher) throws SQLException {
        //循环判定标记
        int exit = 0;
        do {
            System.out.println("----------------欢迎来到校长主页-------------");
            System.out.println("1.查看个人信息");
            System.out.println("2.开除学生");
            System.out.println("3.解雇教师");
            System.out.println("4.岗位变动");
            System.out.println("5.发布消息");
            System.out.println("6.退出");
            System.out.print("选择：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //查看个人信息
                    teacherController.showPerosnInfo(teacher);
                    break;
                case 2:
                    //开除学生
                    int studentId = headTeacherController.expelStudent();
                    LogUtil.writeToLog(teacher.getUsername()+"校长",
                            "开除了学号为"+studentId+"的学生。");
                    break;
                case 3:
                    //解雇教师
                    int teacherId = headTeacherController.expelTeacher();
                    LogUtil.writeToLog(teacher.getUsername()+"校长",
                            "解雇了编号为"+teacherId+"的老师。");
                    break;
                case 4:
                    //岗位变动
                    int adjustId = headTeacherController.adjustJob();
                    LogUtil.writeToLog(teacher.getUsername()+"校长",
                            "对编号为"+adjustId+"的老师进行岗位调动。");
                    break;
                case 5:
                    //发布消息
                    headTeacherController.publishMessage();
                    LogUtil.writeToLog(teacher.getUsername()+"校长","发布了消息。");
                    break;
                case 6:
                    //退出
                    exit = 1;
                    break;
                default:
                    //退出
                    System.out.println("乱选那就回主页吧！");
                    break;
            }
        }while (exit!=1);
    }
}
