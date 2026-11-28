package com.yarez.banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount implements Comparable<BankAccount> {

    private static final BigDecimal WITHDRAWAL_LIMIT = Money.of(5000);

    private final long accountNumber;
    private final Customer customer;

    private BigDecimal balance;
    private boolean active;
    private int transactionCount;

    private final List<BankTransaction> transactions = new ArrayList<>();

    public BankAccount(long accountNumber,
                       Customer customer,
                       int balance,
                       boolean active) {
        this(accountNumber, customer, Money.of(balance), active);
    }

    public BankAccount(long accountNumber,
                       Customer customer,
                       BigDecimal balance,
                       boolean active) {

        BigDecimal openingBalance = Money.of(balance);

        if (openingBalance.signum() < 0) {
            throw new IllegalArgumentException(
                    "Initial balance cannot be negative."
            );
        }

        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = openingBalance;
        this.active = active;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        deposit(Money.of(amount));
    }

    public void deposit(BigDecimal amount) {

        BigDecimal value = Money.of(amount);

        if (value.signum() <= 0) {
            System.out.println(
                    "Deposit amount must be greater than zero."
            );
            return;
        }

        balance = Money.of(balance.add(value));
        transactionCount++;

        BankTransaction deposit = new BankTransaction(
               UUID.randomUUID(),// System.currentTimeMillis(),
                "DEPOSIT",
                value,
                LocalDateTime.now(),
                balance
        );
        transactions.add(deposit);


        System.out.println("Deposit successful.");
    }

    public void withdraw(int amount) {
        withdraw(Money.of(amount));
    }

    public void withdraw(BigDecimal amount) {

        BigDecimal value = Money.of(amount);

        if (value.signum() <= 0) {
            System.out.println(
                    "Withdrawal amount must be greater than zero."
            );
            return;
        }

        if (!active) {
            System.out.println("Account is inactive.");
            return;
        }

        if (value.compareTo(WITHDRAWAL_LIMIT) > 0) {
            System.out.println(
                    "Withdrawal exceeds the maximum limit of "
                            + Money.format(WITHDRAWAL_LIMIT)
            );
            return;
        }

        if (value.compareTo(balance) > 0) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance = Money.of(balance.subtract(value));
        transactionCount++;

        BankTransaction withdrawal = new BankTransaction(
                UUID.randomUUID(),//System.currentTimeMillis(),
                "WITHDRAWAL",
                value,
                LocalDateTime.now(),
                balance
        );

        transactions.add(withdrawal);

        System.out.println("Withdrawal successful.");
    }

    public void displayBalance() {
        System.out.println("Current Balance: " + Money.format(balance));
    }

    public void displayAccountInformation() {

        System.out.println("===== Account Information =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Balance: " + Money.format(balance));
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

    /**
     * Accounts are identified only by account number, the same field used by
     * {@link #compareTo(BankAccount)}. Customer, balance, and active state are
     * operational data and are deliberately excluded from identity.
     */
    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof BankAccount that)) {
            return false;
        }

        return accountNumber == that.accountNumber;
    }

    /**
     * Hashes the account number so that equal accounts have equal hash codes
     * and {@link #equals(Object)}, {@link #hashCode()}, and
     * {@link #compareTo(BankAccount)} stay consistent.
     */
    @Override
    public int hashCode() {
        return Long.hashCode(accountNumber);
    }
}
