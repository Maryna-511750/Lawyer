package com.chekhovska.lawyermvp.dto;

import com.chekhovska.lawyermvp.model.Contents;
import com.chekhovska.lawyermvp.model.Section;
import jakarta.persistence.Column;
import lombok.Value;

@Value
public class SectionRequest {
    @Column(name = "name", nullable = false, unique = true)
    String name;

    @Column(name = "type", unique = true)
    String type;

    @Column(name = "visible")
    Boolean visible;

    public Section toSection() {
        Section section = new Section();
        section.setName(this.name);
        section.setType(this.type);
        section.setVisible(this.visible);
        return section;
    }
}
