package com.falynsky.tss4.services;

import com.falynsky.tss4.modules.DTO.SubjectsDTO;
import com.falynsky.tss4.modules.Subjects;

import org.springframework.stereotype.Service;


@Service
public class SubjectService {

    public SubjectsDTO retrieveBasicObject(Subjects subject) {
        return new SubjectsDTO(subject.getId(), subject.getName(), subject.getLecturer().getId());
    }


}
