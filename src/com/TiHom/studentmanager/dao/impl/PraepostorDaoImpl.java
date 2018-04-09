package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.PraepostorDao;
import com.TiHom.studentmanager.entity.Grade;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018/4/4 12:40
 *
 * @author TiHom
 */
public class PraepostorDaoImpl implements PraepostorDao {
    private DaoUtil daoUtil = new DaoUtil();

    /**
     * 查看所有年级
     */
    @Override
    public List<Grade> showAllGrades(int schoolId) throws SQLException {
        String sql = "SELECT g.id,g.gradename AS gradeName FROM t_grade g " +
                "WHERE g.school_id=?";
        return this.daoUtil.getToList(Grade.class,sql,schoolId);
    }

    /**
     * 查看年级的所有班级
     */
    @Override
    public List<TheClass> showGradeClasses(int gradeId) throws SQLException {
        String sql = "SELECT c.id,c.classname " +
                "FROM t_theclass c,t_grade g " +
                "WHERE c.grade_id=g.id " +
                "AND g.id=?";
        return this.daoUtil.getToList(TheClass.class,sql,gradeId);
    }

    /**
     * 查看所有班级
     */
    @Override
    public List<TheClass> showAllClasses() {
        return null;
    }

    /**
     * 增加班级
     */
    @Override
    public void addClass(TheClass theClass) throws SQLException {
        String sql = "INSERT INTO t_theclass VALUES(?,?,?,?)";
        Object[] params = {theClass.getId(),theClass.getClassname(),theClass.getHeadteacherId(),theClass.getIngrade()};
        this.daoUtil.update(sql,params);
    }

    /**
     * 删除班级
     */
    @Override
    public void deleteClass(int classId) throws SQLException {
        String sql = "DELETE FROM t_theclass WHERE id=?";
        this.daoUtil.update(sql,classId);
    }

    /**
     * 显示所有老师
     */
    @Override
    public List<Teacher> showAllTeachers() throws SQLException {
        String sql = "SELECT id,username,sex," +
                "grade_id AS gradeId,class_id AS classId,phone,email " +
                "FROM t_teacher";
        return this.daoUtil.getToList(Teacher.class,sql);
    }

    /**
     * 显示本级所有老师
     */
    @Override
    public List<Teacher> showGradeTeacher(int gradeId) throws SQLException {
        String sql = "SELECT id,username,sex," +
                "grade_id AS gradeId,class_id AS classId,phone,email " +
                "FROM t_teacher " +
                "WHERE grade_id=?";
        return this.daoUtil.getToList(Teacher.class,sql,gradeId);
    }

    /**
     * 精确查询老师
     */
    @Override
    public Teacher searchTeacher(int teacherId, String teacherName) throws SQLException {
        String sql = "SELECT id,username,sex,grade_id AS gradeId,class_id AS classId,phone,email " +
                "FROM t_teacher WHERE 1=1 ";
        if(!"0".equals(Integer.toString(teacherId))){
            sql += String.format("AND id=" + teacherId + " ");
        }
        if(!"无".equals(teacherName)){
            sql += String.format("AND username='" + teacherName + "' ");
        }
        return this.daoUtil.get(Teacher.class,sql);
    }

    /**
     * 模糊查询老师
     */
    @Override
    public List<Teacher> SpecialSearchTeacher(int teacherId,String teacherName) throws SQLException {
        String sql = "SELECT id,username,sex,grade_id AS gradeId,class_id AS classId,phone,email FROM t_teacher WHERE 1=1 ";
        List<Object> params = new ArrayList<Object>();
        if(!"0".equals(Integer.toString(teacherId))){
            sql += String.format("AND id like ? ");
            params.add("%"+teacherId+"%");
        }
        if(!"无".equals(teacherName)){
            sql += String.format("AND username like ? ");
            params.add("%"+teacherName+"%");
        }
        return this.daoUtil.getToList(Teacher.class,sql,params.toArray());
    }
}
