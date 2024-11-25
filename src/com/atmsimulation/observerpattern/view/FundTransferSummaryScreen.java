package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IScreen;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.Scanner;

public class FundTransferSummaryScreen implements IObserver, IScreen {
    private Account account;
    private FundTransfer fundTransfer;
    private ATMObserverMain.Screen nextScreen;

    public FundTransferSummaryScreen(Account account, FundTransfer fundTransfer) {
        this.account = account;
        this.fundTransfer = fundTransfer;
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
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : " + fundTransfer.getBeneficiaryAccount());
        System.out.println("Transfer Amount     : $" + fundTransfer.getAmount());
        System.out.println("Reference Number    : " + fundTransfer.getReferenceNumber());
        System.out.println("Balance             : $" + account.getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]:");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        nextScreen = option == 1 ? ATMObserverMain.Screen.TRANSACTION_SCREEN : ATMObserverMain.Screen.EXIT_SCREEN;
    }
}
