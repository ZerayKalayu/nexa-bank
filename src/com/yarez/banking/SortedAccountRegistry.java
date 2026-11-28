
package com.yarez.banking;

import java.util.Map;
import java.util.TreeMap;

public class SortedAccountRegistry {

    private final TreeMap<Long, BankAccount> accounts = new TreeMap<>();

    // 1. Register an account
    public boolean registerAccount(BankAccount account) {

        if (account == null) {
            System.out.println("Invalid account.");
            return false;
        }

        long accountNumber = account.getAccountNumber();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already registered: " + accountNumber);
            return false;
        }

        accounts.put(accountNumber, account);

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

    // 5. Display accounts in sorted order
    public void displaySortedAccounts() {

        System.out.println("===== Sorted Account Registry =====");

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

    // 8. Get the smallest account number
    public Long getFirstAccountNumber() {
        return accounts.isEmpty() ? null : accounts.firstKey();
    }

    // 9. Get the largest account number
    public Long getLastAccountNumber() {
        return accounts.isEmpty() ? null : accounts.lastKey();
    }

    // 10. Clear all accounts
    public void clearAccounts() {
        accounts.clear();
        System.out.println("All accounts removed from registry.");
    }
}
