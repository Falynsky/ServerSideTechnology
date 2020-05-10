package com.falynsky.tss4.repositories;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String login);

    Users findFirstByOrderByIdDesc();

    @Query("SELECT new com.falynsky.tss4.models.DTO.UsersDTO(u.id, u.username, u.password, u.role) FROM Users u")
    List<UsersDTO> retrieveAppUserAsDTO();


}
