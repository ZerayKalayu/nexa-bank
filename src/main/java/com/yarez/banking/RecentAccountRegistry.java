
package com.yarez.banking;

import java.util.LinkedHashMap;
import java.util.Map;

public class RecentAccountRegistry {

    private final Map<Long, BankAccount> accounts = new LinkedHashMap<>();

    // 1. Register an account
    public boolean registerAccount(BankAccount account) {

        if (account == null) {
            System.out.println("Invalid account.");
            return false;
        }

        long accountNumber = account.getAccountNumber();

        BankAccount existing = accounts.putIfAbsent(accountNumber, account);

        if (existing != null) {
            System.out.println("Account already registered: " + accountNumber);
            return false;
        }

        System.out.println("Account registered: " + accountNumber);

        return true;
    }

    // 2. Find an account
    public BankAccount findAccount(long accountNumber) {
        return accounts.get(accountNumber);
    }

    // 3. Check whether an account exists
    public boolean accountExists(long accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    // 4. Remove an account
    public boolean removeAccount(long accountNumber) {

        BankAccount removedAccount = accounts.remove(accountNumber);

        if (removedAccount != null) {
            System.out.println("Account removed: " + accountNumber);
            return true;
        }

        System.out.println("Account not found: " + accountNumber);
        return false;
    }

    // 5. Display accounts in insertion order
    public void displayAccounts() {

        System.out.println("===== Recent Account Registry =====");

        for (Map.Entry<Long, BankAccount> entry : accounts.entrySet()) {

            System.out.println(
                    "Account Number: " + entry.getKey()
            );

            System.out.println(
                    "Customer: " + entry.getValue().getCustomer().getName()
            );
        }
    }

    // 6. Get account count
    public int getAccountCount() {
        return accounts.size();
    }

    // 7. Check whether registry is empty
    public boolean isEmpty() {
        return accounts.isEmpty();
    }

    // 8. Clear all accounts
    public void clearAccounts() {
        accounts.clear();
        System.out.println("All accounts removed from registry.");
    }
}
