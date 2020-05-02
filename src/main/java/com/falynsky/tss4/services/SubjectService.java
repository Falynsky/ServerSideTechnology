package com.falynsky.tss4.services;

import com.falynsky.tss4.modules.DTO.GradesDTO;
import com.falynsky.tss4.modules.DTO.SubjectsDTO;
import com.falynsky.tss4.modules.Grades;
import com.falynsky.tss4.modules.Subjects;
import com.falynsky.tss4.repositories.SubjectsRepository;

import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    SubjectsRepository subjectsRepository;

    public SubjectsDTO retrieveBasicObject(Subjects subject) {
        Collection<Grades> grades = subject.getGrades();
        Map<Grades, GradesDTO> gradesDTOsMap = getBasicGradesCollection(grades, this::getGradeDTO);
        List<GradesDTO> gradesDTOS = new ArrayList<>(gradesDTOsMap.values());
        return new SubjectsDTO(subject.getId(), subject.getName(), gradesDTOS, subject.getLecturer().getId());
    }

    private Map<Grades, GradesDTO> getBasicGradesCollection(Collection<Grades> grades, Function<Grades, GradesDTO> gradesFunction) {
        return grades.stream().collect(Collectors.toMap(Function.identity(), gradesFunction));
    }

    private GradesDTO getGradeDTO(Grades grade) {
        return new GradesDTO(grade.getId(), grade.getGrade(), grade.getSubject().getId(), grade.getUser().getId());
    }


}
