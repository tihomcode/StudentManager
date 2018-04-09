package com.TiHom.studentmanager.view;

import com.TiHom.studentmanager.controller.UserController;
import com.TiHom.studentmanager.factory.BeanFactory;
import com.TiHom.studentmanager.entity.Student;
import com.TiHom.studentmanager.entity.Teacher;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 2018/3/30 14:18
 * 登录注册界面
 * @author TiHom
 */
public class LoginAndRegistView {
    //登录状态的全局变量
    public static Map<String,String> loginMap = new HashMap<String, String>();
    //获取控制层对象
    private UserController userController = BeanFactory.getInstance("userController",UserController.class);
    //输入
    Scanner scanner = new Scanner(System.in);

    /**
     * 登录界面展示
     */
    public void loginView(Map<String,String> map) throws SQLException {
        LoginAndRegistView.loginMap = map;
        System.out.println("欢迎来到TiHom的学生管理系统的登录界面！！！");
        Object object = null;
        do {
            if(userController.ifRemember()){
                //有选择记住登录状态
                for(String username: loginMap.keySet()){
                    String password = loginMap.get(username);
                    object = userController.login(username,password);
                    break;
                }
                break;
            }
            System.out.print("用户名：");
            String username = scanner.next();
            System.out.print("密码：");
            String password = scanner.next();
            //是否记住登录状态
            System.out.print("记住登录状态吗？(y/n)");
            System.out.print("请输入：");
            String ifRemember = scanner.next();
            System.out.println("------------执行操作(如未注册请选择注册)---------------");
            System.out.println("1.前往注册\n2.登录");
            System.out.print("选择:");
            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    registView();
                    break;
                case 2:
                    object = userController.login(username,password);
                    if(object!=null){
                        //证明找到了用户，记住登录状态
                        userController.remeberLogin(username,password,loginMap,ifRemember);
                    }
                    break;
                default:
                    System.out.println("乱输那就去注册吧！");
                    registView();
                    break;
            }
        }while (object==null);
        userController.powerJudge(object);
    }

    /**
     * 注册界面
     */
    public void registView() throws SQLException {
        Student student = new Student();
        userController.regist(student);
    }



}
