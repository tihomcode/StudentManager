package com.TiHom.studentmanager.dao;


import com.TiHom.studentmanager.entity.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/3/29 13:11
 * 用户数据访问层接口类
 * @author TiHom
 */
public interface UserDao {
    /**
     * 通过用户名查找是否有这个用户
     */
    int findUserByUserName(String username) throws SQLException;

    /**
     * 通过用户名和密码来校验登录
     */
    User findUserByUserNameAndPassword(String username,String password) throws SQLException;


    /**
     * 添加学生
     */
    void addStudent(Student student,int userId) throws SQLException;

    /**
     * 添加用户
     */
    int addUser(User user) throws SQLException;

    /**
     * 权限验证
     */
    Object validatePower(int userId) throws SQLException;

    /**
     * 显示所有班级
     */
    List<TheClass> showClasses(int schoolId,int gradeId) throws SQLException;

    /**
     * 显示所有学校
     */
    List<School> showAllSchool() throws SQLException;
}
