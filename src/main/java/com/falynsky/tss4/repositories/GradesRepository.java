package com.falynsky.tss4.repositories;

import com.falynsky.tss4.models.Grades;
import com.falynsky.tss4.models.Lecturers;
import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {

    Collection<Grades> findByUserAndSubject(Users userId, Subjects subjectId);

    Grades findFirstByOrderByIdDesc();
}
