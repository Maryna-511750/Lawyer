package com.chekhovska.lawyerclient.controller;

import com.chekhovska.lawyerclient.dto.UserRequest;
import com.chekhovska.lawyerclient.dto.UserResponse;
import com.chekhovska.lawyerclient.model.User;
import com.chekhovska.lawyerclient.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    // @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    List<UserResponse> getAll() {
        return userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
    //@PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    @PostMapping
    ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        User createdUser = userService.create(user);
        UserResponse userResponse = new UserResponse(createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    // @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    UserResponse read(@PathVariable Long id) {
        return new UserResponse(userService.readById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN') or authentication.principal == #id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    UserResponse update(@PathVariable long id, @RequestBody @Valid UserRequest userRequest) {
        User user = userService.readById(id);
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());

        userService.update(user);
        return new UserResponse(user);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN') or authentication.principal == #id")
    @DeleteMapping("/{id}")
    ResponseEntity<UserResponse> delete(@PathVariable long id) {
        User deletedUser = userService.readById(id);
        UserResponse userResponse = new UserResponse(deletedUser);
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }
}
