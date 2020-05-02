package com.falynsky.tss4.modules.DTO;

public class GradesDTO {
    private int id;
    private float grade;
    private int subjectId;
    private int userId;

    public GradesDTO() {
    }

    public GradesDTO(int id, float grade, int subjectId, int userId) {
        this.id = id;
        this.grade = grade;
        this.subjectId = subjectId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
