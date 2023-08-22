package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.AppUser;
import com.manning.javapersistence.testing.service.UserService;
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

    @Autowired
    private UserService userService;

    @BeforeAll
    static void beforeAll() {
        System.out.println("===Check Transaction Status Before All===");
        System.out.println("beforeAll, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("===Check Transaction Status Before Each===");
        System.out.println("beforeEach, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @RepeatedTest(2)
    void storeRetrieve() {
        System.out.println("===Start test===");
        List<AppUser> users = List.of(new AppUser("User1"), new AppUser("User2"), new AppUser("User3"));
        userRepository.saveAll(users);
        var checkUsers = userRepository.findAll();
        assertAll(
                () -> assertEquals(3, checkUsers.size()),
                () -> assertEquals("User1", checkUsers.get(0).getName())
        );
        userService.saveTransactionally(new AppUser("UserABC"));
        System.out.println("===Check Transaction Status during Test===");
        System.out.println("end of method, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @AfterEach
    void afterEach() {
        System.out.println("===Check Transaction Status After Each test===");
        System.out.println("afterEach, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @AfterAll
    static void afterAll() {
        System.out.println("===Check Transaction Status After All tests===");
        System.out.println("afterAll, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }
}
