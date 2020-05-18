package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.repositories.GradesRepository;
import com.falynsky.tss4.repositories.SubjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GradesService {

    private final GradesRepository gradesRepository;
    private final SubjectService subjectService;
    private final SubjectsRepository subjectsRepository;

    public GradesService(GradesRepository gradesRepository, SubjectService subjectService, SubjectsRepository subjectsRepository) {
        this.gradesRepository = gradesRepository;
        this.subjectService = subjectService;
        this.subjectsRepository = subjectsRepository;
    }

    public GradesDTO retrieveBasicGrade(Grades grade) {
        return new GradesDTO(grade.getId(), grade.getGrade(), grade.getSubject().getId(), grade.getUser().getId());
    }

    public ResponseEntity<List<GradesDTO>> retrieveBasicGrades() {
        List<Grades> gradesList = gradesRepository.findAll();
        List<GradesDTO> gradesDTOList = new ArrayList<>();
        gradesList.forEach(grade -> {
            gradesDTOList.add(retrieveBasicGrade(grade));
        });
        return new ResponseEntity(gradesDTOList, HttpStatus.OK);
    }

    public ResponseEntity<List<GradesDTO>> retrieveBasicGrades(List<Grades> gradesList) {
        List<GradesDTO> gradesDTOList = new ArrayList<>();
        gradesList.forEach(grade -> {
            gradesDTOList.add(retrieveBasicGrade(grade));
        });
        return new ResponseEntity(gradesDTOList, HttpStatus.OK);
    }

    public ResponseEntity<GradesDTO> retrieveBasicObjectBySubjectName(String name) {

        ResponseEntity<SubjectsDTO> subjectsDTOResponseEntity = subjectService.retrieveBasicObjectByName(name);
        if (subjectsDTOResponseEntity.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity("No grades with subject name: " + name, HttpStatus.NOT_FOUND);
        }
        SubjectsDTO subjectsDTO = subjectsDTOResponseEntity.getBody();
        Optional<Subjects> subjectOptional = subjectsRepository.findByName(subjectsDTO.getName());
        if (!subjectOptional.isPresent()) {
            return new ResponseEntity("No grades with subject name: " + name, HttpStatus.NOT_FOUND);
        }
        Subjects subjectEntity = subjectOptional.get();
        List<Grades> gradesDTOOptional = gradesRepository.findBySubject(subjectEntity);
        ResponseEntity<List<GradesDTO>> gradesDTOS = retrieveBasicGrades(gradesDTOOptional);
        if (gradesDTOS.getStatusCode() != HttpStatus.OK) {
            return new ResponseEntity("No grades with subject name: " + name, HttpStatus.NOT_FOUND);
        }
        List<GradesDTO> gradesDTOList = gradesDTOS.getBody();
        return new ResponseEntity(gradesDTOList, HttpStatus.OK);
    }


}
