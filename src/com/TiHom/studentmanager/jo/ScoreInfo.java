package com.TiHom.studentmanager.jo;

/**
 * 2018/4/2 11:54
 * 成绩信息表
 * @author TiHom
 */
public class ScoreInfo {
    private int id;          //学生学号
    private String username;        //学生姓名
    private String subjectName;     //课程名称
    private double subjectScore;    //课程成绩
    private String subjectComment;  //老师评价

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

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(double subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getSubjectComment() {
        return subjectComment;
    }

    public void setSubjectComment(String subjectComment) {
        this.subjectComment = subjectComment;
    }
}
