package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;
import com.atmsimulation.observerpattern.service.IScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class SummaryScreen implements IObserver, IScreen {
    private Account account;
    private ATMObserverMain.Screen nextScreen;


    public SummaryScreen(Account account) {
        this.account = account;
        account.registerObserver(this);
        display();
    }

    @Override
    public void update(Account account) {

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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:dd");
        String dateDisplay = LocalDateTime.now().format(dtf);

        System.out.println("Summary");
        System.out.println("Date : " + dateDisplay);
        System.out.println("Withdraw : $" + account.getLastWithdrawAmount());
        System.out.println("Balance : $" + account.getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]:");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        nextScreen = option == 1 ? ATMObserverMain.Screen.TRANSACTION_SCREEN : ATMObserverMain.Screen.EXIT_SCREEN;
    }
}
