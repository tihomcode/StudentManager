package com.TiHom.studentmanager.entity;


import java.util.List;

/**
 * 2018/3/29 15:13
 * 年级实体类
 * @author TiHom
 */
public class Grade {
    private int id;                     //年级编号
    private String gradeName;           //年级名称
    private String teacherId;           //本级老师编号
    private String schoolId;            //对应学校编号
    private List<TheClass> theClasses;  //本级的班级
    private List<Teacher> teachers;     //本级的老师

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public List<TheClass> getTheClasses() {
        return theClasses;
    }

    public void setTheClasses(List<TheClass> theClasses) {
        this.theClasses = theClasses;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
