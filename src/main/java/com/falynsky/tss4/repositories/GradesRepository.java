package com.falynsky.tss4.repositories;

import com.falynsky.tss4.models.Grades;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GradesRepository extends JpaRepository<Grades, Integer> {

    List<Grades> findByUserId(int userId);

    Grades findFirstByOrderByIdDesc();
}
