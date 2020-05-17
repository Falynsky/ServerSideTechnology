package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.repositories.GradesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GradesService {

    private final GradesRepository gradesRepository;

    public GradesService(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    public GradesDTO retrieveBasicGrade(Grades grade) {
        return new GradesDTO(grade.getId(), grade.getGrade(), grade.getSubject().getId(), grade.getUser().getId());
    }

    public List<GradesDTO> retrieveBasicGrades() {
        List<Grades> gradesList = gradesRepository.findAll();
        List<GradesDTO> gradesDTOList = new ArrayList<>();
        gradesList.forEach(grade -> {
            gradesDTOList.add(new GradesDTO(grade.getId(), grade.getGrade(), grade.getSubject().getId(), grade.getUser().getId()));
        });
        return gradesDTOList;
    }


}
