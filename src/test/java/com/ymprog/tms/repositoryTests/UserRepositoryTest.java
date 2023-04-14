package com.ymprog.tms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ymprog.tms.entities.Role;
import com.ymprog.tms.entities.User;
import com.ymprog.tms.repositories.UserRepository;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        userRepository.save(user);

        User foundUser = userRepository.findByUsername("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testFindByEmail() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        userRepository.save(user);

        User foundUser = userRepository.findByEmail("test@example.com");

        assertNotNull(foundUser);
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    public void testFindById() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        User savedUser = userRepository.save(user);

        User foundUser = userRepository.findById(savedUser.getId());

        assertNotNull(foundUser);
        assertEquals(savedUser.getId(), foundUser.getId());
    }

    @Test
    public void testDeleteById() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        User savedUser = userRepository.save(user);

        userRepository.deleteById(savedUser.getId());

        User foundUser = userRepository.findById(savedUser.getId());
        assertNull(foundUser);
    }

    @Test
    public void testFindAll() {
        User user1 = new User();
        user1.setUsername("testuser1");
        user1.setEmail("test1@example.com");

        User user2 = new User();
        user2.setUsername("testuser2");
        user2.setEmail("test2@example.com");

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    public void testSaveUserWithRole() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setName("ROLE_USER");
        roles.add(role);
        user.setRoles(roles);

        userRepository.saveUserWithRole(user, role);

        User foundUser = userRepository.findByUsername("testuser");

        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertTrue(foundUser.getRoles().contains(role));
    }
}
