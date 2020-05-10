package com.falynsky.tss4.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradesDTO {
    private int id;
    private float grade;
    private int subjectId;
    private int userId;

}
