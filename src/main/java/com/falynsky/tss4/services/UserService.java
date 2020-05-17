package com.falynsky.tss4.services;

import com.falynsky.tss4.models.DTO.UsersDTO;
import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<UsersDTO>> retrieveBasicUsers() {
        List<Users> usersList = usersRepository.findAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();
        usersList.forEach(user -> {
            usersDTOList.add(retrieveBasicObject(user));
        });
        return new ResponseEntity(usersDTOList, HttpStatus.OK);
    }

    public ResponseEntity<UsersDTO> retrieveBasicObjectByUsername(String username) {

        Optional<Users> userOptional = usersRepository.findByUsername(username);
        Users user = null;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        }
        if (user == null) {
            return new ResponseEntity("No user as: " + username, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(retrieveBasicObject(user), HttpStatus.OK);

    }
}
