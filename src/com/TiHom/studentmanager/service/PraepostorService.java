package com.TiHom.studentmanager.service;


import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/4 12:39
 *
 * @author TiHom
 */
public interface PraepostorService {
    /**
     * 查看所有年级
     */
    void showAllGrades(int schoolId) throws SQLException;

    /**
     * 查看年级的班级
     */
    List<TheClass> showGradeClasses(int gradeId) throws SQLException;

    /**
     * 增加班级
     */
    int addClass(int gradeId) throws SQLException;

    /**
     * 删除班级
     */
    int deleteClass(int gradeId) throws SQLException;

    /**
     * 显示所有老师
     */
    List<Teacher> showAllTeachers() throws SQLException;

    /**
     * 显示本级老师
     */
    List<Teacher> showGradeTeacher(int gradeId) throws SQLException;

    /**
     * 精确查询老师
     */
    Teacher searchTeacher() throws SQLException;

    /**
     * 模糊查询老师
     */
    List<Teacher> specialSearchTeacher() throws SQLException;

}
