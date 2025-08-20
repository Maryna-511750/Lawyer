package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.Role;
import com.chekhovska.lawyermvp.model.User;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.Value;


@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {

    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    String firstName;
    @Pattern(regexp = "[A-Z][a-z]+",
            message = "Must start with a capital letter followed by one or more lowercase letters")
    String lastName;
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    String email;
    @Pattern(regexp = "[A-Za-z\\d]{6,}",
            message = "Must be minimum 6 symbols long, using digits and latin letters")
    @Pattern(regexp = ".*\\d.*",
            message = "Must contain at least one digit")
    @Pattern(regexp = ".*[A-Z].*",
            message = "Must contain at least one uppercase letter")
    @Pattern(regexp = ".*[a-z].*",
            message = "Must contain at least one lowercase letter")
    String password;

    public User toUser(Role role) {
        User user = new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(this.password);

        user.setRole(role);
        return user;
    }
}