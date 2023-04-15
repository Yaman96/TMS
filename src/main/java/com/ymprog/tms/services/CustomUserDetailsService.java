package com.ymprog.tms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ymprog.tms.entities.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        UserDetails userDetails = new CustomUserDetails(user);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return userDetails;
        // return new org.springframework.security.core.userdetails.User(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }
    
}
