package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/1 17:12
 *
 * @author TiHom
 */
public class StudentController {
    //创建service层对象
    private StudentService studentService = BeanFactory.getInstance("studentService",StudentService.class);
    Scanner scanner = new Scanner(System.in);

    /**
     * 查看个人信息
     */
    public void showPersonInfo(Student student) throws SQLException {
        StudentInfo studentInfo = studentService.showPersonInfo(student);
        System.out.println("----------------学生个人信息----------------");
        System.out.println("学号："+studentInfo.getId());
        System.out.println("学生姓名："+studentInfo.getUsername());
        System.out.println("学生性别："+studentInfo.getSex());
        System.out.println("联系方式："+studentInfo.getPhone());
        System.out.println("邮箱地址："+studentInfo.getEmail());
        System.out.println("班级："+studentInfo.getClassname());
        System.out.println("年级："+studentInfo.getGradeName());
        System.out.println("学校："+studentInfo.getSchoolName());
        System.out.println("-------------------------------------------");
        System.out.println("1.修改个人信息     2.返回");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //修改个人信息
                studentService.editInfo(studentInfo);
                //返回个人信息显示界面
                studentService.showPersonInfo(student);
                break;
            case 2:
                //返回主页
                break;
            default:
                System.out.println("既然要乱选那就返回主页吧！");
                //返回主页
                break;
        }
    }

    /**
     * 查看课程成绩
     */
    public void showScore(Student student) throws SQLException {
        studentService.showScore(student.getId());
    }
}
