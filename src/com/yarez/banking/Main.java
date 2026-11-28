
package com.yarez.banking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ==========================================
        // 1. Customer creation
        // ==========================================

        Customer customer = new Customer(
                233785885L,
                "John",
                35,
                "john1234@gmail.com",
                true
        );


        // ==========================================
        // 2. BankAccount creation
        // ==========================================

        BankAccount account = new BankAccount(
                10000001L,
                customer,
                Money.of("10000"),
                true
        );


        // ==========================================
        // 3. Deposit and withdrawal operations
        // ==========================================

        account.deposit(Money.of("2500"));
        account.withdraw(Money.of("3000"));
        account.withdraw(Money.of("6000"));
        account.withdraw(Money.of("5001"));
        account.withdraw(Money.of("5000"));


        // ==========================================
        // 4. Display transaction history and account information
        // ==========================================

        account.displayTransactionHistory();
        account.displayAccountInformation();


        // ==========================================
        // 5. Basic LinkedList operations (String)
        // ==========================================

        LinkedList<String> bankingOperations = new LinkedList<>();

        bankingOperations.add("Verify customer");
        bankingOperations.add("Validate account");
        bankingOperations.add("Process deposit");

        bankingOperations.addFirst("Authenticate customer");
        bankingOperations.addLast("Generate receipt");

        bankingOperations.removeFirst();
        bankingOperations.removeLast();

        System.out.println("\n===== Banking Operations =====");
        System.out.println("First operation: " + bankingOperations.getFirst());
        System.out.println("Last operation: " + bankingOperations.getLast());
        System.out.println("All operations: " + bankingOperations);


        // ==========================================
        // 6. LinkedList implementation (BankingRequest objects)
        // ==========================================

        LinkedList<BankingRequest> pendingRequests = new LinkedList<>();

        pendingRequests.addLast(
                new BankingRequest(1001, "John", "DEPOSIT", Money.of("5000"))
        );

        pendingRequests.addLast(
                new BankingRequest(1002, "Mary", "WITHDRAWAL", Money.of("2000"))
        );

        pendingRequests.addLast(
                new BankingRequest(1003, "David", "TRANSFER", Money.of("3000"))
        );

        System.out.println("\n===== Pending Banking Requests =====");

        for (BankingRequest request : pendingRequests) {
            System.out.println(request);
        }

        // ==========================================
     // 7. HashSet implementation (Unique transaction IDs)
     // ==========================================

        TransactionRegistry transactionRegistry = new TransactionRegistry();

        transactionRegistry.registerTransaction("TXN-1001");
        transactionRegistry.registerTransaction("TXN-1002");
        transactionRegistry.registerTransaction("TXN-1003");
        transactionRegistry.registerTransaction("TXN-1002");
        transactionRegistry.registerTransaction("TXN-1004");
        transactionRegistry.registerTransaction("TXN-1001");

        transactionRegistry.displayRegisteredTransactions();

        System.out.println(
                "Total registered transactions: "
                        + transactionRegistry.getTransactionCount()
        );

        // ==========================================
// 8. LinkedHashSet implementation (Recent transaction IDs)
// ==========================================

        RecentTransactionRegistry recentRegistry = new RecentTransactionRegistry();

        recentRegistry.addTransaction("TXN-1001");
        recentRegistry.addTransaction("TXN-1002");
        recentRegistry.addTransaction("TXN-1003");
        recentRegistry.addTransaction("TXN-1002");
        recentRegistry.addTransaction("TXN-1004");

        recentRegistry.displayRecentTransactions();

        System.out.println(
                "Total recent transactions: "
                        + recentRegistry.getTransactionCount()
        );

        // ==========================================
