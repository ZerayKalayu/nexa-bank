
package com.yarez.banking;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class RecentActivityManager {

    private static final int MAX_ACTIVITIES = 10;

    private final Deque<BankTransaction> activities =
            new ArrayDeque<>();

    // 1. Add a new banking transaction to recent activities
    public boolean addActivity(BankTransaction transaction) {

        if (transaction == null) {
            System.out.println("Invalid banking transaction.");
            return false;
        }

        activities.addFirst(transaction);

        System.out.println(
                "Transaction added to recent activities: " + transaction.getTransactionId()
        );

        // Remove the oldest transaction if capacity exceeds 10
        if (activities.size() > MAX_ACTIVITIES) {

            BankTransaction removedTransaction = activities.removeLast();

            System.out.println(
                    "Oldest transaction removed: " + removedTransaction.getTransactionId()
            );
        }

        return true;
    }

    // 2. Get the latest banking transaction without removing it
    public BankTransaction getLatestActivity() {

        BankTransaction transaction = activities.peekFirst();

        if (transaction == null) {
            System.out.println("No recent banking activities.");
            return null;
        }

        return transaction;
    }

    // 3. Get all recent activities as an immutable list
    public List<BankTransaction> getRecentActivities() {

        return List.copyOf(activities);
    }

    // 4. Display all recent banking activities
    public void displayActivities() {

        System.out.println("===== Recent Banking Activities =====");

        if (activities.isEmpty()) {
            System.out.println("No recent banking activities.");
            return;
        }

        for (BankTransaction transaction : activities) {
            System.out.println(transaction);
        }
    }

    // 5. Get the number of recent banking activities
    public int getActivityCount() {

        return activities.size();
    }

    // 6. Check whether there are no recent activities
    public boolean isEmpty() {

        return activities.isEmpty();
    }

    // 7. Clear all recent banking activities
    public void clearActivities() {

        activities.clear();

        System.out.println("All recent banking activities cleared.");
    }
}
