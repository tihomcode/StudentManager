package com.TiHom.studentmanager.entity;


/**
 * 2018/3/29 13:11
 * 学生实体类
 * @author TiHom
 */
public class Student {
    private int id;             //学号
    private String username;    //学生姓名
    private String sex;         //学生性别
    private String phone;       //学生联系电话
    private String email;       //学生邮箱地址
    private int classId;        //学生所属班级
    private int userId;         //对应的用户id

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
