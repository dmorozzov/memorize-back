package com.dmore.memorize.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
class UserWordId implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id", foreignKey = @ForeignKey(name = "fk_pw_word"))
    private Word word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_pw_user"))
    private User user;

    UserWordId(Word word, User user) {
        this.setUser(user);
        setWord(word);
    }
}