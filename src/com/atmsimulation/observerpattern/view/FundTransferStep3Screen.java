package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IScreen;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.Random;

public class FundTransferStep3Screen implements IObserver, IScreen {
    private FundTransfer fundTransfer;
    private ATMObserverMain.Screen nextScreen;

    public FundTransferStep3Screen(FundTransfer fundTransfer) {
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
        Random rand = new Random();
        int referenceNumber = rand.nextInt(999999);
        System.out.println("Reference Number: " + String.format("%06d", referenceNumber));
        System.out.println("press enter to continue or press enter to go back to Transaction:");

        fundTransfer.setReferenceNumberFundTransfer(String.valueOf(referenceNumber));

        nextScreen = ATMObserverMain.Screen.FUND_TRANSFER_STEP4_SCREEN;
    }
}
