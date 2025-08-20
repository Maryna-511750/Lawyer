package com.chekhovska.lawyermvp.dto;
import com.chekhovska.lawyermvp.model.Contents;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentResponse {
    Long id;
    String name;
    String description;
    String image;
    String section;

    public ContentResponse() {

    }

    public ContentResponse(Contents content) {
        id = content.getId();
        name = content.getName();
        description = content.getDescription();
        image = content.getImage();
        section = content.getSection().getName();
    }

    public static ContentResponse fromContent(Contents content) {
        ContentResponse contentResponse = new ContentResponse();
        contentResponse.setId(content.getId());
        contentResponse.setName(content.getName());
        contentResponse.setDescription(content.getDescription());
        contentResponse.setImage(content.getImage());
        contentResponse.setSection(content.getSection().getName());
        return contentResponse;
    }
}

