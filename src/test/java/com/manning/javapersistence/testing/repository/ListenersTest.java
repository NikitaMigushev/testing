package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.AppUser;
import com.manning.javapersistence.testing.repository.listeners.DatabaseOperationsListener;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.TestExecutionListeners;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestExecutionListeners(value = {DatabaseOperationsListener.class}, mergeMode =
TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS)
public class ListenersTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @Test
    void storeUpdateRetrieve() {
        TestContextManager testContextManager = new TestContextManager(getClass());
        System.out.println(
                "testContextManager.getTestExecutionListeners().size() = "
                        + testContextManager.getTestExecutionListeners().size());
        List<AppUser> users = List.of(new AppUser("Test1"), new AppUser("Test2"), new AppUser("Test3"));
        userRepository.saveAll(users);
        assertThat(users.size()).isEqualTo(3);
    }

    @AfterEach
    void afterEach() {
        System.out.println("@AfterEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("@AfterAll");
    }
}
