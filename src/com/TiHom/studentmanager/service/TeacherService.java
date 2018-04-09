package com.TiHom.studentmanager.service;

import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;

import java.sql.SQLException;
import java.util.List;

/**
 * 2018/4/2 15:53
 *
 * @author TiHom
 */
public interface TeacherService {
    /**
     * 查看个人信息
     */
    Teacher showPersonInfo(Teacher teacher) throws SQLException;

    /**
     * 修改个人信息
     */
    void editInfo(Teacher teacher) throws SQLException;

    /**
     * 显示学生信息
     */
    List<StudentInfo> showStudent(Teacher teacher) throws SQLException;

    /**
     * 显示所教班级
     */
    List<TheClass> showClasses(int teacherId) throws SQLException;

    /**
     * 根据班级去查找学生
     */
    List<Student> showClassStudents(TheClass theClass) throws SQLException;

    /**
     * 高级模糊查询学生
     */
    List<StudentInfo> searchStudents(Teacher teacher) throws SQLException;

    /**
     * 添加学生
     */
    void addStudent() throws SQLException;

    /**
     * 显示所有科目信息
     */
    void showSubjects() throws SQLException;

    /**
     * 添加科目
     */
    int addSubject() throws SQLException;

    /**
     * 删除科目
     */
    int deleteSubject() throws SQLException;

    /**
     * 修改科目
     */
    int editSubject() throws SQLException;

    /**
     * 查询学生成绩
     */
    void searchStudentScore(Teacher teacher) throws SQLException;

    /**
     * 增加学生成绩
     */
    int addStudentSubject(Teacher teacher) throws SQLException;

    /**
     * 修改学生成绩
     */
    int editStudentScore(Teacher teacher) throws SQLException;

    /**
     * 模糊查询特定学生成绩
     */
    boolean searchSpecialScore(Teacher teacher) throws SQLException;

    /**
     * 对学生成绩的评价
     */
    int scoreComment(Teacher teacher) throws SQLException;
}
