
package com.yarez.banking;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BankTransaction {

    private final UUID transactionId;
    private final String type;
    private final BigDecimal amount;
    private final LocalDateTime timestamp;
    private final BigDecimal balanceAfter;

    public BankTransaction(UUID transactionId,
                           String type,
                           int amount,
                           LocalDateTime timestamp,
                           int balanceAfter) {
        this(transactionId, type, Money.of(amount), timestamp,
                Money.of(balanceAfter));
    }

    public BankTransaction(UUID transactionId,
                           String type,
                           BigDecimal amount,
                           LocalDateTime timestamp,
                           BigDecimal balanceAfter) {

        this.transactionId = transactionId;
        this.type = type;
        this.amount = Money.of(amount);
        this.timestamp = timestamp;
        this.balanceAfter = Money.of(balanceAfter);
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {
        return "BankTransaction{" +
                "transactionId=" + transactionId +
                ", type='" + type + '\'' +
                ", amount=" + Money.format(amount) +
                ", timestamp=" + timestamp +
                ", balanceAfter=" + Money.format(balanceAfter) +
                '}';
    }
}
