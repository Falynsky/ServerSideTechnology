package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.services.GradesService;
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

    private final GradesService gradesService;

    public GradesRestController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/all/DTO")
    public List<GradesDTO> getAllUsersDTOs() {
        return gradesService.retrieveBasicGrades();
    }
}
