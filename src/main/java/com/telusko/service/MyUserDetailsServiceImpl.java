package com.telusko.service;

import com.telusko.model.UserPrincipal;
import com.telusko.model.Users;
import com.telusko.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByUsername(username);
        return new UserPrincipal(user);
    }

    public UserDetails registerUser(Users user) {
        Users savedUser = userRepo.save(user);
        return new UserPrincipal(savedUser);
    }
}
