package com.atmsimulation.observerpattern.entity;

import com.atmsimulation.observerpattern.service.IAccount;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Account implements IAccount {
    private List<IObserver> observers;
    private String name;
    private String pin;
    private int balance;
    private String accountNumber;
    private int lastWithdrawAmount;

    public Account(){
        observers = new ArrayList<IObserver>();
    }

    public Account(String name, String pin, int balance, String accountNumber){
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.lastWithdrawAmount = 0;
        observers = new ArrayList<IObserver>();
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

    public int getLastWithdrawAmount() {
        return lastWithdrawAmount;
    }

    public void setLastWithdrawAmount(int lastWithdrawAmount) {
        this.lastWithdrawAmount = lastWithdrawAmount;
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.update(this);
        }
    }

    public void accountChanged(){
        notifyObservers();
    }

    public void withdraw(int amount){
        this.balance -= amount;
        accountChanged();
    }

    public void fundTransfer(int amount){
        this.balance -= amount;
        accountChanged();
    }

    public void creditAccount(int amount){
        this.balance += amount;
        accountChanged();
    }

    public static boolean validateAccountNumber(String accountNumber) {
        return (accountNumber != null) && (accountNumber.length() == 6) && (accountNumber.matches("[0-9]+"));
    }


}
