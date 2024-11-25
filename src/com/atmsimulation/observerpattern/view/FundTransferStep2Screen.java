package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IScreen;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.Scanner;

public class FundTransferStep2Screen implements IObserver, IScreen {
    private FundTransfer fundTransfer;
    private ATMObserverMain.Screen nextScreen;

    public FundTransferStep2Screen(FundTransfer fundTransfer) {
        this.fundTransfer = fundTransfer;
        fundTransfer.registerObserver(this);
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter transfer amount and press enter to continue or");
        System.out.println("press enter to go back to Transaction:");
        String amountTransfer = sc.next();

        fundTransfer.setAmountFundTransfer(amountTransfer);

        nextScreen = ATMObserverMain.Screen.FUND_TRANSFER_STEP3_SCREEN;
    }
}
