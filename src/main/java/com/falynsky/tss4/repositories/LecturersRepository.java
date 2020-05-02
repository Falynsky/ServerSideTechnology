package com.falynsky.tss4.repositories;

import com.falynsky.tss4.modules.Lecturers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturersRepository extends JpaRepository<Lecturers, Integer> {
}
