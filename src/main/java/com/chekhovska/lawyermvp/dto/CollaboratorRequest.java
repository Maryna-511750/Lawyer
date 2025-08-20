package com.chekhovska.lawyermvp.dto;

//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Pattern;
import lombok.Value;


@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CollaboratorRequest {
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    @Column(name = "email", nullable = false, unique = true)
    String email;
    public CollaboratorRequest(){
        email = "unknown";
    }

    public CollaboratorRequest(String email){
        this.email = email;
    }
}
