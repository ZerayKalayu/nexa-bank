
package com.yarez.banking;

import java.util.ArrayDeque;
import java.util.Queue;

public class BankingRequestQueue {

    private final Queue<BankingRequest> requests = new ArrayDeque<>();

    // 1. Add a banking request to the queue
    public boolean addRequest(BankingRequest request) {

        if (request == null) {
            System.out.println("Invalid banking request.");
            return false;
        }

        boolean added = requests.offer(request);

        if (added) {
            System.out.println(
                    "Request added to queue: " + request.getRequestId()
            );
        }

        return added;
    }

    // 2. Process the next banking request (FIFO)
    public BankingRequest processNextRequest() {

        BankingRequest request = requests.poll();

        if (request == null) {
            System.out.println("No pending banking requests.");
            return null;
        }

        System.out.println(
                "Processing request: " + request.getRequestId()
        );

        return request;
    }

    // 3. View the next request without removing it
    public BankingRequest peekNextRequest() {

        return requests.peek();
    }

    // 4. Display all pending requests
    public void displayPendingRequests() {

        System.out.println("===== Pending Banking Requests =====");

        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (BankingRequest request : requests) {
            System.out.println(request);
        }
    }

    // 5. Get the total number of pending requests
    public int getRequestCount() {

        return requests.size();
    }

    // 6. Check whether the queue is empty
    public boolean isEmpty() {

        return requests.isEmpty();
    }

    // 7. Remove all pending requests
    public void clearRequests() {

        requests.clear();

        System.out.println("All pending requests cleared.");
    }
}
