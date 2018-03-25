package com.dmore.memorize.repository;

import com.dmore.memorize.model.PersonWord;
import com.dmore.memorize.model.request.PersonWordListRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Predicate;

public interface PersonWordRepository extends JpaRepository<PersonWord, Long>, JpaSpecificationExecutor<PersonWord> {

    class Specifications {
        private Specifications() {
        }

        public static Specification<PersonWord> findPersonWords(PersonWordListRequest request) {
            return (personWord, query, cb) -> {
                Predicate personPredicate = cb.equal(personWord.get("pk").get("person").get("id"), request.getPersonId());
                return personPredicate;
            };
        }
    }
}
