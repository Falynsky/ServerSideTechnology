package com.falynsky.tss4.modules;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Lecturers {
    private int id;
    private String name;
    private Collection<Subjects> subjects;

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

        Lecturers lecturers = (Lecturers) o;

        if (id != lecturers.id) return false;
        if (name != null ? !name.equals(lecturers.name) : lecturers.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "lecturer")
    public Collection<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(Collection<Subjects> subjectsById) {
        this.subjects = subjectsById;
    }
}
