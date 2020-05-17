package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.services.GradesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/grades/DTO")
public class GradesRestController {

    private final GradesService gradesService;

    public GradesRestController(GradesService gradesService) {
        this.gradesService = gradesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<GradesDTO>> getAllUsersDTOs() {
        return gradesService.retrieveBasicGrades();
    }

    @GetMapping("/{subjectName}")
    public ResponseEntity<GradesDTO> getAllGradesDTOsBySubjectName(@PathVariable String subjectName) {
        return gradesService.retrieveBasicObjectBySubjectName(subjectName);
    }
}
