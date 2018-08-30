package com.dmore.memorize.model;

import org.hibernate.Hibernate;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

    public abstract T getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (!Hibernate.getClass(o).equals(Hibernate.getClass(this))) {
            return false;
        }

        if (Objects.isNull(getId())) {
            return false;
        }

        return getId().equals(((BaseEntity)o).getId());
    }

    @Override
    public int hashCode() {
        return Objects.nonNull(getId()) ? getId().hashCode() : 0;
    }
}
