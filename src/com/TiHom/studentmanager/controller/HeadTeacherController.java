package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.service.HeadTeacherService;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 2018/4/4 17:26
 *
 * @author TiHom
 */
public class HeadTeacherController {
    private HeadTeacherService headTeacherService = BeanFactory.getInstance("headTeacherService",HeadTeacherService.class);
    Scanner scanner = new Scanner(System.in);

    /**
     * 开除学生
     */
    public int expelStudent() throws SQLException {
        System.out.println("要开除的学生：(输入学生编号)");
        int studentId = scanner.nextInt();
        headTeacherService.expelStudent(studentId);
        System.out.println("开除成功！");
        return studentId;
    }

    /**
     * 解雇老师
     */
    public int expelTeacher() throws SQLException {
        System.out.println("要解雇的教师：(输入教师编号)");
        int teacherId = scanner.nextInt();
        headTeacherService.expelTeacher(teacherId);
        System.out.println("解雇成功！");
        return teacherId;
    }

    /**
     * 岗位变动
     */
    public int adjustJob() throws SQLException {
        System.out.println("要调整岗位的教师：(输入教师编号)");
        int teacherId = scanner.nextInt();
        headTeacherService.adjustJob(teacherId);
        return teacherId;
    }

    /**
     * 发布消息
     */
    public void publishMessage(){
        int exit = 0;
        do {
            System.out.println("输入您要发布的消息：");
            String message = scanner.next();
            String dateFormat = new SimpleDateFormat("yyyy/MM/dd EEE HH:mm:ss").format(new Date());
            message = dateFormat +"\n"+ message + "\n";
            System.out.println("确定发布吗？");
            System.out.println("1.确定    2.取消   3.退出");
            System.out.print("输入选择：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    PrintWriter outputStream = null;
                    try {
                        outputStream = new PrintWriter(new FileOutputStream("Message.txt",true));
                        outputStream.print(message);
                        outputStream.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("写入失败");
                        throw new RuntimeException(e);
                    }
                    System.out.println("发布成功！");
                    //返回主页
                    exit = 1;
                    break;
                case 2:
                    break;
                default:
                    //返回主页
                    exit = 1;
                    break;
            }
        }while (exit!=1);
    }


}
