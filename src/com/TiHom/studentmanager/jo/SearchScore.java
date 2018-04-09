package com.TiHom.studentmanager.jo;

/**
 * 2018/4/3 22:53
 *
 * @author TiHom
 */
public class SearchScore {
    private int id;      //学号
    private String username; //学生姓名
    private double subjectScore;       //学生本课程的成绩

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

    public double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(double subjectScore) {
        this.subjectScore = subjectScore;
    }
}
