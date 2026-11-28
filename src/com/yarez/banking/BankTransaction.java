
package com.yarez.banking;

import java.time.LocalDateTime;
import java.util.UUID;

public class BankTransaction {

    private final UUID transactionId;
    private final String type;
    private final int amount;
    private final LocalDateTime timestamp;
    private final int balanceAfter;

    public BankTransaction(UUID transactionId,
                           String type,
                           int amount,
                           LocalDateTime timestamp,
                           int balanceAfter) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.balanceAfter = balanceAfter;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", balanceAfter=" + balanceAfter +
                '}';
    }
}
