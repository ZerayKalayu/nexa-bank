package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PriorityBankingRequestQueueTest {

    @Test
    void equalPriorityRequestsAreDequeuedInFifoOrder() {
        PriorityBankingRequestQueue queue = new PriorityBankingRequestQueue();
        queue.addRequest(request(1L, "A", RequestPriority.NORMAL));
        queue.addRequest(request(2L, "B", RequestPriority.NORMAL));
        queue.addRequest(request(3L, "C", RequestPriority.NORMAL));
        queue.addRequest(request(4L, "D", RequestPriority.NORMAL));

        List<Long> processed = new ArrayList<>();
        while (!queue.isEmpty()) {
            processed.add(queue.processNextRequest().getRequestId());
        }

        assertEquals(List.of(1L, 2L, 3L, 4L), processed,
                "same-priority requests must be dequeued in insertion order");
    }

    @Test
    void displayPendingRequestsShowsPriorityOrderWithoutMutatingQueue() throws Exception {
        PriorityBankingRequestQueue queue = new PriorityBankingRequestQueue();
        queue.addRequest(request(2001L, "John", RequestPriority.NORMAL));
        queue.addRequest(request(2002L, "Mary", RequestPriority.HIGH));
        queue.addRequest(request(2003L, "David", RequestPriority.LOW));
        queue.addRequest(request(2004L, "Helen", RequestPriority.HIGH));

        List<Long> before = snapshotIds(queue);
        String output = captureOutput(queue::displayPendingRequests);
        List<Long> after = snapshotIds(queue);

        assertEquals(before, after, "display must not reorder the real queue");
        assertEquals(List.of(2002L, 2004L, 2001L, 2003L), displayedIds(output),
                "display must show true priority order");
        assertEquals(4, queue.getRequestCount(), "display must not remove requests");
        assertEquals(2002L, queue.peekNextRequest().getRequestId(),
                "display must not change the next request");
    }

    private static PriorityBankingRequest request(
            long id, String customerName, RequestPriority priority) {
        return new PriorityBankingRequest(id, customerName, "DEPOSIT", 0, priority);
    }

    @SuppressWarnings("unchecked")
    private static List<Long> snapshotIds(PriorityBankingRequestQueue queue)
            throws Exception {
        Field field = PriorityBankingRequestQueue.class.getDeclaredField("requests");
        field.setAccessible(true);
        Collection<Object> elements = (Collection<Object>) field.get(queue);

        List<Long> ids = new ArrayList<>();
        for (Object element : elements) {
            ids.add(unwrap(element).getRequestId());
        }
        return ids;
    }

    private static PriorityBankingRequest unwrap(Object element) throws Exception {
        if (element instanceof PriorityBankingRequest request) {
            return request;
        }
        Field field = element.getClass().getDeclaredField("request");
        field.setAccessible(true);
        return (PriorityBankingRequest) field.get(element);
    }

    private static List<Long> displayedIds(String output) {
        Matcher matcher = Pattern.compile("requestId=(\\d+)").matcher(output);
        List<Long> ids = new ArrayList<>();
        while (matcher.find()) {
            ids.add(Long.parseLong(matcher.group(1)));
        }
        return ids;
    }

    private static String captureOutput(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(bytes, true, StandardCharsets.UTF_8)) {
            System.setOut(capture);
            action.run();
        } finally {
            System.setOut(original);
        }
        return bytes.toString(StandardCharsets.UTF_8);
    }
}
