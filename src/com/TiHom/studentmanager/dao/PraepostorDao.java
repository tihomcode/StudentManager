package com.TiHom.studentmanager.dao;

import com.TiHom.studentmanager.entity.Grade;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/4 12:40
 *
 * @author TiHom
 */
public interface PraepostorDao {

    /**
     * 查看所有年级
     */
    List<Grade> showAllGrades(int schoolId) throws SQLException;

    /**
     * 查看年级的所有班级
     */
    List<TheClass> showGradeClasses(int gradeId) throws SQLException;

    /**
     * 查看所有班级
     */
    List<TheClass> showAllClasses();

    /**
     * 增加班级
     */
    void addClass(TheClass theClass) throws SQLException;

    /**
     * 删除班级
     */
    void deleteClass(int classId) throws SQLException;

    /**
     * 显示所有老师
     */
    List<Teacher> showAllTeachers() throws SQLException;

    /**
     * 显示本级所有老师
     */
    List<Teacher> showGradeTeacher(int gradeId) throws SQLException;

    /**
     * 精确查询老师
     */
    Teacher searchTeacher(int teacherId, String teacherName) throws SQLException;

    /**
     * 模糊查询老师
     */
    List<Teacher> SpecialSearchTeacher(int teacherId,String teacherName) throws SQLException;
}
