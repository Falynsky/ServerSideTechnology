package com.falynsky.tss4.controllers.rest;

import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/subjects/DTO")
public class SubjectsRestController {

    private final SubjectService subjectService;

    public SubjectsRestController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SubjectsDTO>> getAllSubjectsDTO() {
        return subjectService.retrieveBasicSubjects();
    }

    @GetMapping("/{name}")
    public ResponseEntity<SubjectsDTO> getSubjectDTOByName(@PathVariable String name) {
        return subjectService.retrieveBasicObjectByName(name);
    }

}
