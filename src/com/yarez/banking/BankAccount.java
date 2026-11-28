package com.yarez.banking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount implements Comparable<BankAccount> {

    private static final int WITHDRAWAL_LIMIT = 5000;

    private final long accountNumber;
    private final Customer customer;

    private int balance;
    private boolean active;
    private int transactionCount;

    private final List<BankTransaction> transactions = new ArrayList<>();

    public BankAccount(long accountNumber,
                       Customer customer,
                       int balance,
                       boolean active) {

        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = balance;
        this.active = active;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {

        if (amount <= 0) {
            System.out.println(
                    "Deposit amount must be greater than zero."
            );
            return;
        }

        balance += amount;
        transactionCount++;

        BankTransaction deposit = new BankTransaction(
               UUID.randomUUID(),// System.currentTimeMillis(),
                "DEPOSIT",
                amount,
                LocalDateTime.now(),
                balance
        );
        transactions.add(deposit);


        System.out.println("Deposit successful.");
    }

    public void withdraw(int amount) {

        if (amount <= 0) {
            System.out.println(
                    "Withdrawal amount must be greater than zero."
            );
            return;
        }

        if (!active) {
            System.out.println("Account is inactive.");
            return;
        }

        if (amount > WITHDRAWAL_LIMIT) {
            System.out.println(
                    "Withdrawal exceeds the maximum limit of "
                            + WITHDRAWAL_LIMIT
            );
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= amount;
        transactionCount++;

        BankTransaction withdrawal = new BankTransaction(
                UUID.randomUUID(),//System.currentTimeMillis(),
                "WITHDRAWAL",
                amount,
                LocalDateTime.now(),
                balance
        );

        transactions.add(withdrawal);

        System.out.println("Withdrawal successful.");
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public void displayAccountInformation() {

        System.out.println("===== Account Information =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Balance: " + balance);
        System.out.println("Active: " + active);
        System.out.println("Transactions: " + transactionCount);
    }

    // Get transaction history
    public List<BankTransaction> getTransactionHistory() {
        return List.copyOf(transactions);
    }

    public void displayTransactionHistory() {

        System.out.println("===== Transaction History =====");

        for (BankTransaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // ==========================================
// Comparable implementation
// ==========================================

    @Override
    public int compareTo(BankAccount other) {

        if (other == null) {
            throw new NullPointerException(
                    "Cannot compare a bank account with null."
            );
        }

        return Long.compare(
                this.accountNumber,
                other.accountNumber
        );
    }
}
