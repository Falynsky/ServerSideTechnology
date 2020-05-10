package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.repositories.SubjectsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    private final SubjectsRepository subjectsRepository;

    public SubjectService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    public SubjectsDTO retrieveBasicObject(Subjects subject) {
        return new SubjectsDTO(subject.getId(), subject.getName(), subject.getLecturer().getId());
    }

    public List<SubjectsDTO> retrieveBasicUsers() {
        List<Subjects> subjectList = subjectsRepository.findAll();
        List<SubjectsDTO> subjectsDTOList = new ArrayList<>();
        subjectList.forEach(subject -> {
            subjectsDTOList.add(new SubjectsDTO(subject.getId(), subject.getName(), subject.getLecturer().getId()));
        });
        return subjectsDTOList;
    }


}
