package com.TiHom.studentmanager.service.impl;

import com.TiHom.studentmanager.controller.UserController;
import com.TiHom.studentmanager.dao.TeacherDao;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.jo.ScoreInfo;
import com.TiHom.studentmanager.jo.SearchScore;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.*;
import com.TiHom.studentmanager.service.TeacherService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/4/2 15:53
 *
 * @author TiHom
 */
public class TeacherServiceImpl implements TeacherService {
    private TeacherDao teacherDao = BeanFactory.getInstance("teacherDao",TeacherDao.class);
    private UserController userController = BeanFactory.getInstance("userController",UserController.class);

    private Scanner scanner = new Scanner(System.in);

    /**
     * 查看个人信息
     */
    @Override
    public Teacher showPersonInfo(Teacher teacher) throws SQLException {
        return teacherDao.showPersonInfo(teacher);
    }

    /**
     * 修改个人信息
     */
    @Override
    public void editInfo(Teacher teacher) throws SQLException {
        int exit = 0;
        do {
            System.out.println("----------------修改个人信息--------------------");
            System.out.println("1.联系方式");
            System.out.println("2.邮箱地址");
            System.out.println("3.完成修改(其他信息都为固定内容无需修改)");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //联系方式修改
                    System.out.println("联系方式："+teacher.getPhone());
                    System.out.print("进行修改：");
                    String editPhone = scanner.next();
                    //修改信息
                    teacher.setPhone(editPhone);
                    //更新数据库
                    teacherDao.editPhoneInfo(teacher.getPhone(),teacher.getId());
                    System.out.println("修改成功！");
                    break;
                case 2:
                    System.out.println("邮箱地址："+teacher.getEmail());
                    System.out.print("进行修改：");
                    String editEmail = scanner.next();
                    //修改信息
                    teacher.setEmail(editEmail);
                    //更新数据库
                    teacherDao.editEmailInfo(teacher.getEmail(),teacher.getId());
                    System.out.println("修改成功！");
                    break;
                default:
                    exit = 1;
                    break;
            }
        }while (exit!=1);
    }

    /**
     * 显示学生信息
     */
    @Override
    public List<StudentInfo> showStudent(Teacher teacher) throws SQLException {
        //获取学生信息
        return teacherDao.showStudent(teacher);
    }

    /**
     * 显示所教班级
     */
    @Override
    public List<TheClass> showClasses(int teacherId) throws SQLException {
        return teacherDao.showClasses(teacherId);
    }

    /**
     * 根据班级去查看学生
     */
    @Override
    public List<Student> showClassStudents(TheClass theClass) throws SQLException {
        List<Student> students = teacherDao.showClassStudents(theClass);
        theClass.setStudentList(students);
        return theClass.getStudentList();
    }

    /**
     * 高级模糊查询学生
     */
    @Override
    public List<StudentInfo> searchStudents(Teacher teacher) throws SQLException {
        System.out.println("请输入您要查询的信息：(仅允许通过学号、姓名进行查询)");
        System.out.print("学号：(不知道输入0)");
        int studentId = scanner.nextInt();
        System.out.print("姓名：(不知道输入无)");
        String studentName = scanner.next();
        System.out.println("查询结果为：");
        return teacherDao.searchInfo(teacher.getId(),studentId,studentName);
    }

    /**
     * 添加学生
     */
    @Override
    public void addStudent() throws SQLException {
        System.out.println("---------------添加学生---------------");
        Student student = new Student();
        userController.regist(student);
    }

    /**
     * 显示所有科目信息
     */
    @Override
    public void showSubjects() throws SQLException {
        List<Subject> subjects = teacherDao.showSubject();
        for (Subject subject:subjects){
            System.out.println(subject.getId()+"."+subject.getSubjectName());
        }
    }

    /**
     * 添加科目
     */
    @Override
    public int addSubject() throws SQLException {
        System.out.print("输入课程id：");
        int classId = scanner.nextInt();
        System.out.print("课程名称：");
        String subjectName = scanner.next();
        Subject subject = new Subject();
        subject.setId(classId);
        subject.setSubjectName(subjectName);
        teacherDao.addSubject(subject);
        System.out.println("添加成功！");
        return classId;
    }

    /**
     * 删除科目
     */
    @Override
    public int deleteSubject() throws SQLException {
        System.out.print("输入您要删除的科目：(请输入科目编号)");
        int subjectId = scanner.nextInt();
        teacherDao.deleteSubject(subjectId);
        System.out.println("删除成功！");
        return subjectId;
    }

    /**
     * 修改科目
     */
    @Override
    public int editSubject() throws SQLException {
        System.out.print("选择您要修改的科目编号：");
        int subjectId = scanner.nextInt();
        System.out.println("修改：");
        String editSubject = scanner.next();
        teacherDao.editSubject(editSubject,subjectId);
        System.out.println("修改成功！");
        return subjectId;
    }

    /**
     * 查询学生成绩
     */
    @Override
    public void searchStudentScore(Teacher teacher) throws SQLException {
        //先显示科目
        System.out.println("---------所有科目-------------");
        List<Subject> subjects = teacherDao.showSubject();
        for(Subject subject:subjects){
            System.out.println(subject.getId()+"."+subject.getSubjectName());
        }
        //再根据选择的科目进行成绩查询
        System.out.println("选择你要查看的课程成绩");
        int choice = scanner.nextInt();
        List<SearchScore> searchScores = teacherDao.searchStudentsScore(teacher.getId(),choice);
        if(searchScores.size()==0){
            //没有找到学生
            System.out.println("未找到本科目成绩！");
            return;
        }
        for(SearchScore searchScore:searchScores){
            System.out.println(searchScore.getId()+"  "
                    +searchScore.getUsername()+"  "+searchScore.getSubjectScore());
        }
    }

    /**
     * 增加学生成绩
     */
    @Override
    public int addStudentSubject(Teacher teacher) throws SQLException {
        boolean ifFind = searchSpecialScore(teacher);
        if(!ifFind){
            System.out.println("没有找到学生！");
            return 0;
        }
        System.out.print("选择您要增加成绩的学生：");
        int studentChoice = scanner.nextInt();
        System.out.println("选择您要增加的科目编号：");
        List<Subject> subjects = teacherDao.showSubject();
        for (Subject subject:subjects){
            System.out.println(subject.getId()+"."+subject.getSubjectName());
        }
        //输入选择
        int subjectChoice = scanner.nextInt();
        System.out.print("增加的分数为：");
        double addScore = scanner.nextDouble();
        //执行增加方法
        teacherDao.addStudentScore(addScore,studentChoice,subjectChoice);
        return studentChoice;
    }

    /**
     * 修改学生成绩
     */
    @Override
    public int editStudentScore(Teacher teacher) throws SQLException {
        boolean ifFind = searchSpecialScore(teacher);
        if(!ifFind){
            System.out.println("没有找到学生！");
            return 0;
        }
        System.out.print("选择您要修改的学生：(填学生编号)");
        int studentChoice = scanner.nextInt();
        System.out.println("选择您要修改的科目编号：");
        List<Subject> subjects = teacherDao.showSubject();
        for (Subject subject:subjects){
            System.out.println(subject.getId()+"."+subject.getSubjectName());
        }
        int subjectChoice = scanner.nextInt();
        System.out.print("修改后的分数：");
        double editScore = scanner.nextDouble();
        //执行修改方法
        teacherDao.editStudentScore(editScore,studentChoice,subjectChoice);
        return studentChoice;
    }

    /**
     * 模糊查询特定学生成绩
     */
    @Override
    public boolean searchSpecialScore(Teacher teacher) throws SQLException {
        System.out.println("模糊查询：");
        System.out.println("学号：(不知道输入0)");
        int studentId = scanner.nextInt();
        System.out.println("学生姓名：(不知道写无)");
        String studentName = scanner.next();
        List<ScoreInfo> scoreInfoList = teacherDao.seniorSearchStudentScore(teacher.getId(),studentId,studentName);
        if(scoreInfoList.size()==0){
            //返回未找到
            return false;
        }
        for(ScoreInfo scoreInfo:scoreInfoList){
            System.out.println(scoreInfo.getId()+"  "+
                    scoreInfo.getUsername()+"  "+
                    scoreInfo.getSubjectName()+"  "+
                    scoreInfo.getSubjectScore()+"  "+
                    scoreInfo.getSubjectComment()
            );
        }
        //返回找到学生
        return true;
    }

    /**
     * 对学生成绩的评价
     */
    @Override
    public int scoreComment(Teacher teacher) throws SQLException {
        //先模糊查询学生成绩
        boolean ifFind = searchSpecialScore(teacher);
        if(!ifFind){
            System.out.println("没有找到学生！");
            return 0;
        }
        //选择进行评论
        System.out.print("选择您要评价的学生：(填学生编号)");
        int studentChoice = scanner.nextInt();
        System.out.println("选择您要评价的科目：");
        showSubjects();
        int subjectChoice = scanner.nextInt();
        System.out.println("您的评价：");
        String comment = scanner.next();
        teacherDao.commentStudent(comment,studentChoice,subjectChoice);
        System.out.println("评论成功!");
        return studentChoice;
    }
}
