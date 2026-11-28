
package com.yarez.banking;

import java.util.NavigableSet;
import java.util.TreeSet;

public class SortedTransactionRegistry {

    private final NavigableSet<String> transactionIds = new TreeSet<>();

    // 1. Register a transaction ID
    public boolean addTransaction(String transactionId) {

        if (transactionId == null || transactionId.isBlank()) {
            System.out.println("Invalid transaction ID.");
            return false;
        }

        boolean added = transactionIds.add(transactionId);

        if (added) {
            System.out.println("Transaction registered: " + transactionId);
        } else {
            System.out.println("Duplicate transaction rejected: " + transactionId);
        }

        return added;
    }

    // 2. Check whether a transaction exists
    public boolean transactionExists(String transactionId) {
        return transactionIds.contains(transactionId);
    }

    // 3. Remove a transaction
    public boolean removeTransaction(String transactionId) {
        boolean removed = transactionIds.remove(transactionId);

        if (removed) {
            System.out.println("Transaction removed: " + transactionId);
        } else {
            System.out.println("Transaction not found: " + transactionId);
        }

        return removed;
    }

    // 4. Display transactions in sorted order
    public void displaySortedTransactions() {

        System.out.println("===== Sorted Transaction Registry =====");

        for (String transactionId : transactionIds) {
            System.out.println(transactionId);
        }
    }

    // 5. Get total number of transactions
    public int getTransactionCount() {
        return transactionIds.size();
    }

    // 6. Check whether the registry is empty
    public boolean isEmpty() {
        return transactionIds.isEmpty();
    }

    // 7. Get the smallest transaction ID
    public String getFirstTransaction() {
        return transactionIds.isEmpty() ? null : transactionIds.first();
    }

    // 8. Get the largest transaction ID
    public String getLastTransaction() {
        return transactionIds.isEmpty() ? null : transactionIds.last();
    }
}
