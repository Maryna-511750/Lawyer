package com.chekhovska.lawyerclient.service.impl;

import com.chekhovska.lawyerclient.dto.UserResponse;
import com.chekhovska.lawyerclient.exception.NotFoundException;
import com.chekhovska.lawyerclient.exception.NullEntityReferenceException;
import com.chekhovska.lawyerclient.model.User;
import com.chekhovska.lawyerclient.repository.UserRepository;
import com.chekhovska.lawyerclient.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User role) {
        if (role != null) {
            return userRepository.save(role);
        }
        throw new NullEntityReferenceException("User cannot be 'null'");
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User with id " + id + " not found"));
    }

    @Override
    public User update(User role) {
        if (role != null) {
            readById(role.getId());
            return userRepository.save(role);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not Found!");
        }
        return user;
    }
    @Override
    @Transactional
    public Optional<UserResponse> findByEmail(String email) {
        log.info("email {}", email);
        User notDeactivatedByEmail = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("The user does not exist by this email: "));
        log.info("user: {}", notDeactivatedByEmail);
        return Optional.of(modelMapper.map(notDeactivatedByEmail, UserResponse.class));
    }
}
