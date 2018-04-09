package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.HeadTeacherDao;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;

/**
 * 2018/4/4 17:26
 *
 * @author TiHom
 */
public class HeadTeacherDaoImpl implements HeadTeacherDao{
    private DaoUtil daoUtil = new DaoUtil();

    /**
     * 删除学生
     */
    @Override
    public void expelStudent(int studentId) throws SQLException {
        String sql = "DELETE FROM t_student WHERE id=?";
        this.daoUtil.update(sql,studentId);
    }

    /**
     * 删除老师
     */
    @Override
    public void expelTeacher(int teacherId) throws SQLException {
        String sql = "DELETE FROM t_teacher WHERE id=?";
        this.daoUtil.update(sql,teacherId);
    }

    /**
     * 查找老师
     */
    @Override
    public Teacher searchTeacher(int teacherId) throws SQLException {
        String sql = "SELECT id,username,sex,phone,email " +
                "FROM t_teacher WHERE id=?";
        return this.daoUtil.get(Teacher.class,sql,teacherId);
    }

    /**
     * 更改老师权限
     */
    @Override
    public void editPower(int power, int teacherId) throws SQLException {
        String sql = "UPDATE t_teacher SET power=? WHERE id=?";
        Object[] params = {power,teacherId};
        this.daoUtil.update(sql,params);
    }


}
