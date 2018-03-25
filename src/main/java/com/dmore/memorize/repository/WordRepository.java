package com.dmore.memorize.repository;

import com.dmore.memorize.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface WordRepository extends JpaRepository<Word, Long>, JpaSpecificationExecutor {

    Optional<Word> findByOriginal(String original);

}
