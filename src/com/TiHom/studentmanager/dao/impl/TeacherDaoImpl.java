package com.TiHom.studentmanager.dao.impl;

import com.TiHom.studentmanager.dao.TeacherDao;
import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.SearchScore;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.*;
import com.TiHom.studentmanager.utils.DaoUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 2018/4/2 15:54
 *
 * @author TiHom
 */
public class TeacherDaoImpl implements TeacherDao {
    DaoUtil daoUtil = new DaoUtil();

    /**
     * 查看个人信息
     */
    @Override
    public Teacher showPersonInfo(Teacher teacher) throws SQLException {
        String sql = "SELECT id,username,sex,phone,email FROM t_teacher t WHERE t.id=?";
        return this.daoUtil.get(Teacher.class,sql,teacher.getId());
    }

    /**
     * 修改教师联系信息
     */
    @Override
    public void editPhoneInfo(String editPhone,int teacherId) throws SQLException {
        String sql = "UPDATE t_teacher t SET phone=? WHERE t.id=?";
        Object[] params = {editPhone,teacherId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 修改邮箱信息
     */
    @Override
    public void editEmailInfo(String editEmail,int teacherId) throws SQLException {
        String sql = "UPDATE t_teacher t SET email=? WHERE t.id=?";
        Object[] params = {editEmail,teacherId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 显示学生信息
     */
    @Override
    public List<StudentInfo> showStudent(Teacher teacher) throws SQLException {
        String sql = "SELECT s.id,s.username,s.sex,s.phone,s.email,classname " +
                "FROM t_student s,t_theclass c,t_teacher t,t_csmid cs " +
                "WHERE s.class_id=c.id " +
                "AND t.id=cs.teacher_id " +
                "AND cs.class_id=c.id " +
                "AND t.id=?";
        return this.daoUtil.getToList(StudentInfo.class,sql,teacher.getId());
    }

    /**
     * 显示所教班级
     */
    @Override
    public List<TheClass> showClasses(int teacherId) throws SQLException {
        String sql = "SELECT c.id,c.classname " +
                "FROM t_csmid cs,t_teacher t,t_theclass c " +
                "WHERE cs.class_id=c.id " +
                "AND t.id=cs.teacher_id " +
                "AND t.id=?";
        return this.daoUtil.getToList(TheClass.class,sql,teacherId);
    }

    /**
     * 根据班级显示学生
     */
    @Override
    public List<Student> showClassStudents(TheClass theClass) throws SQLException {
        String sql = "SELECT s.id,s.username FROM t_student s,t_theclass c " +
                "WHERE s.class_id=c.id " +
                "AND c.id=?";
        return this.daoUtil.getToList(Student.class,sql,theClass.getId());
    }

    /**
     * 根据信息模糊查询
     */
    @Override
    public List<StudentInfo> searchInfo(int teacherId,int studentId,String studentName) throws SQLException {
        String sql = "SELECT s.id,s.username,s.sex,s.phone,s.email,classname " +
                "FROM t_student s,t_theclass c,t_teacher t,t_csmid cs " +
                "WHERE s.class_id=c.id " +
                "AND t.id=cs.teacher_id " +
                "AND cs.class_id=c.id " +
                "AND t.id=? ";
        List<Object> params = new ArrayList<Object>();
        params.add(teacherId);
        if(!"0".equals(Integer.toString(studentId))){
            sql += String.format("AND s.id like ? ");
            params.add("%"+studentId+"%");
        }
        if(!"无".equals(studentName)){
            sql += String.format("AND s.username like ? ");
            params.add("%"+studentName+"%");
        }
        return this.daoUtil.getToList(StudentInfo.class,sql,params.toArray());
    }

    /**
     * 显示所有课程
     */
    @Override
    public List<Subject> showSubject() throws SQLException {
        String sql = "SELECT id,subjectname AS subjectName FROM t_subject";
        return this.daoUtil.getToList(Subject.class,sql);
    }

    /**
     * 添加课程
     */
    @Override
    public void addSubject(Subject subject) throws SQLException {
        String sql = "INSERT INTO t_subject VALUES(?,?)";
        Object[] params = {subject.getId(),subject.getSubjectName()};
        this.daoUtil.update(sql,params);
    }

    /**
     * 删除课程
     */
    @Override
    public void deleteSubject(int subjectId) throws SQLException {
        String sql = "DELETE FROM t_subject WHERE id=?";
        this.daoUtil.update(sql,subjectId);
    }

    /**
     * 修改课程
     */
    @Override
    public void editSubject(String editSubject,int subjectId) throws SQLException {
        String sql = "UPDATE t_subject SET subjectname=? WHERE id=?";
        Object[] params = {editSubject,subjectId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 查询学生成绩
     */
    @Override
    public List<SearchScore> searchStudentsScore(int teacherId, int subjectId) throws SQLException {
        String sql = "SELECT s.id,s.username,sc.subject_score AS subjectScore " +
                "FROM t_student s,t_subject sb,t_score sc,t_teacher t,t_theclass c " +
                "WHERE c.id=t.class_id " +
                "AND s.class_id=c.id " +
                "AND s.id=sc.student_id " +
                "AND sb.id=sc.subject_id " +
                "AND t.id=? " +
                "AND sb.id=?";
        Object[] params = {teacherId,subjectId};
        return this.daoUtil.getToList(SearchScore.class,sql,params);
    }

    /**
     * 增加学生成绩
     */
    @Override
    public void addStudentScore(double editScore,int studentId,int subjectId) {

    }


    /**
     * 修改学生成绩
     */
    @Override
    public void editStudentScore(double editScore,int studentId,int subjectId) throws SQLException {
        String sql = "UPDATE t_score sc SET sc.subject_score=? " +
                "WHERE sc.student_id=? AND sc.subject_id=?";
        Object[] params = {editScore,studentId,subjectId};
        this.daoUtil.update(sql,params);
    }

    /**
     * 发表评论
     */
    @Override
    public void commentStudent(String comment,int studentId,int subjectChoice) throws SQLException {
        String sql = "UPDATE t_score SET subject_comment=? WHERE student_id=? AND subject_id=?";
        Object[] params = {comment,studentId,subjectChoice};
        this.daoUtil.update(sql,params);
    }

    /**
     * 模糊查询单个或多个学生的成绩
     */
    @Override
    public List<ScoreInfo> seniorSearchStudentScore(int teacherId, int studentId, String studentName) throws SQLException {
        String sql = "SELECT s.id,s.username,sb.subjectname AS subjectName,sc.subject_score AS subjectScore,sc.subject_comment AS subjectComment " +
                "FROM t_student s,t_subject sb,t_score sc,t_teacher t,t_theclass c " +
                "WHERE c.id=t.class_id " +
                "AND s.class_id=c.id " +
                "AND s.id=sc.student_id " +
                "AND sb.id=sc.subject_id " +
                "AND t.id=? ";
        List<Object> params = new ArrayList<Object>();
        params.add(teacherId);
        if(!"0".equals(Integer.toString(studentId))){
            sql += String.format("AND s.id like ? ");
            params.add("%"+studentId+"%");
        }
        if(!"无".equals(studentName)){
            sql += String.format("AND s.username like ? ");
            params.add("%"+studentName+"%");
        }
        return daoUtil.getToList(ScoreInfo.class,sql,params.toArray());
    }


}
