
package com.yarez.banking;

import java.util.HashMap;
import java.util.Map;

public class AccountRegistry {

    private final Map<Long, BankAccount> accounts = new HashMap<>();

    // 1. Register a bank account
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

    // 2. Find an account using its account number
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

    // 5. Display all registered accounts
    public void displayAllAccounts() {

        System.out.println("===== Registered Bank Accounts =====");

        for (Map.Entry<Long, BankAccount> entry : accounts.entrySet()) {

            System.out.println("Account Number: " + entry.getKey());

            System.out.println(
                    "Customer: " + entry.getValue().getCustomer().getName()
            );

            System.out.println("--------------------------------");
        }
    }

    // 6. Get the total number of registered accounts
    public int getAccountCount() {

        return accounts.size();
    }

    // 7. Check whether the registry is empty
    public boolean isEmpty() {

        return accounts.isEmpty();
    }

    // 8. Remove all registered accounts from the registry
    public void clearAccounts() {

        accounts.clear();

        System.out.println("All accounts removed from registry.");
    }
}
