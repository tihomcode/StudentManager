package com.TiHom.studentmanager.dao;

import com.TiHom.studentmanager.entity.Teacher;

import java.sql.SQLException;

/**
 * 2018/4/4 17:25
 *
 * @author TiHom
 */
public interface HeadTeacherDao {
    /**
     * 删除学生
     */
    void expelStudent(int studentId) throws SQLException;

    /**
     * 删除老师
     */
    void expelTeacher(int teacherId) throws SQLException;

    /**
     * 查找老师
     */
    Teacher searchTeacher(int teacherId) throws SQLException;

    /**
     * 更改老师权限
     */
    void editPower(int power, int teacherId) throws SQLException;
}
