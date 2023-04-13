package com.ymprog.tms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ymprog.tms.entities.Role;
import com.ymprog.tms.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    void deleteById(long id);
    List<User> findAll();

    @Transactional
    default void saveUserWithRole(User user, Role role) {
        user.getRoles().add(role);
        save(user);
    }
}
