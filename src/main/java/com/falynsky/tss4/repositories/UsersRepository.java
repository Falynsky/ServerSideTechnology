package com.falynsky.tss4.repositories;

import com.falynsky.tss4.models.Subjects;
import com.falynsky.tss4.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByLogin(String login);

    Users findFirstByOrderByIdDesc();
}
