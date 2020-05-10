package com.falynsky.tss4.repositories;

import com.falynsky.tss4.models.Subjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SubjectsRepository extends JpaRepository<Subjects, Integer> {

    Optional<Subjects> findByName(String name);
}
