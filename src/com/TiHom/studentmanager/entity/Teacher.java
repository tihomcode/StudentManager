package com.TiHom.studentmanager.entity;

/**
 * 2018/3/29 13:11
 * 教师实体类
 * @author TiHom
 */
public class Teacher {
    private int id;                 //老师编号
    private String username;        //老师姓名
    private String sex;             //老师性别
    private int gradeId;           //老师所属年级
    private int classId;         //老师所属班级
    private String phone;           //老师联系电话
    private String email;           //老师邮箱地址
    private int schoolId;         //老师所属学校,默认为0
    private int power;            //老师的权限
    private int userId;             //对应的用户id

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

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
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

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
