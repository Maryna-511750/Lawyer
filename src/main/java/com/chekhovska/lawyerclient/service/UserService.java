package com.chekhovska.lawyerclient.service;


import com.chekhovska.lawyerclient.dto.UserResponse;
import com.chekhovska.lawyerclient.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    List<User> getAll();

    Optional<UserResponse> findByEmail(String email);
}
