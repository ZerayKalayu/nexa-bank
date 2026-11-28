
package com.yarez.banking;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityBankingRequestQueue {

    private static final class QueuedRequest {

        private final PriorityBankingRequest request;
        private final long sequence;

        private QueuedRequest(PriorityBankingRequest request, long sequence) {
            this.request = request;
            this.sequence = sequence;
        }
    }

    private long nextSequence;

    private final PriorityQueue<QueuedRequest> requests =
            new PriorityQueue<>(
                    Comparator.comparing(
                            (QueuedRequest entry) -> entry.request.getPriority()
                    ).thenComparingLong(entry -> entry.sequence)
            );

    // 1. Add a banking request
    public boolean addRequest(PriorityBankingRequest request) {

        if (request == null) {
            System.out.println("Invalid banking request.");
            return false;
        }

        boolean added = requests.offer(
                new QueuedRequest(request, nextSequence++)
        );

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

        QueuedRequest entry = requests.poll();

        if (entry == null) {
            System.out.println("No pending banking requests.");
            return null;
        }

        PriorityBankingRequest request = entry.request;

        System.out.println(
                "Processing request: " + request.getRequestId()
                        + " | Priority: " + request.getPriority()
        );

        return request;
    }

    // 3. View the next request without removing it
    public PriorityBankingRequest peekNextRequest() {

        QueuedRequest entry = requests.peek();

        return entry == null ? null : entry.request;
    }

    // 4. Display all pending requests in true priority order
    public void displayPendingRequests() {

        System.out.println("===== Pending Priority Requests =====");

        if (requests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        PriorityQueue<QueuedRequest> ordered =
                new PriorityQueue<>(requests.comparator());
        ordered.addAll(requests);

        while (!ordered.isEmpty()) {
            System.out.println(ordered.poll().request);
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
