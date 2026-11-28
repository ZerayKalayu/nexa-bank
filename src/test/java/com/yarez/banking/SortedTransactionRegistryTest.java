package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SortedTransactionRegistryTest {

    @Test
    void keepsTransactionIdsSortedAndReportsExtremes() {
        SortedTransactionRegistry registry = new SortedTransactionRegistry();
        registry.addTransaction("TXN-1005");
        registry.addTransaction("TXN-1002");
        registry.addTransaction("TXN-1008");
        registry.addTransaction("TXN-1001");
        registry.addTransaction("TXN-1003");
        registry.addTransaction("TXN-1002");

        String output = captureOutput(registry::displaySortedTransactions);

        assertEquals(
                "===== Sorted Transaction Registry =====\n"
                        + "TXN-1001\n"
                        + "TXN-1002\n"
                        + "TXN-1003\n"
                        + "TXN-1005\n"
                        + "TXN-1008\n",
                output,
                "iteration must stay sorted as before the refactor"
        );
        assertEquals(5, registry.getTransactionCount());
        assertEquals("TXN-1001", registry.getFirstTransaction());
        assertEquals("TXN-1008", registry.getLastTransaction());
    }

    @Test
    void emptyRegistryStillReturnsNullExtremes() {
        SortedTransactionRegistry registry = new SortedTransactionRegistry();

        assertNull(registry.getFirstTransaction());
        assertNull(registry.getLastTransaction());
        assertEquals(
                "===== Sorted Transaction Registry =====\n",
                captureOutput(registry::displaySortedTransactions)
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
