package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.repositories.SubjectsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectsRepository subjectsRepository;

    public SubjectService(SubjectsRepository subjectsRepository) {
        this.subjectsRepository = subjectsRepository;
    }

    public SubjectsDTO retrieveBasicObject(Subjects subject) {
        return new SubjectsDTO(subject.getId(), subject.getName(), subject.getLecturer().getId());
    }

    public ResponseEntity<List<SubjectsDTO>> retrieveBasicSubjects() {
        List<Subjects> subjectList = subjectsRepository.findAll();
        List<SubjectsDTO> subjectsDTOList = new ArrayList<>();
        subjectList.forEach(subject -> {
            subjectsDTOList.add(retrieveBasicObject(subject));
        });
        return new ResponseEntity(subjectsDTOList, HttpStatus.OK);
    }

    public ResponseEntity<SubjectsDTO> retrieveBasicObjectByName(String name) {

        Optional<Subjects> subjectOptional = subjectsRepository.findByName(name);
        Subjects subject = null;
        if (subjectOptional.isPresent()) {
            subject = subjectOptional.get();
        }
        if (subject == null) {
            return new ResponseEntity("No subject with name: " + name, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(retrieveBasicObject(subject), HttpStatus.OK);
    }
}
