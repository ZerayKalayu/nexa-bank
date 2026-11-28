package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MoneyCharacterizationTest {

    @Test
    void depositKeepsWholeAmountFormatting() {
        BankAccount account = account(10000);

        assertEquals("Deposit successful.\n", captureOutput(() -> account.deposit(2500)));
        assertEquals("Current Balance: 12500\n", captureOutput(account::displayBalance));

        String history = captureOutput(account::displayTransactionHistory);
        assertTrue(history.contains("amount=2500"));
        assertTrue(history.contains("balanceAfter=12500"));
    }

    @Test
    void withdrawalKeepsWholeAmountFormatting() {
        BankAccount account = account(10000);

        assertEquals("Withdrawal successful.\n",
                captureOutput(() -> account.withdraw(3000)));
        assertEquals("Current Balance: 7000\n", captureOutput(account::displayBalance));
    }

    @Test
    void transferRequestKeepsFormatting() {
        // There is no funds-transfer method in this project; only a TRANSFER request.
        BankingRequest request = new BankingRequest(
                1003, "David", "TRANSFER", 3000);

        assertEquals(
                "BankingRequest{requestId=1003, customerName='David', "
                        + "operationType='TRANSFER', amount=3000}",
                request.toString()
        );
    }

    @Test
    void insufficientFundsIsRejectedWithoutChangingBalance() {
        BankAccount account = account(100);

        assertEquals("Insufficient balance.\n",
                captureOutput(() -> account.withdraw(200)));
        assertEquals("Current Balance: 100\n", captureOutput(account::displayBalance));
        assertFalse(captureOutput(account::displayTransactionHistory)
                .contains("BankTransaction{"));
    }

    @Test
    void accountInformationKeepsTheSameLayout() {
        BankAccount account = account(10000);

        assertEquals(
                "===== Account Information =====\n"
                        + "Account Number: 10000001\n"
                        + "Customer: John\n"
                        + "Balance: 10000\n"
                        + "Active: true\n"
                        + "Transactions: 0\n",
                captureOutput(account::displayAccountInformation)
        );
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
