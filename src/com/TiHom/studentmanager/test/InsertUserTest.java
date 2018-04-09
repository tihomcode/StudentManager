package com.TiHom.studentmanager.test;


import java.sql.SQLException;

/**
 * 2018/4/7 17:20
 *
 * @author TiHom
 */
public class InsertUserTest {
    public static void main(String[] args) throws SQLException {
        String username = "用户";
        for(int i=1;i<=10;i++){
            String sql = "INSERT INTO t_user(username) VALUES(?)";
        }
    }
}
