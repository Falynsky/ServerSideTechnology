package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.repositories.GradesRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("/rest/grades")
public class GradesRestController {

    final GradesRepository gradesRepository;


    public GradesRestController(GradesRepository gradesRepository) {
        this.gradesRepository = gradesRepository;
    }

    @GetMapping("/all/DTO")
    public List<GradesDTO> getAllUsersDTOs() {
        List<Grades> grades = gradesRepository.findAll();
        List<GradesDTO> gradesDTOs = new ArrayList<>();
        for (Grades grade : grades) {
            gradesDTOs.add(new GradesDTO(grade.getId(), grade.getGrade(), grade.getSubject().getId(), grade.getUser().getId()));
        }
        return gradesDTOs;
    }
}
