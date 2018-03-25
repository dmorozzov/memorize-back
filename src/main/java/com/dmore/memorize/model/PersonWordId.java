package com.dmore.memorize.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
class PersonWordId implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", foreignKey = @ForeignKey(name = "fk_pw_word"))
    private Word word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "fk_pw_person"))
    private Person person;

    public PersonWordId(Word word, Person person) {
        setPerson(person);
        setWord(word);
    }
}