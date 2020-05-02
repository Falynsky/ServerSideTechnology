package com.falynsky.tss4.controllers;

import com.falynsky.tss4.modules.DTO.GradesDTO;
import com.falynsky.tss4.modules.DTO.SubjectsDTO;
import com.falynsky.tss4.modules.Grades;
import com.falynsky.tss4.modules.Subjects;
import com.falynsky.tss4.modules.Users;
import com.falynsky.tss4.repositories.GradesRepository;
import com.falynsky.tss4.repositories.LecturersRepository;
import com.falynsky.tss4.repositories.SubjectsRepository;
import com.falynsky.tss4.repositories.UsersRepository;
import com.falynsky.tss4.services.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@Controller
@RequestMapping("/grades")
public class GradesController {

    final GradesRepository gradesRepository;
    final UsersRepository usersRepository;
    final LecturersRepository lecturersRepository;
    final SubjectsRepository subjectsRepository;

    final SubjectService subjectService;

    public GradesController(GradesRepository gradesRepository,
                            UsersRepository usersRepository,
                            LecturersRepository lecturersRepository,
                            SubjectsRepository subjectsRepository, SubjectService subjectService) {
        this.gradesRepository = gradesRepository;
        this.usersRepository = usersRepository;
        this.lecturersRepository = lecturersRepository;
        this.subjectsRepository = subjectsRepository;
        this.subjectService = subjectService;
    }

    @GetMapping("/all")
    public String getAllGrades(Model model) {
        List<Grades> grades = gradesRepository.findAll();
        Map<Integer, List<Object>> gradesMap = new HashMap<>();
        List<Object> gradesObjects = new ArrayList<>();
        for (Grades grade : grades) {
            int subjectId = grade.getSubject().getId();
            int userId = grade.getUser().getId();
            GradesDTO gradeDTO = new GradesDTO(grade.getId(), grade.getGrade(), subjectId, userId);
            Subjects subject = subjectsRepository.getOne(subjectId);
            SubjectsDTO subjectsDTO = subjectService.retrieveBasicObject(subject);
            Users users = usersRepository.getOne(userId);
            gradesObjects.add(gradeDTO);
            gradesObjects.add(subjectsDTO);
        }
        ArrayList list = new ArrayList();
        model.addAttribute("gradesMap", gradesMap);
        return "grades/grades";
    }
}
