package com.ymprog.tms.repositories;

import java.util.HashSet;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ymprog.tms.entities.Role;
import com.ymprog.tms.entities.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    void deleteById(long id);
    List<User> findAll();

    @Transactional
    default void saveUserWithRole(User user, Role role) {
        user.setRoles(new HashSet<>());
        user.getRoles().add(role);
        save(user);
    }
}
