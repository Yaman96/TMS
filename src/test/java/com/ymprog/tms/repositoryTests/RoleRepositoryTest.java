package com.ymprog.tms.repositoryTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ymprog.tms.entities.Role;
import com.ymprog.tms.repositories.RoleRepository;

@DataJpaTest
public class RoleRepositoryTest {
    
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByNameTest() {
        Role role = new Role();
        role.setName("ROLE_TEST");
        roleRepository.save(role);

        Role foundRole = roleRepository.findByName("ROLE_TEST");

        assertNotNull(foundRole);
        assertEquals("ROLE_TEST", foundRole.getName());
    }
}
