package com.TiHom.studentmanager.controller;

import com.TiHom.studentmanager.dao.ManagerDao;
import com.TiHom.studentmanager.factory.BeanFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 2018/4/4 20:44
 *
 * @author TiHom
 */
public class ManagerController {
    private ManagerDao managerDao = BeanFactory.getInstance("managerDao",ManagerDao.class);

    Scanner scanner = new Scanner(System.in);

    /**
     * 查看校园、日志消息
     */
    public void watchEvething(String pathName){
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

    /**
     * 添加学校
     */
    public void insertSchool() throws SQLException {
        System.out.print("输入你要添加的学校编号：");
        int schoolId = scanner.nextInt();
        System.out.println();
        System.out.print("输入你要添加的学校名称：");
        String schoolName = scanner.next();
        //添加学校
        managerDao.addSchool(schoolId,schoolName);
    }

    /**
     * 换校长
     */
    public void editSchoolHead() throws SQLException {
        int oldSchoolHead = (int) managerDao.searchOld();
        System.out.println("您要让谁当校长：(输入编号)");
        int newSchoolHead = scanner.nextInt();
        //更改权限
        managerDao.editSchoolHead(newSchoolHead,3,oldSchoolHead,2);
    }

}
