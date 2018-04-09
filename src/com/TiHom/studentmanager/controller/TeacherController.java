package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.jo.StudentInfo;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;
import com.TiHom.studentmanager.service.TeacherService;
import com.TiHom.studentmanager.utils.LogUtil;

import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/4/2 15:50
 * 教师控制层
 * @author TiHom
 */
public class TeacherController {
    private TeacherService teacherService  = BeanFactory.getInstance("teacherService",TeacherService.class);

    Scanner scanner = new Scanner(System.in);

    /**
     * 查看个人信息
     */
    public void showPerosnInfo(Teacher teacher) throws SQLException {
        //显示教师个人信息
        Teacher teacherInfo = teacherService.showPersonInfo(teacher);

        System.out.println("----------------教师个人信息---------------");
        System.out.println("教师编号："+teacherInfo.getId());
        System.out.println("教师姓名："+teacherInfo.getUsername());
        System.out.println("教师性别："+teacherInfo.getSex());
        System.out.println("教师联系方式："+teacherInfo.getPhone());
        System.out.println("教师邮箱地址："+teacherInfo.getEmail());
        System.out.println("-------------------------------------------");
        System.out.println("1.修改个人信息\n2.返回");

        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //修改个人信息
                teacherService.editInfo(teacher);
                break;
            case 2:
                //返回主页
                break;
            default:
                System.out.println("既然要乱选那就返回主页吧！");
                //返回主页
                break;
        }
    }

    /**
     * 查看所有学生信息
     * 增加学生、查询学生
     * 模糊查询
     */
    public void showStudentInfo(Teacher teacher) throws SQLException {
        List<StudentInfo> studentInfos = teacherService.showStudent(teacher);
        //遍历学生信息
        System.out.println("---------------------学生信息----------------------------");
        System.out.println("学号      姓名    性别    联系电话      邮箱地址       班级");
        for (StudentInfo studentInfo:studentInfos){
            System.out.println(studentInfo.getId()+"   "+
                    studentInfo.getUsername()+"   "+
                    studentInfo.getSex()+"   "+
                    studentInfo.getPhone()+"   "+
                    studentInfo.getEmail()+"   "+
                    studentInfo.getClassname()
            );
        }
        //遍历结束
        int exit = 0;
        do {
            System.out.println("---------------------------------------------------------");
            System.out.println("1.查询学生");
            System.out.println("2.增加学生");
            System.out.println("3.返回");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    List<StudentInfo> studentInfos1 = teacherService.searchStudents(teacher);
                    if(studentInfos1.size()==0){
                        System.out.println("无符合条件的学生！！！");
                        break;
                    }
                    //遍历输出查询结果
                    for (StudentInfo studentInfo:studentInfos1){
                        System.out.println(studentInfo.getId()+"   "+
                                studentInfo.getUsername()+"   "+
                                studentInfo.getSex()+"   "+
                                studentInfo.getPhone()+"   "+
                                studentInfo.getEmail()+"   "+
                                studentInfo.getClassname()
                        );
                    }
                    break;
                case 2:
                    System.out.println("老师增加好像没什么意义！");
                    break;
                default:
                    exit = 1;
                    break;
            }
        }while(exit!=1);
    }

    /**
     * 查看老师对应的班级信息
     */
    public void showClasses(int teacherId) throws SQLException {
        List<TheClass> theClassList = teacherService.showClasses(teacherId);
        if(theClassList.size()==0){
            System.out.println("没有对应的班级！！！");
            return;
        }
        System.out.println("-----------------所教的班级---------------------");
        for (int i=0;i<theClassList.size();i++){
            TheClass theClass = theClassList.get(i);
            System.out.println(i+1+"."+theClass.getClassname());
        }
        System.out.println("1.查看班级的学生\n2.返回");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //查看班级的学生
                System.out.print("输入要查看的班级编号：");
                int classId = scanner.nextInt();
                if(classId>theClassList.size()){
                    System.out.println("没有您要查找的班级!!");
                    break;
                }
                TheClass choiceClass = theClassList.get(classId-1);
                teacherService.showClassStudents(choiceClass);
                for(Student student:choiceClass.getStudentList()){
                    System.out.println(student.getId() +" "+ student.getUsername());
                }
                System.out.println("想看更详细的信息请查看主页的学生信息！！！");
                break;
            case 2:
                //返回上一层
                break;
            default:
                System.out.println("既然乱选那就回去！");
                break;
        }
    }

    /**
     * 查看所有课程
     */
    public void showSubjects(Teacher teacher) throws SQLException {
        System.out.println("--------------------目前所有课程---------------------");
        teacherService.showSubjects();
        System.out.println("----------------------------------------------------");
        System.out.println("1.添加科目");
        System.out.println("2.删除科目");
        System.out.println("3.修改科目");
        System.out.println("4.返回");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //显示所有科目
                teacherService.showSubjects();
                //增加
                int insertClassId = teacherService.addSubject();
                LogUtil.writeToLog(teacher.getUsername()+"老师",
                        "添加了编号为"+insertClassId+"的科目。");
                break;
            case 2:
                //显示所有科目
                teacherService.showSubjects();
                //删除
                int deleteClassId = teacherService.deleteSubject();
                LogUtil.writeToLog(teacher.getUsername()+"老师",
                        "删除了编号为"+deleteClassId+"的科目。");
                break;
            case 3:
                //显示所有科目
                teacherService.showSubjects();
                //修改
                int editClassId = teacherService.editSubject();
                LogUtil.writeToLog(teacher.getUsername()+"老师",
                        "修改了编号为"+editClassId+"的科目。");
                break;
            default:
                //返回
                break;
        }
    }

    /**
     * 查看学生课程成绩
     */
    public void showStudentScore(Teacher teacher) throws SQLException {
        //判断标记
        int exit = 0;
        do {
            System.out.println("-----------学生成绩----------------");
            System.out.println("1.查询学生成绩");
            System.out.println("2.增加学生成绩");
            System.out.println("3.修改学生成绩");
            System.out.println("4.评价学生成绩");
            System.out.println("5.返回");
            System.out.print("选择：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //查询学生成绩
                    teacherService.searchStudentScore(teacher);
                    System.out.println("-------------------------");
                    showStudentScore(teacher);
                    break;
                case 2:
                    //增加学生成绩
                    int insertStudentId = teacherService.addStudentSubject(teacher);
                    if(insertStudentId==0){
                        break;
                    }
                    LogUtil.writeToLog(teacher.getUsername()+"老师",
                            "增加了编号为"+insertStudentId+"的学生的成绩。");
                    break;
                case 3:
                    //修改学生成绩
                    int editStudentId = teacherService.editStudentScore(teacher);
                    if(editStudentId==0){
                        break;
                    }
                    LogUtil.writeToLog(teacher.getUsername()+"老师",
                            "修改了编号为"+editStudentId+"的学生的成绩。");
                    break;
                case 4:
                    //评价学生成绩
                    int commentId = teacherService.scoreComment(teacher);
                    if(commentId==0){
                        break;
                    }
                    LogUtil.writeToLog(teacher.getUsername()+"老师",
                            "评论了编号为"+commentId+"的学生的成绩。");
                default:
                    //判断标记
                    exit = 1;
                    break;
            }
        }while (exit!=1);
    }

    /**
     * 查看校园消息
     */
    public void watchMessage(){
        String pathName = "Message.txt";
        try {
            File fileName = new File(pathName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            while (line!=null){
                line = br.readLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
