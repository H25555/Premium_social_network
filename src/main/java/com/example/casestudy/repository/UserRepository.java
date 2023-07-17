package com.example.casestudy.repository;

import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    public User findByEmailOrUserNameOrPhoneNumber(String email, String userName, String phoneNumber);

}
