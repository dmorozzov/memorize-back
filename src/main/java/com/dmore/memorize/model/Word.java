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
@Table(name = "word")
public class Word implements Serializable {

    @Id
    @SequenceGenerator(name = "word_pkey", sequenceName = "WORD_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_pkey")
    private Long id;

    @Column(name = "original", unique = true)
    private String original;

    @Column(name = "translation")
    private String translation;

    @Column(name = "create_date")
    private LocalDate createDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.word", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<PersonWord> persons = new HashSet<>();
}
