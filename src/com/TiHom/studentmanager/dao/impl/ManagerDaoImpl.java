package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.ManagerDao;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;

/**
 * 2018/4/4 20:46
 *
 * @author TiHom
 */
public class ManagerDaoImpl implements ManagerDao{
    private DaoUtil daoUtil = new DaoUtil();

    /**
     * 添加学校
     */
    @Override
    public void addSchool(int schoolId, String schoolName) throws SQLException {
        String sql = "INSERT INTO t_school VALUES(?,?)";
        Object[] params = {schoolId,schoolName};
        this.daoUtil.update(sql,params);
    }

    /**
     * 换校长
     */
    @Override
    public void editSchoolHead(int newTeacherId,int newPower,int oldTeacherId,int oldPower) throws SQLException {
        String sql = "UPDATE t_teacher SET power=? WHERE id=?";
        this.daoUtil.update(sql,newPower,newTeacherId);
        String sql1 = "UPDATE t_teacher SET power=? WHERE id=?";
        this.daoUtil.update(sql,oldPower,oldTeacherId);
    }

    /**
     * 查找旧校长
     */
    @Override
    public Object searchOld() throws SQLException {
        String sql = "SELECT id FROM t_teacher WHERE power=3";
        return this.daoUtil.get(Object.class,sql);
    }
}
