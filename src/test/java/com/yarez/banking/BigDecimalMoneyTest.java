package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BigDecimalMoneyTest {

    @Test
    void amountsWithCentsAreExact() throws Exception {
        BankAccount account = account(0);

        deposit(account, new BigDecimal("10.10"));
        deposit(account, new BigDecimal("0.20"));

        assertEquals("Current Balance: 10.30\n", captureOutput(account::displayBalance));
    }

    @Test
    void largeValuesDoNotOverflow() throws Exception {
        BankAccount account = account(0);

        deposit(account, new BigDecimal("999999999999999999999999999999.98"));
        deposit(account, new BigDecimal("0.01"));

        assertEquals("Current Balance: 999999999999999999999999999999.99\n",
                captureOutput(account::displayBalance));
    }

    private static void deposit(BankAccount account, BigDecimal amount) throws Exception {
        try {
            Method method = BankAccount.class.getMethod("deposit", BigDecimal.class);
            method.invoke(account, amount);
        } catch (NoSuchMethodException missingBigDecimalApi) {
            throw new AssertionError(
                    "BankAccount.deposit(BigDecimal) is missing; int money cannot represent cents",
                    missingBigDecimalApi
            );
        }
    }

    private static BankAccount account(int balance) {
        return new BankAccount(
                10000001L,
                new Customer(233785885L, "John", 35, "john@example.com", true),
                balance,
                true
        );
    }

    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            action.run();
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }
}
