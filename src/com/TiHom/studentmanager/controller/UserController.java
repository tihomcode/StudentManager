package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.entity.Teacher;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.User;
import com.TiHom.studentmanager.service.UserService;
import com.TiHom.studentmanager.view.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

import static com.TiHom.studentmanager.view.LoginAndRegistView.loginMap;

/**
 * 2018/3/30 14:29
 * 用户控制层
 * @author TiHom
 */
public class UserController {
    //利用Bean工厂创建service
    private UserService userService = BeanFactory.getInstance("userService",UserService.class);
    Scanner scanner = new Scanner(System.in);

    /**
     * 登录功能
     */
    public Object login(String username,String password) throws SQLException {
        //如果返回400表示有误，返回-1表示学生，返回0-3表示老师的不同等级
        return userService.login(username,password);
    }

    /**
     * 注册功能
     */
    public void regist(Student student) throws SQLException {
        //先进行用户名和密码的注册
        User registUser = new User();
        int registUserId = userService.insertUsernameAndPassword(registUser);
        //进行个人信息的输入
        userService.insertStudentInfo(student,registUserId);
        System.out.println("成功注册！");
    }

    /**
     * 记住登录状态
     */
    public void remeberLogin(String username, String password, Map<String, String> loginMap,String choice){
        if("y".equals(choice)){
            //记住登录状态
            loginMap.put(username,password);
        }
    }

    /**
     * 判断是否有登录状态
     */
    public boolean ifRemember() throws SQLException {
        if(loginMap==null){
            return false;
        }
        for(String key:loginMap.keySet()){
            String value = loginMap.get(key);
            if(value!=null && key!=null){
                login(key,value);
                return true;
            }
        }
        return false;
    }

    /**
     * 权限判断
     */
    public void powerJudge(Object object) throws SQLException {
        System.out.println("登录成功");
        if(object instanceof Teacher){
            Teacher teacher = (Teacher) object;
            int power = teacher.getPower();
            if(power==0){
                //普通老师
                TeacherView teacherView = new TeacherView();
                teacherView.indexView(teacher);
            }else if(power==1){
                //班主任
                TeacherView teacherView = new TeacherView();
                teacherView.indexView(teacher);
            }else if(power==2){
                //级长
                PraepostorView praepostorView = new PraepostorView();
                praepostorView.indexView(teacher);
            }else if(power==3){
                //校长
                HeadTeacherView headTeacherView = new HeadTeacherView();
                headTeacherView.indexView(teacher);
            }else {
                //那必须我啊
                ManagerView managerView = new ManagerView();
                managerView.indexView();
            }
        }else if(object instanceof Student){
            Student student = (Student) object;
            //学生
            StudentView studentView = new StudentView();
            //传入学生对象，获取信息
            studentView.indexView(student);
        }
    }
}


