
package com.yarez.banking;

import java.util.HashSet;
import java.util.Set;

public class TransactionRegistry {

    private final Set<String> transactionIds = new HashSet<>();

    public boolean registerTransaction(String transactionId) {

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

    public boolean transactionExists(String transactionId) {
        return transactionIds.contains(transactionId);
    }

    public void displayRegisteredTransactions() {
        System.out.println("===== Registered Transactions =====");

        for (String transactionId : transactionIds) {
            System.out.println(transactionId);
        }
    }

    public int getTransactionCount() {
        return transactionIds.size();
    }
}
