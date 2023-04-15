package com.ymprog.tms.services;

import com.ymprog.tms.entities.PasswordResetToken;

public interface PasswordResetTokenService {
    void save(PasswordResetToken Token);
    void delete(PasswordResetToken token);
}

