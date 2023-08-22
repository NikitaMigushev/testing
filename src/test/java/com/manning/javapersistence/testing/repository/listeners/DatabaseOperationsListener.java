package com.manning.javapersistence.testing.repository.listeners;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class DatabaseOperationsListener implements TestExecutionListener {
    @Override
    public void beforeTestClass(TestContext testContext) {
        System.out.println("Running from DatabaseOperationsListener class: beforeTestClass, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void afterTestClass(TestContext testContext) {
        System.out.println("Running from DatabaseOperationsListener class: afterTestClass, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.out.println("Running from DatabaseOperationsListener class: beforeTestMethod, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.out.println("Running from DatabaseOperationsListener class: afterTestMethod, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void beforeTestExecution(TestContext testContext) throws Exception {
        System.out.println("Running from DatabaseOperationsListener class: beforeTestExectution, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void afterTestExecution(TestContext testContext) throws Exception {
        System.out.println("Running from DatabaseOperationsListener class: afterTestExecution, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        System.out.println("Running from DatabaseOperationsListener class: prepareTestInstance, transaction active = " +
                TransactionSynchronizationManager.isActualTransactionActive());
    }
}
