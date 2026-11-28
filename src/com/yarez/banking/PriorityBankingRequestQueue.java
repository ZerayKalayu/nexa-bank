
package com.yarez.banking;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityBankingRequestQueue {

    private final PriorityQueue<PriorityBankingRequest> requests =
            new PriorityQueue<>(
                    Comparator.comparing(
                            PriorityBankingRequest::getPriority
                    )
            );

    // 1. Add a banking request
    public boolean addRequest(PriorityBankingRequest request) {

        if (request == null) {
            System.out.println("Invalid banking request.");
            return false;
        }

        boolean added = requests.offer(request);

        if (added) {
            System.out.println(
                    "Request added: " + request.getRequestId()
                            + " | Priority: " + request.getPriority()
            );
        }

        return added;
    }

    // 2. Process the highest-priority request
    public PriorityBankingRequest processNextRequest() {

        PriorityBankingRequest request = requests.poll();

        if (request == null) {
            System.out.println("No pending banking requests.");
            return null;
        }

        System.out.println(
                "Processing request: " + request.getRequestId()
                        + " | Priority: " + request.getPriority()
        );

        return request;
    }

    // 3. View the next request without removing it
    public PriorityBankingRequest peekNextRequest() {

        return requests.peek();
    }

    // 4. Display all pending requests
    public void displayPendingRequests() {

        System.out.println("===== Pending Priority Requests =====");

        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (PriorityBankingRequest request : requests) {
            System.out.println(request);
        }
    }

    // 5. Get the number of pending requests
    public int getRequestCount() {

        return requests.size();
    }

    // 6. Check whether the queue is empty
    public boolean isEmpty() {

        return requests.isEmpty();
    }

    // 7. Clear all pending requests
    public void clearRequests() {

        requests.clear();

        System.out.println("All pending requests cleared.");
    }
}
