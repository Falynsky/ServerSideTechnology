package com.falynsky.tss4.repositories;

import com.falynsky.tss4.modules.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {
}
