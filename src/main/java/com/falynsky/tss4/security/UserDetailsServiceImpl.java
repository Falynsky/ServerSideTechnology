package com.falynsky.tss4.security;

import com.falynsky.tss4.models.Users;
import com.falynsky.tss4.repositories.UsersRepository;
import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    UsersRepository userRepository;

    public UserDetailsServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> foundedUsername = userRepository.findByUsername(username);
        if (foundedUsername.isPresent())
            return foundedUsername.get();
        else {
            throw new IOException("user with username: " + username + " not found!");
        }
    }
}
