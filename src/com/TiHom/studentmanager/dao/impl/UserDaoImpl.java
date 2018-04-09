package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.UserDao;
import com.TiHom.studentmanager.entity.*;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/3/29 13:14
 * 用户数据访问层接口实现类
 * @author TiHom
 */
public class UserDaoImpl implements UserDao{
    DaoUtil daoUtil = new DaoUtil();

    /**
     * 通过用户名查找是否有这个用户
     */
    @Override
    public int findUserByUserName(String username) throws SQLException {
        String sql = "SELECT username FROM t_user where username=?";
        User user = this.daoUtil.get(User.class,sql,username);
        if(user==null){
            //证明没有找到用户，可以注册
            return 0;
        }
        //证明找到用户
        return 1;
    }

    /**
     * 通过用户名和密码来校验登录
     */
    @Override
    public User findUserByUserNameAndPassword(String username,String password) throws SQLException {
        String sql = "SELECT id,username,password FROM t_user where username=? and password=?";
        Object[] params = {username,password};
        return this.daoUtil.get(User.class,sql,params);
    }

    /**
     * 添加学生
     */
    @Override
    public void addStudent(Student student,int userId) throws SQLException {
        String sql = "INSERT INTO t_student VALUES(?,?,?,?,?,?,?)";
        Object[] params = {student.getId(),student.getUsername(),student.getSex(),
                student.getPhone(), student.getEmail(),student.getClassId(),userId
        };
        this.daoUtil.update(sql,params);
    }

    /**
     * 添加用户获取用户id
     */
    @Override
    public int addUser(User user) throws SQLException {
        String sql = "INSERT INTO t_user(username,password) VALUES(?,?) ";
        Object[] params = {user.getUsername(), user.getPassword()};
        this.daoUtil.update(sql,params);
        String sql1 = "SELECT id FROM t_user WHERE username=?";
        User findUser = this.daoUtil.get(User.class,sql,user.getUsername());
        return findUser.getId();
    }

    /**
     * 权限验证
     */
    @Override
    public Object validatePower(int userId) throws SQLException {
        String sql = "SELECT id,username,sex,phone,email,class_id AS classId,user_id AS userId FROM t_student WHERE user_id=?";
        Student student = this.daoUtil.get(Student.class,sql,userId);
        if(student==null){
            //说明是教师对象
            String sql1 = "SELECT t.id,t.username,sex,grade_id AS gradeId,class_id AS classId,phone,email,school_id AS schoolId,power,user_id AS userId" +
                    " FROM t_user u,t_teacher t WHERE u.id=t.user_id " +
                    "AND u.id=?";
            return this.daoUtil.get(Teacher.class,sql1,userId);
        }
        return student;
    }

    /**
     * 显示所有班级
     */
    @Override
    public List<TheClass> showClasses(int schoolId,int gradeId) throws SQLException {
        String sql = "SELECT c.id,classname FROM t_theclass c,t_grade g,t_school sc " +
                "WHERE g.id=c.grade_id AND sc.id=g.school_id AND sc.id=? AND g.id=?";
        Object[] params = {schoolId,gradeId};
        return this.daoUtil.getToList(TheClass.class,sql,params);
    }

    /**
     * 显示所有学校
     */
    @Override
    public List<School> showAllSchool() throws SQLException {
        String sql = "SELECT id,schoolname AS schoolName FROM t_school";
        return this.daoUtil.getToList(School.class,sql);
    }

}
