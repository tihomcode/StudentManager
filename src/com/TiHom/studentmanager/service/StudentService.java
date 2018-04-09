package com.TiHom.studentmanager.service;

import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;

import java.sql.SQLException;

/**
 * 2018/4/1 17:11
 *
 * @author TiHom
 */
public interface StudentService {

    /**
     * 展示个人信息
     */
    StudentInfo showPersonInfo(Student student) throws SQLException;

    /**
     * 修改个人信息
     */
    void editInfo(StudentInfo student) throws SQLException;

    /**
     * 展示个人成绩
     */
    void showScore(int studentId) throws SQLException;
}
