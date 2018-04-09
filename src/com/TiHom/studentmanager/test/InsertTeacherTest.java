package com.TiHom.studentmanager.test;


import java.sql.SQLException;

/**
 * 2018/4/7 17:17
 *
 * @author TiHom
 */
public class InsertTeacherTest {
    public static void main(String[] args) throws SQLException {
        int id = 170001;
        String username = "老师";
        int userId = 17;
        for(int i=0;i<10;i++,id=id+1,userId+=1){
            String sql = "INSERT INTO t_teacher(id,username,userId) VALUES(?,?,?)";
        }
    }
}
