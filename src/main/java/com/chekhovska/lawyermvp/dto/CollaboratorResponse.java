package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.User;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Value;

@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CollaboratorResponse {
    String userName;
//    String todoTitle;

    public CollaboratorResponse(User user){ //, ToDo todo
        userName = user.getFullName();
//        todoTitle = todo.getTitle();
    }
}
