package com.chekhovska.lawyerclient.model;


import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "сontents")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Contents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    //@JdbcType(IntegerJdbcType.class)
    private Type type;

    @NotBlank(message = "The 'title' cannot be empty")
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 63206)
    private String description;

//    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL)
//    private List<Images> image;
    private String image;
}
