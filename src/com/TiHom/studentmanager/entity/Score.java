package com.TiHom.studentmanager.entity;

/**
 * 2018/3/29 15:32
 * 学生成绩实体类
 * @author TiHom
 */
public class Score {
    private int id;                 //成绩id
    private int studentId;          //学号
    private double subjectScore;    //课程成绩
    private String subjectName;     //课程名称


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(double subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
