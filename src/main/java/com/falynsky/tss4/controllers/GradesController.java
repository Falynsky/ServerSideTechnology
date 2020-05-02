package com.falynsky.tss4.controllers;

import com.falynsky.tss4.modules.DTO.SubjectsDTO;
import com.falynsky.tss4.modules.DTO.UsersDTO;
import com.falynsky.tss4.modules.Grades;
import com.falynsky.tss4.modules.Subjects;
import com.falynsky.tss4.modules.Users;
import com.falynsky.tss4.repositories.GradesRepository;
import com.falynsky.tss4.repositories.LecturersRepository;
import com.falynsky.tss4.repositories.SubjectsRepository;
import com.falynsky.tss4.repositories.UsersRepository;
import com.falynsky.tss4.services.SubjectService;
import com.falynsky.tss4.services.UserService;
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
    final UserService userService;

    public GradesController(GradesRepository gradesRepository,
                            UsersRepository usersRepository,
                            LecturersRepository lecturersRepository,
                            SubjectsRepository subjectsRepository, SubjectService subjectService, UserService userService) {
        this.gradesRepository = gradesRepository;
        this.usersRepository = usersRepository;
        this.lecturersRepository = lecturersRepository;
        this.subjectsRepository = subjectsRepository;
        this.subjectService = subjectService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getAllGrades(Model model) {
        List<Grades> grades = gradesRepository.findAll();
        Map<Integer, List<Object>> gradesMap = new HashMap<>();

        grades.forEach(grade -> {
            List<Object> gradesObjects = getGradeData(grade);
            gradesMap.put(grade.getId(), gradesObjects);
        });

        model.addAttribute("gradesMap", gradesMap);
        return "grades/grades";
    }

    private List<Object> getGradeData(Grades gradeObj) {
        float grade = gradeObj.getGrade();

        Subjects subject = gradeObj.getSubject();
        String subjectName = subject.getName();


        Users user = gradeObj.getUser();
        String login = user.getLogin();

        return Arrays.asList(grade, subjectName, login);
    }
}
