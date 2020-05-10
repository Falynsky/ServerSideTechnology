package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAndAddUser(Users appUser) {

        String encodePassword = getEncodedPassword(appUser);
        appUser.setPassword(encodePassword);
        int id = getIdForNewUser();
        appUser.setId(id);
        appUser.setRole("ROLE_ADMIN");

        usersRepository.save(appUser);
    }

    private String getEncodedPassword(Users appUser) {
        String password = appUser.getPassword();
        return passwordEncoder.encode(password);
    }

    private int getIdForNewUser() {
        Users lastUser = usersRepository.findFirstByOrderByIdDesc();
        if (lastUser == null) {
            return 1;
        }
        int lastId = lastUser.getId();
        return ++lastId;
    }

    public UsersDTO retrieveBasicObject(Users users) {
        return new UsersDTO(users.getId(), users.getUsername(), users.getPassword(), users.getRole());
    }

    public List<UsersDTO> retrieveBasicUsers() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();
        usersList.forEach(user -> {
            usersDTOList.add(new UsersDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole()));
        });
        return usersDTOList;
    }
}
