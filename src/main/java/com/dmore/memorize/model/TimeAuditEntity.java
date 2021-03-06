package com.dmore.memorize.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
abstract class TimeAuditEntity extends BaseEntity {

        @CreatedDate
        @Column(name = "created_at", nullable = false, updatable = false)
        private Instant createdAt;

        @LastModifiedDate
        @Column(name = "updated_at", nullable = false)
        private Instant updatedAt;
}
