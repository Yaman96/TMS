package com.ymprog.tms.entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class PasswordResetToken {

    private static final int EXPIRATION_IN_MINUTES = 60 * 6; // 6 hours
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String token;
    
    @OneToOne
    private User user;
    
    private LocalDateTime expiryDate;
    
    public PasswordResetToken() {
        this.expiryDate = LocalDateTime.now().plus(EXPIRATION_IN_MINUTES, ChronoUnit.MINUTES);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiryDate);
    }
}
