package com.falynsky.tss4.modules;

import javax.persistence.*;

@Entity
public class Grades {
    private int id;
    private Float grade;
    private Subjects subject;
    private Users user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "grade", nullable = true, precision = 0)
    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grades grades = (Grades) o;

        if (id != grades.id) return false;
        if (grade != null ? !grade.equals(grades.grade) : grades.grade != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    public Subjects getSubject() {
        return subject;
    }

    public void setSubject(Subjects subjectsBySubjectId) {
        this.subject = subjectsBySubjectId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public Users getUser() {
        return user;
    }

    public void setUser(Users usersByUserId) {
        this.user = usersByUserId;
    }
}
