package com.dmore.memorize.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "words")
public class Word extends TimeAuditEntity {

    @Id
    @SequenceGenerator(name = "words_pkey", sequenceName = "WORDS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "words_pkey")
    private Long id;

    @Column(name = "original", unique = true)
    private String original;

    @Column(name = "description")
    private String description;

    @Column(name = "translation")
    private String translation;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.word", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<UserWord> users = new HashSet<>();
}
