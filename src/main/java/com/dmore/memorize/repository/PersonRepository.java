package com.dmore.memorize.repository;

import com.dmore.memorize.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
