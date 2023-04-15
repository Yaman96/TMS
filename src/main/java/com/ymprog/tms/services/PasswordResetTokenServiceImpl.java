package com.ymprog.tms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ymprog.tms.entities.PasswordResetToken;
import com.ymprog.tms.repositories.PasswordResetTokenRepository;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository repository;

    @Override
    public void save(PasswordResetToken token) {
        repository.save(token);
    }

    @Override
    public void delete(PasswordResetToken token) {
        repository.delete(token);
    }
}
