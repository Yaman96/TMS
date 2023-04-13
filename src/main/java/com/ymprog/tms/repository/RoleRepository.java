package com.ymprog.tms.repository;

import org.springframework.data.repository.CrudRepository;

import com.ymprog.tms.entities.Role;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(String name);
}
