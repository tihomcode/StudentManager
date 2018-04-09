package com.TiHom.studentmanager.jo;


/**
 * 2018/4/1 19:27
 * 学生个人信息
 * @author TiHom
 */
public class StudentInfo{
    private int id;       //学生学号
    private String username;     //学生姓名
    private String sex;             //学生性别
    private String phone;           //学生联系方式
    private String email;           //学生邮箱
    private String classname;         //学生所属班级
    private String gradeName;           //学生所属年级
    private String schoolName;      //学生所属学校

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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
