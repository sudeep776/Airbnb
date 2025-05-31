package com.springboot.project.airBnbApp.service.impl;

import com.springboot.project.airBnbApp.entity.User;
import com.springboot.project.airBnbApp.exception.ResourceNotFoundException;
import com.springboot.project.airBnbApp.repository.UserRepository;
import com.springboot.project.airBnbApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }
}
