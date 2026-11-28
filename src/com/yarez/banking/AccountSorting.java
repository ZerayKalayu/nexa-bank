package com.yarez.banking;

import java.util.Comparator;
import java.util.List;

public class AccountSorting {

    // ==========================================
    // 1. Sort accounts by account number
    // ==========================================

    public void sortByAccountNumber(List<BankAccount> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException(
                    "Account list cannot be null."
            );
        }

        accounts.sort(Comparator.naturalOrder());

        System.out.println("Accounts sorted by account number.");
    }

    // ==========================================
    // 2. Sort accounts by balance (ascending)
    // ==========================================

    public void sortByBalanceAscending(List<BankAccount> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException(
                    "Account list cannot be null."
            );
        }

        accounts.sort(
                Comparator.comparingInt(BankAccount::getBalance)
        );

        System.out.println("Accounts sorted by balance (ascending).");
    }

    // ==========================================
    // 3. Sort accounts by balance (descending)
    // ==========================================

    public void sortByBalanceDescending(List<BankAccount> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException(
                    "Account list cannot be null."
            );
        }

        accounts.sort(
                Comparator.comparingInt(BankAccount::getBalance)
                        .reversed()
        );

        System.out.println("Accounts sorted by balance (descending).");
    }

    // ==========================================
    // 4. Sort by balance, then account number
    // ==========================================

    public void sortByBalanceThenAccountNumber(
            List<BankAccount> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException(
                    "Account list cannot be null."
            );
        }

        Comparator<BankAccount> comparator =
                Comparator.comparingInt(BankAccount::getBalance)
                        .thenComparingLong(BankAccount::getAccountNumber);

        accounts.sort(comparator);

        System.out.println(
                "Accounts sorted by balance and account number."
        );
    }

    // ==========================================
    // 5. Display sorted accounts
    // ==========================================

    public void displayAccounts(List<BankAccount> accounts) {

        if (accounts == null) {
            throw new IllegalArgumentException(
                    "Account list cannot be null."
            );
        }

        System.out.println("===== Sorted Bank Accounts =====");

        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
            return;
        }

        for (BankAccount account : accounts) {

            System.out.println(
                    "Account Number: " + account.getAccountNumber()
                            + " | Customer: " + account.getCustomer().getName()
                            + " | Balance: " + account.getBalance()
            );
        }
    }
}