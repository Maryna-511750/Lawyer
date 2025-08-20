package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.Section;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SectionResponse {
    String name;
    String type;

    public SectionResponse(Section section) {
        this.name = section.getName();
        this.type = section.getType();
    }

    public static SectionResponse fromSection(Section section) {
        SectionResponse sectionResponse = new SectionResponse();
        sectionResponse.setName(section.getName());
        sectionResponse.setType(section.getType());
        return sectionResponse;
    }
}
