package com.atmsimulation;

public class Account {
    private String name;
    private String pin;
    private int balance;
    private String accountNumber;

    public Account() { }

    public Account(String name, String pin, int balance, String accountNumber) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public static boolean validateAccountNumber(String accountNumber) {
        return (accountNumber != null) && (accountNumber.length() == 6) && (accountNumber.matches("[0-9]+"));
    }

    public static boolean validatePin(String pin) {
        return (pin != null) && (pin.length() == 6) && (pin.matches("[0-9]+"));
    }
}
