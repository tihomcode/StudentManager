package com.TiHom.studentmanager.service.impl;

import com.TiHom.studentmanager.dao.StudentDao;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.jo.HeadTeacherInfo;
import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.service.StudentService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/4/1 17:11
 *
 * @author TiHom
 */
public class StudentServiceImpl implements StudentService {
    //创建dao层对象
    private StudentDao studentDao = BeanFactory.getInstance("studentDao",StudentDao.class);

    Scanner scanner = new Scanner(System.in);

    /**
     * 展示个人信息
     */
    @Override
    public StudentInfo showPersonInfo(Student student) throws SQLException {
        //获取学生个人信息对象
        StudentInfo studentInfo = studentDao.showPersonInfo(student);
        return studentInfo;
    }

    /**
     * 修改个人信息
     */
    @Override
    public void editInfo(StudentInfo studentInfo) throws SQLException {
        System.out.println("----------------修改个人信息--------------------");
        System.out.println("1.联系方式");
        System.out.println("2.邮箱地址");
        System.out.println("3.完成修改(其他信息都为固定内容无需修改)");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //联系方式修改
                System.out.println("联系方式："+studentInfo.getPhone());
                System.out.print("进行修改：");
                String editPhone = scanner.next();
                //修改信息
                studentInfo.setPhone(editPhone);
                //更新数据库
                studentDao.editPhoneInfo(studentInfo.getPhone(),studentInfo.getId());
                break;
            case 2:
                System.out.println("邮箱地址："+studentInfo.getEmail());
                System.out.print("进行修改：");
                String editEmail = scanner.next();
                //修改信息
                studentInfo.setEmail(editEmail);
                //更新数据库
                studentDao.editEmailInfo(studentInfo.getEmail(),studentInfo.getId());
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    /**
     * 展示个人成绩
     */
    @Override
    public void showScore(int studentId) throws SQLException {
        List<ScoreInfo> scoreInfoList = studentDao.showScore(studentId);
        if(scoreInfoList.size()==0){
            System.out.println("没有找到成绩！");
            return;
        }
        System.out.println("---------------------学生成绩-------------------------");
        System.out.println("学生学号   学生姓名   课程名称   课程成绩");
        for (ScoreInfo scoreInfo:scoreInfoList){
            System.out.println(scoreInfo.getId() + "   " +
                    scoreInfo.getUsername() + "   " +
                    scoreInfo.getSubjectName() + "   " +
                    scoreInfo.getSubjectScore());
        }
        System.out.println("----------------------------------------------");
        HeadTeacherInfo headTeacherInfo = studentDao.queryHeadTeacher(studentId);
        System.out.println("联系老师："+headTeacherInfo.getTeacherName()+"  "+headTeacherInfo.getPhone()
                +" "+headTeacherInfo.getEmail());
    }
}
