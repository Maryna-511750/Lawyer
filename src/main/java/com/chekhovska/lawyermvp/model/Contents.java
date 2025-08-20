package com.chekhovska.lawyermvp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "сontents")
public class Contents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @Column(name = "type")
//    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name = "section_id")
    private Section section;

    @Column(nullable = false, length = 63206)
    private String description;

    @Column(name = "image")
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contents contents = (Contents) o;
        return Objects.equals(id, contents.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Contents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", section=" + section +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}