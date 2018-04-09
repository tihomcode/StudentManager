package com.TiHom.studentmanager.service.impl;

import com.TiHom.studentmanager.dao.UserDao;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.*;
import com.TiHom.studentmanager.service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 2018/3/29 13:15
 * 用户业务层接口实现类
 * @author TiHom
 */
public class UserServiceImpl implements UserService{
    //加载dao类
    private UserDao userDao = BeanFactory.getInstance("userDao",UserDao.class);

    /**
     * 用户登录
     */
    @Override
    public Object login(String username, String password) throws SQLException {
        //判断用户密码是否正确
        User loginUser = userDao.findUserByUserNameAndPassword(username, password);
        if(loginUser!=null){
            //证明找到了用户，则登录成功，获取对象
            Object object = userDao.validatePower(loginUser.getId());
            if(object instanceof Teacher){
                Teacher teacher = (Teacher) object;
                return teacher;
            }else if(object instanceof Student){
                Student student = (Student) object;
                return student;
            }
        }
        return null;
    }

    /**
     * 添加用户信息
     */
    @Override
    public int insertUsernameAndPassword(User registUser) throws SQLException {
        int userId = 0;
        Scanner scanner = new Scanner(System.in);
        //需要重新进行为1，下一步为0
        int flag = 1;
        do {
            System.out.println("-------------学生注册界面------------------");
            System.out.println("用户名：");
            String username = scanner.next();
            if(username!=null && !username.trim().isEmpty()){
                //证明找到用户
                flag = userDao.findUserByUserName(username);
                if(flag == 1){
                    //证明找到用户，已被注册过
                    System.out.println("不好意思，您输入的用户名已被他人使用，请重新输入！");
                    continue;
                }
            }
            System.out.println("密码：");
            String password = scanner.next();
            do {
                if(password!=null && !password.trim().isEmpty()){
                    //证明密码合法
                    registUser.setUsername(username);
                    registUser.setPassword(password);
                    //添加用户
                    userId = userDao.addUser(registUser);
                    //跳出所有循环完成用户注册过程
                    flag = 0;
                }else {
                    System.out.println("密码不能为空！");
                    flag = 1;
                }
            }while (flag == 1);
        }while(flag == 1);
        return userId;
    }

    /**
     * 添加学生个人信息
     */
    @Override
    public Student insertStudentInfo(Student student,int registUserId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        //成功表示1，失败表示0
        int flag = 0;
        /* 学生姓名 */
        do {
            System.out.println("----------------欢迎来到学生个人信息注册界面---------------");
            System.out.print("学号：(17xxxxx――专业号(一位)+班级+班内编号)");
            int studentId = scanner.nextInt();
            student.setId(studentId);

            System.out.print("学生姓名：");
            String studentName = scanner.next();
            if (studentName == null || studentName.trim().isEmpty()) {
                //证明为空
                flag = 0;
            }else {
                flag = 1;
                //注入学生姓名属性
                student.setUsername(studentName);
            }
        }while(flag == 0);

        /* 学生性别 */
        System.out.println("学生性别：1.男 2.女 3.你选来试试看");
        int choice1 = scanner.nextInt();
        switch (choice1){
            case 1:
                student.setSex("男");
                break;
            case 2:
                student.setSex("女");
                break;
            default:
                student.setSex("说了别乱选吧");
        }

        /* 学生联系电话 */
        System.out.print("学生联系电话：(没有写无)");
        String phone = scanner.next();
        if(phone.equals("无")){
            phone = null;
        }
        student.setPhone(phone);

        /* 学生邮箱 */
        System.out.print("学生邮箱地址：(没有写无)");
        String email = scanner.next();
        if(email.equals("无")){
            email = null;
        }
        //正则表达式判断合法性(扩展)
        student.setEmail(email);

        /* 学生所属学校 */
        System.out.println("所属学校：");
        List<School> schoolList = userDao.showAllSchool();
        for(School school:schoolList){
            System.out.println(school.getId() + "." + school.getSchoolName());
        }
        int schoolId = scanner.nextInt();

        /* 学生班级 */
        System.out.println("所属班级：");
        System.out.println("1.大一");
        System.out.println("2.大二");
        System.out.println("3.大三");
        System.out.println("4.大四");
        int gradeId = scanner.nextInt();
        List<TheClass> classes = userDao.showClasses(schoolId,gradeId);
        for(int i=0;i<classes.size();i++){
            //获取班级
            TheClass theClass = classes.get(i);
            System.out.print(theClass.getId() + "." + theClass.getClassname() + "   ");
            if(i!=0 && i%5==0){
                //一行6个班级显示
                System.out.println();
            }
            if(i==classes.size()-1){
                System.out.println();
            }
        }
        System.out.println("请输入班级的编号：");
        int classChoice = scanner.nextInt();
        student.setClassId(classChoice);
        userDao.addStudent(student,registUserId);

        return student;
    }
}
