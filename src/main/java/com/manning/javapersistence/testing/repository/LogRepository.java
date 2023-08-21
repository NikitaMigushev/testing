package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
