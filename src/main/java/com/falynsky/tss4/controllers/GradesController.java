package com.falynsky.tss4.controllers;

import com.falynsky.tss4.models.DTO.GradesDTO;
import com.falynsky.tss4.models.DTO.SubjectsDTO;
import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.GradesRepository;
import com.falynsky.tss4.repositories.LecturersRepository;
import com.falynsky.tss4.repositories.SubjectsRepository;
import com.falynsky.tss4.repositories.UsersRepository;
import com.falynsky.tss4.services.SubjectService;
import com.falynsky.tss4.services.UserService;
import com.sun.tracing.dtrace.Attributes;
import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@Controller
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

    @GetMapping("/")
    public String getAllGrades(Model model) {
        List<Grades> grades = gradesRepository.findAll();
        Map<Integer, List<Object>> gradesMap = new HashMap<>();

        grades.forEach(grade -> {
            List<Object> gradesObjects = getGradeData(grade);
            gradesMap.put(grade.getId(), gradesObjects);
        });

        String login2 = "";
        model.addAttribute("gradesMap", gradesMap);
        model.addAttribute("newGrade", new GradesDTO());
        model.addAttribute("newSubject", new SubjectsDTO());
        model.addAttribute("newUser", new UsersDTO());
        model.addAttribute("login2", login2);
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

    @PostMapping("/add-grade")
    public String addGrade(@ModelAttribute SubjectsDTO newSubject, @ModelAttribute GradesDTO newGrade, @ModelAttribute UsersDTO newUser) {

        float grade = newGrade.getGrade();
        String userLogin = newUser.getLogin();
        String subjectName = newSubject.getName();

        if (!subjectName.isEmpty() && !userLogin.isEmpty() && grade >= 2.0 && grade <= 5.0) {
            Grades newGradeObj = new Grades();
            newGradeObj.setGrade(grade);

            Optional<Users> optionalUser = usersRepository.findByLogin(userLogin);
            Users user = optionalUser.orElseThrow(() -> new NoSuchElementException("There's no user with login: " + userLogin));
            newGradeObj.setUser(user);

            Optional<Subjects> optionalSubject = subjectsRepository.findByName(subjectName.toUpperCase());
            Subjects subject = optionalSubject.orElseThrow(() -> new NoSuchElementException("There's no subject with name: " + subjectName.toUpperCase()));
            newGradeObj.setSubject(subject);

            int newId = getIdForNewGrade();
            newGradeObj.setId(newId);

            gradesRepository.save(newGradeObj);
        }
        return "redirect:/";
    }

    private Integer getIdForNewGrade() {
        Grades lastGrade = gradesRepository.findFirstByOrderByIdDesc();
        if (lastGrade == null) {
            return 1;
        }
        int lastId = lastGrade.getId();
        return ++lastId;
    }

}
