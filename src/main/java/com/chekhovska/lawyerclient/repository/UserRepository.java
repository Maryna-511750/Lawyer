package com.chekhovska.lawyerclient.repository;

import com.chekhovska.lawyerclient.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
