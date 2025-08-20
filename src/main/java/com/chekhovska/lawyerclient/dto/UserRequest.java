package com.chekhovska.lawyerclient.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequest {

    String email;

    String firstName;

    String lastName;

    String password;

    String role;
    public UserRequest() {
    }
    public UserRequest(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}