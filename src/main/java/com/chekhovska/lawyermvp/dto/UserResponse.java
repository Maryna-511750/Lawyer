package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.User;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
    Long id;
    String firstName;
    String lastName;
    String email;
    String roleName;

    public UserResponse() {

    }

    public UserResponse(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        roleName = user.getRole().getName();
    }

    public static UserResponse fromUser(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setRoleName(user.getRole().toString());
        return userResponse;
    }
}
