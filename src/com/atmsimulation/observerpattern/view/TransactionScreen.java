package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;
import com.atmsimulation.observerpattern.service.IScreen;

import java.util.List;
import java.util.Scanner;

public class TransactionScreen implements IObserver, IScreen {
    private List<Account> accountList;
    private Account account;
    private ATMObserverMain.Screen nextScreen;

    public TransactionScreen(Account account) {
        this.account = account;
        account.registerObserver(this);
        display();
    }

    @Override
    public void update(Account account) {
        // nothing to be updated here
    }

    @Override
    public void update(FundTransfer fundTransfer) {

    }

    @Override
    public ATMObserverMain.Screen getNextScreen() {
        return nextScreen;
    }

    @Override
    public void display() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.println("Please choose option[3]:");
        int option = sc.nextInt();

        switch (option) {
            case 1 -> nextScreen = ATMObserverMain.Screen.WITHDRAW_SCREEN;
            case 2 -> nextScreen = ATMObserverMain.Screen.FUND_TRANSFER_STEP1_SCREEN;
            default -> nextScreen = ATMObserverMain.Screen.EXIT_SCREEN;
        };
    }
}