// 9. TreeSet implementation (Sorted transaction IDs)
// ==========================================

        SortedTransactionRegistry sortedRegistry = new SortedTransactionRegistry();

        sortedRegistry.addTransaction("TXN-1005");
        sortedRegistry.addTransaction("TXN-1002");
        sortedRegistry.addTransaction("TXN-1008");
        sortedRegistry.addTransaction("TXN-1001");
        sortedRegistry.addTransaction("TXN-1003");
        sortedRegistry.addTransaction("TXN-1002");

        sortedRegistry.displaySortedTransactions();

        System.out.println("Total transactions: " + sortedRegistry.getTransactionCount());

        System.out.println("First transaction: " + sortedRegistry.getFirstTransaction());

        System.out.println("Last transaction: " + sortedRegistry.getLastTransaction());


        // ==========================================
// 10. HashMap implementation (Account Registry)
// ==========================================

        AccountRegistry accountRegistry = new AccountRegistry();

        Customer customer1 = new Customer(
                101L, "John", 35, "john@gmail.com", true
        );

        Customer customer2 = new Customer(
                102L, "Mary", 29, "mary@gmail.com", true
        );

        Customer customer3 = new Customer(
                103L, "David", 40, "david@gmail.com", true
        );

        BankAccount account1 = new BankAccount(
                10000001L, customer1, Money.of("15000"), true
        );

        BankAccount account2 = new BankAccount(
                10000002L, customer2, Money.of("25000"), true
        );

        BankAccount account3 = new BankAccount(
                10000003L, customer3, Money.of("35000"), true
        );

// Register accounts
        accountRegistry.registerAccount(account1);
        accountRegistry.registerAccount(account2);
        accountRegistry.registerAccount(account3);

// Try registering a duplicate account number
        accountRegistry.registerAccount(account1);

// Display all accounts
        accountRegistry.displayAllAccounts();

// Find an account
        BankAccount foundAccount = accountRegistry.findAccount(10000002L);

        if (foundAccount != null) {
            System.out.println("Found customer: "
                    + foundAccount.getCustomer().getName());
        }

// Check whether an account exists
        System.out.println(
                "Account exists: "
                        + accountRegistry.accountExists(10000003L)
        );

// Display total accounts
        System.out.println(
                "Total registered accounts: "
                        + accountRegistry.getAccountCount()
        );

        // ==========================================
      // 11. LinkedHashMap implementation
      // ==========================================

        RecentAccountRegistry recentAccountRegistry =
                new RecentAccountRegistry();

        recentAccountRegistry.registerAccount(account3);
        recentAccountRegistry.registerAccount(account1);
        recentAccountRegistry.registerAccount(account2);

        recentAccountRegistry.displayAccounts();

        // ==========================================
// 12. TreeMap implementation
// ==========================================

        SortedAccountRegistry sortedAccountRegistry =
                new SortedAccountRegistry();

        sortedAccountRegistry.registerAccount(account3);
        sortedAccountRegistry.registerAccount(account1);
        sortedAccountRegistry.registerAccount(account2);

        sortedAccountRegistry.displaySortedAccounts();

        System.out.println(
                "Smallest account number: "
                        + sortedAccountRegistry.getFirstAccountNumber()
        );

        System.out.println(
                "Largest account number: "
                        + sortedAccountRegistry.getLastAccountNumber()
        );

        // ==========================================
// 13. Queue implementation (Banking Requests)
// ==========================================

        BankingRequestQueue requestQueue = new BankingRequestQueue();

// Add requests
        requestQueue.addRequest(
                new BankingRequest(1001, "John", "DEPOSIT", Money.of("5000"))
        );

        requestQueue.addRequest(
                new BankingRequest(1002, "Mary", "WITHDRAWAL", Money.of("2000"))
        );

        requestQueue.addRequest(
                new BankingRequest(1003, "David", "TRANSFER", Money.of("3000"))
        );

// Display pending requests
        requestQueue.displayPendingRequests();

// View next request without removing it
        System.out.println(
                "Next request: " + requestQueue.peekNextRequest()
        );

// Process requests
        BankingRequest processedRequest = requestQueue.processNextRequest();

        System.out.println("Processed: " + processedRequest);

// Display remaining requests
        requestQueue.displayPendingRequests();

// Display queue size
        System.out.println(
                "Remaining requests: " + requestQueue.getRequestCount()
        );

        // ==========================================
