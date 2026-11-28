package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionSortingTest {

    @Test
    void sortsDistinctExplicitTimestampsInBothDirections() {
        List<BankTransaction> transactions = new ArrayList<>(List.of(
                transaction("NEWEST", LocalDateTime.of(2026, 1, 3, 12, 0)),
                transaction("OLDEST", LocalDateTime.of(2026, 1, 1, 12, 0)),
                transaction("MIDDLE", LocalDateTime.of(2026, 1, 2, 12, 0))
        ));

        TransactionSorting sorting = new TransactionSorting();

        sorting.sortByTimestampAscending(transactions);
        assertEquals(
                List.of("OLDEST", "MIDDLE", "NEWEST"),
                transactionIds(transactions),
                "ascending sort must use the explicit timestamps"
        );

        sorting.sortByTimestampDescending(transactions);
        assertEquals(
                List.of("NEWEST", "MIDDLE", "OLDEST"),
                transactionIds(transactions),
                "descending sort must use the explicit timestamps"
        );
    }

    private static BankTransaction transaction(
            String label, LocalDateTime timestamp) {
        return new BankTransaction(
                UUID.randomUUID(),
                label,
                new BigDecimal("10.25"),
                timestamp,
                new BigDecimal("20.50")
        );
    }

    private static List<String> transactionIds(
            List<BankTransaction> transactions) {
        return transactions.stream()
                .map(BankTransaction::getType)
                .toList();
    }
}
