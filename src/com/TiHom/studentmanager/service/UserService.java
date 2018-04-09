package com.TiHom.studentmanager.service;


import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.User;

import java.sql.SQLException;

/**
 * 2018/3/29 13:14
 * 用户业务层接口类
 * @author TiHom
 */
public interface UserService {

    /**
     * 用户登录
     */
    Object login(String username, String password) throws SQLException;

    /**
     * 添加用户并返回用户id
     */
    int insertUsernameAndPassword(User registUser) throws SQLException;

    /**
     * 添加学生个人信息
     */
    Student insertStudentInfo(Student student,int registUserId) throws SQLException;


}
