package com.falynsky.tss4.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Grades {
    private int id;
    private float grade;
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
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grades grades = (Grades) o;
        return id == grades.id &&
                Float.compare(grades.grade, grade) == 0 &&
                Objects.equals(subject, grades.subject) &&
                Objects.equals(user, grades.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grade, subject, user);
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
