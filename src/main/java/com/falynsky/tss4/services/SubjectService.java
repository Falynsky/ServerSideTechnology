package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.models.Subjects;

import org.springframework.stereotype.Service;


@Service
public class SubjectService {

    public SubjectsDTO retrieveBasicObject(Subjects subject) {
        return new SubjectsDTO(subject.getId(), subject.getName(), subject.getLecturer().getId());
    }


}
