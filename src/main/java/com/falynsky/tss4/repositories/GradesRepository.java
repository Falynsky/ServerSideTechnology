package com.falynsky.tss4.repositories;

import com.falynsky.tss4.modules.Grades;
import com.falynsky.tss4.modules.Subjects;
import com.falynsky.tss4.modules.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {

    Collection<Grades> findByUserAndSubject(Users userId, Subjects subjectId);


}
