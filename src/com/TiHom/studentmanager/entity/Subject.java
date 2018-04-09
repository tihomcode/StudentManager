package com.TiHom.studentmanager.entity;

/**
 * 2018/3/29 15:34
 * 课程实体类
 * @author TiHom
 */
public class Subject {
    private int id;             //课程编号
    private String subjectName; //课程名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
