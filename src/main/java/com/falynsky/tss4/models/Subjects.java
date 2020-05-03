package com.falynsky.tss4.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Subjects {
    private int id;
    private String name;
    private Collection<Grades> grades;
    private Lecturers lecturer;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subjects subjects = (Subjects) o;

        if (id != subjects.id) return false;
        if (name != null ? !name.equals(subjects.name) : subjects.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "subject")
    public Collection<Grades> getGrades() {
        return grades;
    }

    public void setGrades(Collection<Grades> gradesById) {
        this.grades = gradesById;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_id", referencedColumnName = "id", nullable = false)
    public Lecturers getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturers lecturersByLecturerId) {
        this.lecturer = lecturersByLecturerId;
    }
}
