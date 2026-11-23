package com.yarez.banking;

public class Customer {

    //1. Fields
    private long id;
    private String name;
    private int age;
    private String email;
    private boolean active;

    public Customer() {
    }

    //2. constructor
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

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //3. Display customer information
    public void displayInformation(){
        System.out.println("===== customer information ======");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: "+ email);
        System.out.println("Active:"+ active);
    }

}
