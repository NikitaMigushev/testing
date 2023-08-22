package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.AppUser;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class TransactionalTest {
    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("beforeAll, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("beforeEach, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @RepeatedTest(2)
    void storeRetrieve() {
        userRepository.save(new AppUser("User1"));
        List<AppUser> users = userRepository.findAll();
        assertAll(
                () -> assertEquals(1, users.size()),
                () -> assertEquals("User1", users.get(0).getName())
        );
        System.out.println("end of method, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @AfterEach
    void afterEach() {
        System.out.println("afterEach, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("afterAll, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }
}
