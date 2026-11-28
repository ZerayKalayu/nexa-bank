
package com.yarez.banking;

import java.math.BigDecimal;

public class PriorityBankingRequest {

    private final long requestId;
    private final String customerName;
    private final String operationType;
    private final BigDecimal amount;
    private final RequestPriority priority;

    public PriorityBankingRequest(
            long requestId,
            String customerName,
            String operationType,
            int amount,
            RequestPriority priority) {
        this(requestId, customerName, operationType, Money.of(amount), priority);
    }

    public PriorityBankingRequest(
            long requestId,
            String customerName,
            String operationType,
            BigDecimal amount,
            RequestPriority priority) {

        if (requestId <= 0) {
            throw new IllegalArgumentException(
                    "Request ID must be positive."
            );
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException(
                    "Customer name is required."
            );
        }

        if (operationType == null || operationType.isBlank()) {
            throw new IllegalArgumentException(
                    "Operation type is required."
            );
        }

        BigDecimal value = Money.of(amount);

        if (value.signum() < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative."
            );
        }

        if (priority == null) {
            throw new IllegalArgumentException(
                    "Request priority is required."
            );
        }

        this.requestId = requestId;
        this.customerName = customerName;
        this.operationType = operationType;
        this.amount = value;
        this.priority = priority;
    }

    public long getRequestId() {
        return requestId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOperationType() {
        return operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public RequestPriority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "PriorityBankingRequest{" +
                "requestId=" + requestId +
                ", customerName='" + customerName + '\'' +
                ", operationType='" + operationType + '\'' +
                ", amount=" + Money.format(amount) +
                ", priority=" + priority +
                '}';
    }
}
