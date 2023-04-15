package com.ymprog.tms.services;

import java.util.List;

import com.ymprog.tms.entities.Role;
import com.ymprog.tms.entities.User;

import jakarta.transaction.Transactional;

public interface UserService {
    
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    void deleteById(long id);
    List<User> findAll();

    @Transactional
    void saveUserWithRole(User user, Role role);
}
