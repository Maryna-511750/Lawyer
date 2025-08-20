package com.chekhovska.lawyerclient.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "images")
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String link;

    @ManyToOne
    private Contents content;
}
