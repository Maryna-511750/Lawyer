package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.Contents;
import com.chekhovska.lawyermvp.model.Section;
import com.chekhovska.lawyermvp.model.Type;
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ContentRequest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "image")
    String image;

    @Column(name = "type")
    String type;

    public Contents toContents(Section section) {
//        Types type = Type.valueOf(this.type);
        Contents content = new Contents();
        //content.setId(this.id);
        content.setName(this.name);
        content.setImage(this.image);
        content.setDescription(this.description);
        content.setSection(section);

        return content;
    }
}
