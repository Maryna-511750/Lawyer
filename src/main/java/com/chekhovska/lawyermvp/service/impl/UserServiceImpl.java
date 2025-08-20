package com.chekhovska.lawyermvp.service.impl;

import com.chekhovska.lawyermvp.exception.NullEntityReferenceException;
import com.chekhovska.lawyermvp.model.User;
import com.chekhovska.lawyermvp.repository.UserRepository;
import com.chekhovska.lawyermvp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User readByEmail(String email) {
        return findByEmail(email);
    }

    @Override
    public User create(User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User user) {
        if (user != null) {
            findByEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
        userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public boolean isCurrentUser(Long userId) {
        var userDetail = SecurityContextHolder.getContext();
        Authentication authentication = userDetail.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        System.out.println(principal);
        return Objects.equals(readByEmail(principal.getUsername()).getId(), userId);
    }
//
//    public boolean isCurrentUser(User user) {
//        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return Objects.equals(readByEmail(userDetails.getUsername()).getId(), user.getId());
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByEmail(username);
        return user;
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        return findByEmail(username);
    }
}
