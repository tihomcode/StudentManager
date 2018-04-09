package com.TiHom.studentmanager.dao;

import com.TiHom.studentmanager.jo.HeadTeacherInfo;
import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/1 17:10
 *
 * @author TiHom
 */
public interface StudentDao {
    /**
     * 显示学生的个人信息
     */
    StudentInfo showPersonInfo(Student student) throws SQLException;

    /**
     * 修改联系信息
     */
    void editPhoneInfo(String editPhone,int studentI) throws SQLException;

    /**
     * 修改邮箱信息
     */
    void editEmailInfo(String editEmail,int studentI) throws SQLException;

    /**
     * 显示学生成绩信息
     */
    List<ScoreInfo> showScore(int studentId) throws SQLException;

    /**
     * 根据学号查询班主任
     */
    HeadTeacherInfo queryHeadTeacher(int studentId) throws SQLException;
}
