package com.falynsky.tss4.modules.DTO;

public class LecturersDTO {
    private int id;
    private String name;

    public LecturersDTO() {
    }

    public LecturersDTO(int id, String name) {
        this.id = id;
        this.name = name;
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

}
