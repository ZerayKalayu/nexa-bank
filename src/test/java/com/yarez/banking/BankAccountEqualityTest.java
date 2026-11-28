package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BankAccountEqualityTest {

    @Test
    void sameAccountNumberIsEqualAndHasSameHashCode() {
        BankAccount first = account(10000001L, "John", 15000);
        BankAccount second = account(10000001L, "Mary", 25000);

        assertEquals(first, second, "same account number must be equal");
        assertEquals(first.hashCode(), second.hashCode(),
                "same account number must have the same hashCode");
    }

    @Test
    void equalAccountsBehaveTheSameInHashSetAndTreeSet() {
        BankAccount first = account(10000001L, "John", 15000);
        BankAccount second = account(10000001L, "Mary", 25000);

        Set<BankAccount> hashSet = new HashSet<>();
        hashSet.add(first);
        assertTrue(hashSet.contains(second), "HashSet must find an equal account");
        assertEquals(1, hashSet.size(),
                "HashSet must not hold two accounts with the same number");

        Set<BankAccount> treeSet = new TreeSet<>();
        treeSet.add(first);
        assertTrue(treeSet.contains(second),
                "TreeSet must find an account with compareTo == 0");
        assertEquals(1, treeSet.size(),
                "TreeSet must not hold two accounts with the same number");
    }

    @Test
    void compareToZeroIfAndOnlyIfEquals() {
        BankAccount base = account(10000001L, "John", 15000);
        BankAccount sameNumber = account(10000001L, "Mary", 25000);
        BankAccount otherNumber = account(10000002L, "John", 15000);

        assertEquals(0, base.compareTo(sameNumber));
        assertTrue(base.equals(sameNumber), "compareTo == 0 must imply equals");
        assertNotEquals(0, base.compareTo(otherNumber));
        assertNotEquals(base, otherNumber, "equals must imply compareTo == 0");
    }

    private static BankAccount account(long number, String customerName, int balance) {
        return new BankAccount(
                number,
                new Customer(number, customerName, 30,
                        customerName.toLowerCase() + "@example.com", true),
                balance,
                true
        );
    }
}
