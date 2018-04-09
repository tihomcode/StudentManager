package com.TiHom.studentmanager.dao;

import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.SearchScore;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.Subject;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/2 15:54
 *
 * @author TiHom
 */
public interface TeacherDao {
    /**
     * 查看个人信息
     */
    Teacher showPersonInfo(Teacher teacher) throws SQLException;

    /**
     * 修改教师联系信息
     */
    void editPhoneInfo(String editPhone,int teacherId) throws SQLException;

    /**
     * 修改邮箱信息
     */
    void editEmailInfo(String editEmail,int teacherId) throws SQLException;

    /**
     * 显示学生信息
     */
    List<StudentInfo> showStudent(Teacher teacher) throws SQLException;

    /**
     * 显示所教班级
     */
    List<TheClass> showClasses(int teacherId) throws SQLException;

    /**
     * 根据班级显示学生
     */
    List<Student> showClassStudents(TheClass theClass) throws SQLException;

    /**
     * 根据信息模糊查询
     */
    List<StudentInfo> searchInfo(int teacherId,int studentId,String studentName) throws SQLException;

    /**
     * 显示所有课程
     */
    List<Subject> showSubject() throws SQLException;

    /**
     * 添加课程
     */
    void addSubject(Subject subject) throws SQLException;

    /**
     * 删除课程
     */
    void deleteSubject(int subjectId) throws SQLException;

    /**
     * 修改课程
     */
    void editSubject(String editSubject,int subjectId) throws SQLException;

    /**
     * 查询学生成绩
     */
    List<SearchScore> searchStudentsScore(int teacherId, int subjectId) throws SQLException;

    /**
     * 增加学生成绩
     */
    void addStudentScore(double addScore,int studentId,int subjectId);

    /**
     * 修改学生成绩
     */
    void editStudentScore(double editScore,int studentId,int subjectId) throws SQLException;

    /**
     * 发表评论
     */
    void commentStudent(String comment,int studentId,int subjectChoice) throws SQLException;

    /**
     * 模糊查询单个或多个学生的成绩
     */
    List<ScoreInfo> seniorSearchStudentScore(int teacherId, int studentId, String studentName) throws SQLException;

}

