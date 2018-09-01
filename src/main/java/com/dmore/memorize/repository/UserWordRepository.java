package com.dmore.memorize.repository;

import com.dmore.memorize.model.UserWord;
import com.dmore.memorize.model.request.UserWordListRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long>, JpaSpecificationExecutor<UserWord> {

    class Specifications {
        private Specifications() {
        }

        public static Specification<UserWord> findUserWords(UserWordListRequest request) {
            return (userWord, query, cb) -> {
                Predicate userPredicate = cb.equal(userWord.get("pk").get("user").get("id"), request.getUserId());
                query.orderBy(cb.desc(userWord.get("createdAt")));
                return userPredicate;
            };
        }
    }
}
