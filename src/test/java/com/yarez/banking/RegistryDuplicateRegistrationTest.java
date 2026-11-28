package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistryDuplicateRegistrationTest {

    @Test
    void accountRegistriesRejectDuplicatesWithTheSameMessage() throws Exception {
        assertAccountRegistryDuplicate(
                new AccountRegistry(), "Account already registered: 10000001");
        assertAccountRegistryDuplicate(
                new RecentAccountRegistry(), "Account already registered: 10000001");
        assertAccountRegistryDuplicate(
                new SortedAccountRegistry(), "Account already registered: 10000001");
    }

    @Test
    void transactionRegistriesAlreadyUseSingleBooleanAdd() {
        TransactionRegistry unique = new TransactionRegistry();
        assertTrue(unique.registerTransaction("TXN-1001"));
        assertFalse(unique.registerTransaction("TXN-1001"));
        assertEquals(1, unique.getTransactionCount());

        RecentTransactionRegistry recent = new RecentTransactionRegistry();
        assertTrue(recent.addTransaction("TXN-1001"));
        assertFalse(recent.addTransaction("TXN-1001"));
        assertEquals(1, recent.getTransactionCount());

        SortedTransactionRegistry sorted = new SortedTransactionRegistry();
        assertTrue(sorted.addTransaction("TXN-1001"));
        assertFalse(sorted.addTransaction("TXN-1001"));
        assertEquals(1, sorted.getTransactionCount());
    }

    private static void assertAccountRegistryDuplicate(
            Object registry, String expectedDuplicateMessage) throws Exception {
        Method register = registry.getClass().getMethod("registerAccount", BankAccount.class);
        Method count = registry.getClass().getMethod("getAccountCount");
        Method exists = registry.getClass().getMethod("accountExists", long.class);

        BankAccount account = account(10000001L);

        assertTrue((boolean) register.invoke(registry, account));
        assertFalse((boolean) register.invoke(registry, account),
                "duplicate registration must be rejected");
        assertEquals(1, count.invoke(registry));
        assertTrue((boolean) exists.invoke(registry, 10000001L));

        String output = captureOutput(() -> {
            try {
                register.invoke(registry, account);
            } catch (ReflectiveOperationException e) {
                throw new IllegalStateException(e);
            }
        });
        assertTrue(output.contains(expectedDuplicateMessage),
                "duplicate message must stay unchanged, got: " + output);
    }

    private static BankAccount account(long number) {
        return new BankAccount(
                number,
                new Customer(number, "John", 35, "john@example.com", true),
                10000,
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
