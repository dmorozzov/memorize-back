package com.dmore.memorize.repository;

import com.dmore.memorize.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNameOrEmail(String username, String email);

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);
}
