package com.TiHom.studentmanager.entity;

/**
 * 2018/3/29 13:10
 * 用户实体类
 * @author TiHom
 */
public class User {
    private int id;             //用户的id
    private String username;    //用户的用户名
    private String password;    //用户的密码

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
