package com.falynsky.tss4.repositories;

import com.falynsky.tss4.modules.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
