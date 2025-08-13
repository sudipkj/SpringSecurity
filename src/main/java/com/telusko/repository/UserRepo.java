package com.telusko.repository;

import com.telusko.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByUsername(String username);

    // Additional query methods can be defined here if needed
}
