
package com.yarez.banking;

public class BankingRequest {

    private final long requestId;
    private final String customerName;
    private final String operationType;
    private final int amount;

    public BankingRequest(long requestId,
                          String customerName,
                          String operationType,
                          int amount) {

        if (requestId <= 0) {
            throw new IllegalArgumentException("Request ID must be positive.");
        }

        if (customerName == null || customerName.isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }

        if (operationType == null || operationType.isBlank()) {
            throw new IllegalArgumentException("Operation type is required.");
        }

        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        this.requestId = requestId;
        this.customerName = customerName;
        this.operationType = operationType;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "BankingRequest{" +
                "requestId=" + requestId +
                ", customerName='" + customerName + '\'' +
                ", operationType='" + operationType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
