package com.ymprog.tms.services;

import com.ymprog.tms.entities.Role;

public interface RoleService {
    void save(Role role);
    Role findRoleByName(String name);
}
