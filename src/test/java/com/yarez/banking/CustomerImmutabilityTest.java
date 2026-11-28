package com.yarez.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerImmutabilityTest {

    @Test
    void mutatingOriginalCustomerMustNotChangeAccountDisplay() throws Exception {
        Customer customer = new Customer(
                233785885L, "John", 35, "john@example.com", true);
        BankAccount account = new BankAccount(10000001L, customer, 10000, true);

        String before = captureOutput(account::displayAccountInformation);

        // If Customer has no setName, it is already immutable and this is a no-op.
        try {
            Method setter = Customer.class.getMethod("setName", String.class);
            setter.invoke(customer, "Changed");
        } catch (NoSuchMethodException immutableCustomer) {
            return;
        }

        String after = captureOutput(account::displayAccountInformation);

        assertEquals(before, after,
                "changing the original Customer must not change the account display");
        assertEquals("John", account.getCustomer().getName(),
                "the account must keep the customer data it was created with");
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
        return bytes.toString(StandardCharsets.UTF_8).replace("\r\n", "\n");
    }
}
