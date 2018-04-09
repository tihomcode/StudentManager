package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.StudentDao;
import com.TiHom.studentmanager.jo.HeadTeacherInfo;
import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/1 17:10
 *
 * @author TiHom
 */
public class StudentDaoImpl implements StudentDao {
    //创建工具类对象
    private DaoUtil daoUtil = new DaoUtil();

    /**
     * 显示学生的个人信息
     */
    @Override
    public StudentInfo showPersonInfo(Student student) throws SQLException {
        String sql = "SELECT s.id,s.username,s.sex,s.phone,s.email,classname,gradename AS gradeName,schoolname AS schoolName " +
                "FROM t_student s,t_theclass c,t_grade g,t_school sc " +
                "WHERE s.class_id=c.id " +
                "AND g.id=c.grade_id " +
                "AND sc.id=g.school_id " +
                "AND s.id=?";
        return this.daoUtil.get(StudentInfo.class,sql,student.getId());
    }

    /**
     * 修改学生联系信息
     */
    @Override
    public void editPhoneInfo(String editPhone,int studentId) throws SQLException {
        String sql = "UPDATE t_student SET phone=? WHERE id=?";
        Object[] params = {editPhone,studentId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 修改邮箱信息
     */
    @Override
    public void editEmailInfo(String editEmail,int studentId) throws SQLException {
        String sql = "UPDATE t_student SET email=? WHERE id=?";
        Object[] params = {editEmail,studentId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 显示学生成绩信息
     */
    @Override
    public List<ScoreInfo> showScore(int studentId) throws SQLException {
        String sql = "SELECT s.id,s.username,subjectname AS subjectName,subject_score AS subjectScore " +
                "FROM t_student s,t_score se,t_subject sb " +
                "WHERE s.id=se.student_id " +
                "AND se.subject_id=sb.id " +
                "AND s.id=?";
        //返回这个学生的所有课程信息
        return this.daoUtil.getToList(ScoreInfo.class,sql,studentId);
    }

    /**
     * 根据学号查询班主任
     */
    @Override
    public HeadTeacherInfo queryHeadTeacher(int studentId) throws SQLException {
        String sql = "SELECT t.username AS teacherName,t.phone,t.email FROM t_student s,t_teacher t,t_theclass c " +
                "WHERE s.class_id=c.id " +
                "AND c.headteacher_id=t.id " +
                "AND s.id=?";
        //返回班主任信息
        return this.daoUtil.get(HeadTeacherInfo.class,sql,studentId);
    }

}