// 14. PriorityQueue implementation
// ==========================================

        PriorityBankingRequestQueue priorityQueue =
                new PriorityBankingRequestQueue();

    // Add requests in different arrival orders and priorities

        priorityQueue.addRequest(
                new PriorityBankingRequest(
                        2001, "John", "DEPOSIT", Money.of("5000"),
                        RequestPriority.NORMAL
                )
        );

        priorityQueue.addRequest(
                new PriorityBankingRequest(
                        2002, "Mary", "FRAUD INVESTIGATION", Money.of("0"),
                        RequestPriority.HIGH
                )
        );

        priorityQueue.addRequest(
                new PriorityBankingRequest(
                        2003, "David", "ACCOUNT STATEMENT", Money.of("0"),
                        RequestPriority.LOW
                )
        );

        priorityQueue.addRequest(
                new PriorityBankingRequest(
                        2004, "Helen", "ACCOUNT BLOCKING", Money.of("0"),
                        RequestPriority.HIGH
                )
        );

   // View the next request
        System.out.println(
                "Next request: " + priorityQueue.peekNextRequest()
        );

   // Process requests according to priority
        while (!priorityQueue.isEmpty()) {

            PriorityBankingRequest processed =
                    priorityQueue.processNextRequest();

            System.out.println("Processed: " + processed);
        }

        // ==========================================
// 15. ArrayDeque implementation
// ==========================================

        RecentActivityManager recentActivityManager =
                new RecentActivityManager();

// Add existing account transactions
        for (BankTransaction transaction : account.getTransactionHistory()) {
            recentActivityManager.addActivity(transaction);
        }

// Display recent activities
        recentActivityManager.displayActivities();

// Get latest transaction
        System.out.println(
                "Latest transaction: "
                        + recentActivityManager.getLatestActivity()
        );

// Display activity count
        System.out.println(
                "Total recent activities: "
                        + recentActivityManager.getActivityCount()
        );

// Check whether recent activities are empty
        System.out.println(
                "Is activity history empty: "
                        + recentActivityManager.isEmpty()
        );

        // ==========================================
        // 16. Comparable and Comparator implementation
        // ==========================================

        AccountSorting accountSorting = new AccountSorting();

        // Create account list
        List<BankAccount> accounts = new ArrayList<>();

        accounts.add(account3);
        accounts.add(account1);
        accounts.add(account2);

        // 1. Sort by natural ordering (account number)
        accountSorting.sortByAccountNumber(accounts);
        accountSorting.displayAccounts(accounts);

        // 2. Sort by balance (ascending)
        accountSorting.sortByBalanceAscending(accounts);
        accountSorting.displayAccounts(accounts);

        // 3. Sort by balance (descending)
        accountSorting.sortByBalanceDescending(accounts);
        accountSorting.displayAccounts(accounts);

        // 4. Sort by balance, then account number
        accountSorting.sortByBalanceThenAccountNumber(accounts);
        accountSorting.displayAccounts(accounts);


        // ==========================================
        // Transaction sorting implementation
        // ==========================================

        TransactionSorting transactionSorting =
                new TransactionSorting();

        // getTransactionHistory() returns an immutable copy.
        // Create a mutable ArrayList before sorting.
        List<BankTransaction> transactions =
                new ArrayList<>(account.getTransactionHistory());

        // 5. Sort transactions by amount (ascending)
        transactionSorting.sortByAmountAscending(transactions);
        transactionSorting.displayTransactions(transactions);

        // 6. Sort transactions by amount (descending)
        transactionSorting.sortByAmountDescending(transactions);
        transactionSorting.displayTransactions(transactions);

        // 7. Sort transactions by timestamp (oldest first)
        transactionSorting.sortByTimestampAscending(transactions);
        transactionSorting.displayTransactions(transactions);

        // 8. Sort transactions by timestamp (newest first)
        transactionSorting.sortByTimestampDescending(transactions);
        transactionSorting.displayTransactions(transactions);

        // 9. Sort transactions by type, then amount
        transactionSorting.sortByTypeThenAmount(transactions);
        transactionSorting.displayTransactions(transactions);

    }
}
