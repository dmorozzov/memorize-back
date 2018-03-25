package com.dmore.memorize.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @SequenceGenerator(name = "person_pkey", sequenceName = "PERSON_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_pkey")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.person", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<PersonWord> words = new HashSet<>();

}
