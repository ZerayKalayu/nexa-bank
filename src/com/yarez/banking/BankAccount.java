package com.yarez.banking;

public class BankAccount {

    private static final int WITHDRAWAL_LIMIT = 5000;

    private final long accountNumber;
    private final Customer customer;

    private int balance;
    private boolean active;
    private int transactionCount;

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

    public void deposit(int amount) {

        if (amount <= 0) {
            System.out.println(
                    "Deposit amount must be greater than zero."
            );
            return;
        }

        balance += amount;
        transactionCount++;

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
}
