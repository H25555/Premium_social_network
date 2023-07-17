package com.example.casestudy.repositoy;

import com.example.casestudy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserNameIgnoreCaseOrEmailIgnoreCaseOrPhoneNumber(String userName, String email, String phoneNumber);
    boolean existsByUserNameIgnoreCase(String userName);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByPhoneNumberIgnoreCase(String phoneNumber);

    public User findByUserName(String userName);

}
