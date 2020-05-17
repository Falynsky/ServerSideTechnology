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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

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

    @GetMapping("/grades")
    public String getAllGrades(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int currentUserId = -1;
        if (principal instanceof UserDetails) {
            currentUserId = ((Users) (principal)).getId();
        }
        List<Grades> grades = gradesRepository.findByUserId(currentUserId);
        Map<Integer, List<Object>> gradesMap = new HashMap<>();

        grades.forEach(grade -> {
            List<Object> gradesObjects = getGradeData(grade);
            gradesMap.put(grade.getId(), gradesObjects);
        });
        model.addAttribute("gradesMap", gradesMap);
        model.addAttribute("newGrade", new GradesDTO());
        model.addAttribute("newSubject", new SubjectsDTO());
        model.addAttribute("newUser", new UsersDTO());
        String username;
        if (principal instanceof Users) {
            username = ((Users) principal).getUsername();
        } else {
            username = "XXXXXXXXXXXXXXXX";
        }
        model.addAttribute("user", username);
        model.addAttribute("userId", currentUserId);
        return "grades/grades";
    }

    private List<Object> getGradeData(Grades gradeObj) {
        float grade = gradeObj.getGrade();
        Subjects subject = gradeObj.getSubject();
        String subjectName = subject.getName();
        Users user = gradeObj.getUser();
        String login = user.getUsername();
        return Arrays.asList(grade, subjectName, login);
    }

    @PostMapping("/add-grade")
    public String addGrade(@ModelAttribute SubjectsDTO newSubject, @ModelAttribute GradesDTO newGrade) {

        float grade = newGrade.getGrade();
        String subjectName = newSubject.getName();

        if (!subjectName.isEmpty() && grade >= 2.0 && grade <= 5.0) {
            Grades newGradeObj = new Grades();
            newGradeObj.setGrade(grade);
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            newGradeObj.setUser((Users) (principal));

            Optional<Subjects> optionalSubject = subjectsRepository.findByName(subjectName.toUpperCase());
            Subjects subject = optionalSubject.orElseThrow(() -> new NoSuchElementException("There's no subject with name: " + subjectName.toUpperCase()));
            newGradeObj.setSubject(subject);

            int newId = getIdForNewGrade();
            newGradeObj.setId(newId);

            gradesRepository.save(newGradeObj);
        }
        return "redirect:/grades";
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