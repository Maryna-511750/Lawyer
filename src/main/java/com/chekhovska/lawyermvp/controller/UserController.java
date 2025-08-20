package com.chekhovska.lawyermvp.controller;

import com.chekhovska.lawyermvp.dto.CollaboratorResponse;
import com.chekhovska.lawyermvp.dto.UserRequest;
import com.chekhovska.lawyermvp.dto.UserResponse;
import com.chekhovska.lawyermvp.exception.ResponseStatusException;
import com.chekhovska.lawyermvp.model.User;
import com.chekhovska.lawyermvp.service.RoleService;
import com.chekhovska.lawyermvp.service.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService){
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CollaboratorResponse> getUserById(@PathVariable Long userId) {
        User user = userService.readById(userId);
//        if(user == null){
//            throw new ResponseStatusException("User with id:" + userId + "does not exist!");
//        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new CollaboratorResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userRequest.toUser(roleService.readById(2));
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(user));
    }

    //@PreAuthorize("(hasRole('ADMIN') or @userServiceImpl.isCurrentUser(#userId))")
    @PreAuthorize("#userId == principal.id or hasRole('ROLE_ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        User userForEmail = userService.readById(userId);
        User user = userRequest.toUser(roleService.readById(2));
        user.setId(userId);
        user.setEmail(userForEmail.getEmail());
        userService.update(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(user));
    }

//    @PreAuthorize("(hasRole('ADMIN') or @userServiceImpl.isCurrentUser(#userId))")
    @PreAuthorize("#userId == principal.id or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId){
        userService.delete(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
