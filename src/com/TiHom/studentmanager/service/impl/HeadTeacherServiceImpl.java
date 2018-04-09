package com.TiHom.studentmanager.service.impl;

import com.TiHom.studentmanager.dao.HeadTeacherDao;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.service.HeadTeacherService;
import com.TiHom.studentmanager.service.TeacherService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/4 17:26
 *
 * @author TiHom
 */
public class HeadTeacherServiceImpl implements HeadTeacherService{
    private HeadTeacherDao headTeacherDao = BeanFactory.getInstance("headTeacherDao",HeadTeacherDao.class);
    private TeacherService teacherService  = BeanFactory.getInstance("teacherService",TeacherService.class);


    Scanner scanner = new Scanner(System.in);

    /**
     * 开除学生
     */
    @Override
    public void expelStudent(int studentId) throws SQLException {
        headTeacherDao.expelStudent(studentId);
    }

    /**
     * 解雇老师
     */
    @Override
    public void expelTeacher(int teacherId) throws SQLException {
        headTeacherDao.expelTeacher(teacherId);
    }

    /**
     * 岗位变动
     */
    @Override
    public void adjustJob(int teacherId) throws SQLException {
        Teacher teacher = headTeacherDao.searchTeacher(teacherId);
        teacherService.showPersonInfo(teacher);
        System.out.println("岗位等级变为：(输入power等级0-3)");
        int power = scanner.nextInt();
        headTeacherDao.editPower(power,teacherId);
        System.out.println("岗位变动成功！");
    }


}
