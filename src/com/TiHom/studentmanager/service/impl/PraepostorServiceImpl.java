package com.TiHom.studentmanager.service.impl;

import com.TiHom.studentmanager.dao.PraepostorDao;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Grade;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;
import com.TiHom.studentmanager.service.PraepostorService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/4/4 12:39
 *
 * @author TiHom
 */
public class PraepostorServiceImpl implements PraepostorService{
    private PraepostorDao praepostorDao = BeanFactory.getInstance("praepostorDao",PraepostorDao.class);
    Scanner scanner = new Scanner(System.in);


    /**
     * 查看所有年级
     */
    @Override
    public void showAllGrades(int schoolId) throws SQLException {
        List<Grade> grades = praepostorDao.showAllGrades(schoolId);
        //遍历年级
        for(Grade grade:grades){
            System.out.println(grade.getId()+"."+grade.getGradeName());
        }
    }

    /**
     * 查看年级的班级
     */
    @Override
    public List<TheClass> showGradeClasses(int gradeId) throws SQLException {
        return praepostorDao.showGradeClasses(gradeId);
    }

    /**
     * 增加班级
     */
    @Override
    public int addClass(int gradeId) throws SQLException {
        TheClass theClass = new TheClass();
        System.out.println("添加操作：");
        System.out.print("班级编号：");
        int classId = scanner.nextInt();
        System.out.print("班级名称：");
        String className = scanner.next();
        System.out.print("班主任编号：(不知道请输入0)");
        int classTeacher = scanner.nextInt();
        theClass.setId(classId);
        theClass.setClassname(className);
        theClass.setHeadteacherId(classTeacher);
        theClass.setIngrade(gradeId);
        //调用方法
        praepostorDao.addClass(theClass);
        return classId;
    }

    /**
     * 删除班级
     */
    @Override
    public int deleteClass(int gradeId) throws SQLException {
        System.out.println("删除操作：");
        System.out.println("请输入要删除的班级编号：");
        int choice = scanner.nextInt();
        praepostorDao.deleteClass(choice);
        return choice;
    }

    /**
     * 显示所有老师
     */
    @Override
    public List<Teacher> showAllTeachers() throws SQLException {
        return praepostorDao.showAllTeachers();
    }

    /**
     * 显示本级老师
     */
    @Override
    public List<Teacher> showGradeTeacher(int gradeId) throws SQLException {
        return praepostorDao.showGradeTeacher(gradeId);
    }

    /**
     * 精确查询老师
     */
    @Override
    public Teacher searchTeacher() throws SQLException {
        System.out.print("输入教师编号：(不知道输入0)");
        int teacherId = scanner.nextInt();
        System.out.print("输入教师姓名：(不知道输入无)");
        String teacherName = scanner.next();
        return praepostorDao.searchTeacher(teacherId,teacherName);
    }

    /**
     * 模糊查询老师
     */
    @Override
    public List<Teacher> specialSearchTeacher() throws SQLException {
        System.out.print("输入教师编号：(不知道输入0)");
        int teacherId = scanner.nextInt();
        System.out.print("输入教师姓名：(不知道输入无)");
        String teacherName = scanner.next();
        return praepostorDao.SpecialSearchTeacher(teacherId,teacherName);
    }


}
