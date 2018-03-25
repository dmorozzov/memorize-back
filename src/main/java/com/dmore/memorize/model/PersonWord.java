package com.dmore.memorize.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "person_word")
@AssociationOverrides({
        @AssociationOverride(name = "pk.person",
                joinColumns = @JoinColumn(name = "person_id")),
        @AssociationOverride(name = "pk.word",
                joinColumns = @JoinColumn(name = "word_id")) })
public class PersonWord implements java.io.Serializable {

    public PersonWord() {
        createDate = LocalDateTime.now();
    }

    @EmbeddedId
    private PersonWordId pk = new PersonWordId();

    @Column(name = "create_date")
    private LocalDateTime createDate;

    public PersonWordId getPk() {
        return pk;
    }

    public void setPk(PersonWordId pk) {
        this.pk = pk;
    }

    @Transient
    public Person getPerson() {
        return getPk().getPerson();
    }

    public void setPerson(Person person) {
        getPk().setPerson(person);
    }

    @Transient
    public Word getWord() {
        return getPk().getWord();
    }

    public void setWord(Word word) {
        getPk().setWord(word);
    }



}