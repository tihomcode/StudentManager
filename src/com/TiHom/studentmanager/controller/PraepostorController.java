package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.entity.TheClass;
import com.TiHom.studentmanager.service.PraepostorService;
import com.TiHom.studentmanager.utils.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/4/4 12:31
 * 级长控制层
 * @author TiHom
 */
public class PraepostorController {
    //创建service层对象
    private PraepostorService praepostorService = BeanFactory.getInstance("praepostorService",PraepostorService.class);
    Scanner scanner = new Scanner(System.in);

    /**
     *查看不同年级的所有班级
     */
    public void showAllClasses(Teacher teacher) throws SQLException {
        System.out.println("------------------查看班级--------------------");
        //先遍历本学校年级
        praepostorService.showAllGrades(teacher.getSchoolId());
        System.out.print("输入您要查看的年级编号：");
        int gradeId = scanner.nextInt();
        //遍历班级
        List<TheClass> theClassList = praepostorService.showGradeClasses(gradeId);
        for(TheClass theClass:theClassList){
            System.out.println(theClass.getId()+"."+theClass.getClassname());
        }
        System.out.println("1.增加班级");
        System.out.println("2.删除班级");
        System.out.println("3.返回");
        System.out.println("输入选择：");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                //增加班级
                int insertId = praepostorService.addClass(gradeId);
                System.out.println("增加成功！");
                LogUtil.writeToLog(teacher.getUsername()+"级长",
                        "增加了一个编号为"+insertId+"的班级。");
                break;
            case 2:
                //删除班级
                int deleteId = praepostorService.deleteClass(gradeId);
                System.out.println("删除成功！");
                LogUtil.writeToLog(teacher.getUsername()+"级长",
                        "删除了一个编号为"+deleteId+"的班级。");
                break;
            default:
                //返回
                break;
        }
    }

    /**
     * 查看所有老师
     */
    public void showAllTeachers() throws SQLException {
        System.out.println("----------所有老师-----------");
        System.out.println("教师编号     教师名称     性别    所属年级    所属班级    电话      邮箱");
        List<Teacher> teacherList = praepostorService.showAllTeachers();
        for(Teacher teacher:teacherList){
            System.out.println(teacher.getId()+"  "+
                    teacher.getUsername()+"  "+
                    teacher.getSex()+"  "+
                    teacher.getGradeId()+"  "+
                    teacher.getClassId()+"  "+
                    teacher.getPhone()+"  "+
                    teacher.getEmail());
        }
        //遍历完成
        search();
    }

    /**
     * 查看本年级老师
     */
    public void showGradeTeacher(int gradeId) throws SQLException {
        System.out.println("-----本年级老师------");
        System.out.println("教师编号     教师名称     性别    所属班级    电话      邮箱");
        List<Teacher> gradeTeacher = praepostorService.showGradeTeacher(gradeId);
        for(Teacher teacher:gradeTeacher){
            System.out.println(teacher.getId()+"      "+
                    teacher.getUsername()+"     "+
                    teacher.getSex()+"        "+
                    teacher.getClassId()+"  "+
                    teacher.getPhone()+"  "+
                    teacher.getEmail());
        }
        //遍历完成
        search();
    }

    /**
     *
     */
    public void search() throws SQLException {
        int exit = 0;
        do{
            System.out.println("查询老师：");
            System.out.println("1.精确查询");
            System.out.println("2.模糊查询");
            System.out.println("3.返回");
            System.out.println("请输入：");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    //精确查询
                    Teacher teacher = praepostorService.searchTeacher();
                    if(teacher==null){
                        System.out.println("没有找到这位老师！");
                        break;
                    }
                    System.out.println("-----查找到的老师信息------");
                    System.out.println(teacher.getId()+"  "+
                            teacher.getUsername()+"  "+
                            teacher.getSex()+"  "+
                            teacher.getGradeId()+"  "+
                            teacher.getClassId()+"  "+
                            teacher.getPhone()+"  "+
                            teacher.getEmail());
                    break;
                case 2:
                    //模糊查询
                    List<Teacher> teachers = praepostorService.specialSearchTeacher();
                    if(teachers.size()==0){
                        System.out.println("没有找到！");
                        break;
                    }
                    System.out.println("-----符合条件的老师------");
                    for(Teacher searchTeacher:teachers){
                        System.out.println(searchTeacher.getId()+"  "+
                                searchTeacher.getUsername()+"  "+
                                searchTeacher.getSex()+"  "+
                                searchTeacher.getGradeId()+"  "+
                                searchTeacher.getClassId()+"  "+
                                searchTeacher.getPhone()+"  "+
                                searchTeacher.getEmail());
                    }
                    break;
                default:
                    //返回
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
