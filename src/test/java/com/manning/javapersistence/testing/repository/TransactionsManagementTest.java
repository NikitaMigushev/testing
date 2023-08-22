package com.manning.javapersistence.testing.repository;

import com.manning.javapersistence.testing.model.Log;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class TransactionsManagementTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogRepository logRepository;

    @BeforeTransaction
    void beforeTransaction() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new IllegalStateException("BeforeTransaction check: Test failed because a transaction is still active.");
        }
    }

    @RepeatedTest(2)
    void storeRetrieveLogTest() {
        logRepository.save(new Log("@TestLog1"));
        logRepository.save(new Log("@TestLog2"));
        var checkLog = logRepository.findAll();
        assertThat(checkLog.size()).isEqualTo(2);
    }

    @AfterTransaction
    void afterTransaction() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            throw new IllegalStateException("AfterTransactionCheck check: Test failed because a transaction is still active.");

        }
    }
}
