package com.TiHom.studentmanager.dao;

import java.sql.SQLException;

/**
 * 2018/4/4 20:46
 *
 * @author TiHom
 */
public interface ManagerDao {
    /**
     * 添加学校
     */
    void addSchool(int schoolId,String schoolName) throws SQLException;

    /**
     * 换校长
     */
    void editSchoolHead(int newTeacherId,int newPower,int oldTeacherId,int oldPower) throws SQLException;

    /**
     * 查找旧校长
     */
    Object searchOld() throws SQLException;
}
