
package com.yarez.banking;

import java.math.BigDecimal;

public class BankingRequest {

    private final long requestId;
    private final String customerName;
    private final String operationType;
    private final BigDecimal amount;

    public BankingRequest(long requestId,
                          String customerName,
                          String operationType,
                          int amount) {
        this(requestId, customerName, operationType, Money.of(amount));
    }

    public BankingRequest(long requestId,
                          String customerName,
                          String operationType,
                          BigDecimal amount) {

        if (requestId <= 0) {
            throw new IllegalArgumentException("Request ID must be positive.");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }

        if (operationType == null || operationType.isBlank()) {
            throw new IllegalArgumentException("Operation type is required.");
        }

        BigDecimal value = Money.of(amount);

        if (value.signum() < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        this.requestId = requestId;
        this.customerName = customerName;
        this.operationType = operationType;
        this.amount = value;
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

    @Override
    public String toString() {
        return "BankingRequest{" +
                "requestId=" + requestId +
                ", customerName='" + customerName + '\'' +
                ", operationType='" + operationType + '\'' +
                ", amount=" + Money.format(amount) +
                '}';
    }
}
