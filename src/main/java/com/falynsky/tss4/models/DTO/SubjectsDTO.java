package com.falynsky.tss4.models.DTO;

public class SubjectsDTO {
    private int id;
    private String name;
    private int lecturerId;

    public SubjectsDTO() {
    }

    public SubjectsDTO(int id, String name, int lecturerId) {
        this.id = id;
        this.name = name;
        this.lecturerId = lecturerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }
}
