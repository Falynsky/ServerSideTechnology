package com.falynsky.tss4.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectsDTO {
    private int id;
    private String name;
    private int lecturerId;
}
