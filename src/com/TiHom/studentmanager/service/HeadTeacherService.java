package com.TiHom.studentmanager.service;

import java.sql.SQLException;

/**
 * 2018/4/4 17:26
 *
 * @author TiHom
 */
public interface HeadTeacherService {

    /**
     * 开除学生
     */
    void expelStudent(int studentId) throws SQLException;

    /**
     * 解雇老师
     */
    void expelTeacher(int teacherId) throws SQLException;

    /**
     * 岗位变动
     */
    void adjustJob(int teacherId) throws SQLException;
}
