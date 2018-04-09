package com.TiHom.studentmanager.entity;

import java.util.List;

/**
 * 2018/3/29 15:08
 * 班级实体类
 * @author TiHom
 */
public class TheClass {
    private int id;                     //班级编号
    private String classname;           //班级名称
    private int headteacherId;         //班主任
    private int ingrade;                //所属年级
    private List<Student> studentList;  //班级所包含的学生

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getHeadteacherId() {
        return headteacherId;
    }

    public void setHeadteacherId(int headteacherId) {
        this.headteacherId = headteacherId;
    }

    public int getIngrade() {
        return ingrade;
    }

    public void setIngrade(int ingrade) {
        this.ingrade = ingrade;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
