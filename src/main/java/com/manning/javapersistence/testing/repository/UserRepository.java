package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
