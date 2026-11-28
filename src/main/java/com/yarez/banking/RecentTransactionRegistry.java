
package com.yarez.banking;

import java.util.LinkedHashSet;
import java.util.Set;

public class RecentTransactionRegistry {

    private final Set<String> recentTransactionIds = new LinkedHashSet<>();

    // 1. Register a transaction ID
    public boolean addTransaction(String transactionId) {

        if (transactionId == null || transactionId.isBlank()) {
            System.out.println("Invalid transaction ID.");
            return false;
        }

        boolean added = recentTransactionIds.add(transactionId);

        if (added) {
            System.out.println("Transaction registered: " + transactionId);
        } else {
            System.out.println("Duplicate transaction rejected: " + transactionId);
        }

        return added;
    }

    // 2. Check whether a transaction exists
    public boolean transactionExists(String transactionId) {
        return recentTransactionIds.contains(transactionId);
    }

    // 3. Remove a transaction
    public boolean removeTransaction(String transactionId) {

        boolean removed = recentTransactionIds.remove(transactionId);

        if (removed) {
            System.out.println("Transaction removed: " + transactionId);
        } else {
            System.out.println("Transaction not found: " + transactionId);
        }

        return removed;
    }

    // 4. Display transactions in insertion order
    public void displayRecentTransactions() {

        System.out.println("===== Recent Transaction Registry =====");

        for (String transactionId : recentTransactionIds) {
            System.out.println(transactionId);
        }
    }

    // 5. Get total number of transactions
    public int getTransactionCount() {
        return recentTransactionIds.size();
    }

    // 6. Check whether the registry is empty
    public boolean isEmpty() {
        return recentTransactionIds.isEmpty();
    }
}
