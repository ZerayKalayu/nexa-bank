package com.yarez.banking;

import java.util.Comparator;
import java.util.List;

public class TransactionSorting {

    // ==========================================
    // 1. Sort transactions by amount (ascending)
    // ==========================================

    public void sortByAmountAscending(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        transactions.sort(
                Comparator.comparingInt(BankTransaction::getAmount)
        );

        System.out.println(
                "Transactions sorted by amount (ascending)."
        );
    }

    // ==========================================
    // 2. Sort transactions by amount (descending)
    // ==========================================

    public void sortByAmountDescending(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        transactions.sort(
                Comparator.comparingInt(BankTransaction::getAmount)
                        .reversed()
        );

        System.out.println(
                "Transactions sorted by amount (descending)."
        );
    }

    // ==========================================
    // 3. Sort transactions by timestamp (oldest first)
    // ==========================================

    public void sortByTimestampAscending(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        transactions.sort(
                Comparator.comparing(BankTransaction::getTimestamp)
        );

        System.out.println(
                "Transactions sorted by timestamp (oldest first)."
        );
    }

    // ==========================================
    // 4. Sort transactions by timestamp (newest first)
    // ==========================================

    public void sortByTimestampDescending(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        transactions.sort(
                Comparator.comparing(BankTransaction::getTimestamp)
                        .reversed()
        );

        System.out.println(
                "Transactions sorted by timestamp (newest first)."
        );
    }

    // ==========================================
    // 5. Sort by transaction type, then amount
    // ==========================================

    public void sortByTypeThenAmount(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        Comparator<BankTransaction> comparator =
                Comparator.comparing(BankTransaction::getType)
                        .thenComparingInt(BankTransaction::getAmount);

        transactions.sort(comparator);

        System.out.println(
                "Transactions sorted by type and amount."
        );
    }

    // ==========================================
    // 6. Display sorted transactions
    // ==========================================

    public void displayTransactions(
            List<BankTransaction> transactions) {

        if (transactions == null) {
            throw new IllegalArgumentException(
                    "Transaction list cannot be null."
            );
        }

        System.out.println("===== Sorted Bank Transactions =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (BankTransaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}