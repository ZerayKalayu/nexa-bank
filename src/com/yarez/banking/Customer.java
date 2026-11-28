package com.yarez.banking;

/**
 * Immutable customer data. The no-arg constructor keeps the original defaults,
 * and the all-args constructor is the only way to set real values.
 */
public final class Customer {

    private final long id;
    private final String name;
    private final int age;
    private final String email;
    private final boolean active;

    public Customer() {
        this(0L, null, 0, null, false);
    }

    public Customer(long id, String name, int age, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.active = active;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActive() {
        return active;
    }

    public void displayInformation(){
        System.out.println("===== customer information ======");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: "+ email);
        System.out.println("Active:"+ active);
    }
}
