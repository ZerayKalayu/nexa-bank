package com.yarez.banking;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer(
                233785885L,
                "John",
                35,
                "john1234@gmail.com",
                true
        );

        BankAccount account = new BankAccount(
                10000001L,
                customer,
                10000,
                true
        );

        account.deposit(2500);
        account.withdraw(3000);
        account.withdraw(6000);
        account.withdraw(5001);
        account.withdraw(5000);

        account.displayTransactionHistory();
        account.displayAccountInformation();
    }
}