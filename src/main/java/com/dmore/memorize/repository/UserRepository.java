package com.dmore.memorize.repository;

import com.dmore.memorize.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
