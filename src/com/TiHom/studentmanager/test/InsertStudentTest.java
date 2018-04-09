package com.TiHom.studentmanager.test;


import java.sql.SQLException;

/**
 * 2018/4/7 17:04
 *
 * @author TiHom
 */
public class InsertStudentTest{
    public static void main(String[] args) throws SQLException {
        int id = 170001;
        String username = "学生";
        int userId = 7;
        for(int i=0;i<10;i++,id=id+1,userId+=1){
            String sql = "INSERT INTO t_student(id,username,userId) VALUES(?,?,?)";

        }
    }
}
