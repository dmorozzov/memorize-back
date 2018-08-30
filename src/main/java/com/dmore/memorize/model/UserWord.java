package com.dmore.memorize.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "user_words")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user",
                joinColumns = @JoinColumn(name = "user_id")),
        @AssociationOverride(name = "pk.word",
                joinColumns = @JoinColumn(name = "word_id")) })
public class UserWord implements java.io.Serializable {

    public UserWord() {
        createdAt = LocalDateTime.now();
    }

    @EmbeddedId
    private UserWordId pk = new UserWordId();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public UserWordId getPk() {
        return pk;
    }

    public void setPk(UserWordId pk) {
        this.pk = pk;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Word getWord() {
        return getPk().getWord();
    }

    public void setWord(Word word) {
        getPk().setWord(word);
    }
}