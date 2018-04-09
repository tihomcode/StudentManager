package com.TiHom.studentmanager.entity;

import java.util.List;

/**
 * 2018/3/29 15:27
 * 学校实体类
 * @author TiHom
 */
public class School {
    private int id;             //学校编号
    private String schoolName;  //学校名称
    private List<Grade> grades; //年级列表

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
