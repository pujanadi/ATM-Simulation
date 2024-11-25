package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;
import com.atmsimulation.observerpattern.service.IScreen;

import java.util.List;
import java.util.Scanner;

public class WelcomeScreen implements IObserver, IScreen {
    private List<Account> accountList;
    private Account account;
    private ATMObserverMain.Screen nextScreen;

    public WelcomeScreen(List<Account> accountList){
        this.accountList = accountList;
        display();
    }

    @Override
    public void update(Account account) {
    }

    @Override
    public void update(FundTransfer fundTransfer) {

    }

    @Override
    public void display() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number: ");
        String accountNumber = sc.next();
        System.out.println("Enter PIN: ");
        String pin = sc.next();

        // authentication
        if(authentication(accountNumber, pin)) {
            System.out.println("Account successfully authenticated");
            this.nextScreen = ATMObserverMain.Screen.TRANSACTION_SCREEN;
        } else {
            System.out.println("Invalid Account Number/PIN");
            nextScreen = ATMObserverMain.Screen.WELCOME_SCREEN;
        }
    }

    @Override
    public ATMObserverMain.Screen getNextScreen() {
        return nextScreen;
    }

    public boolean authentication(String accountNumber, String pin) {

        boolean isValidAccountNumber = com.atmsimulation.Account.validateAccountNumber(accountNumber);
        boolean isValidPin = com.atmsimulation.Account.validatePin(pin);
        boolean isValidAccNumAndPin = false;
        for(Account acc : accountList) {
            if(acc.getAccountNumber().equals(accountNumber) && acc.getPin().equals(pin)) {
                isValidAccNumAndPin = true;
                account = acc;
                account.registerObserver(this);
            }
        }

        return isValidAccountNumber && isValidPin && isValidAccNumAndPin;
    }

    public Account getCurrentAccount() {
        return account;
    }
}
