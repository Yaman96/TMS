package com.ymprog.tms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ymprog.tms.entities.PasswordResetToken;

public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken,Long> {
}
