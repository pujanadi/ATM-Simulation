package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IScreen;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.Scanner;

public class FundTransferStep1Screen implements IObserver, IScreen {
    private FundTransfer fundTransfer;
    private ATMObserverMain.Screen nextScreen;

    public FundTransferStep1Screen(FundTransfer fundTransfer){
        this.fundTransfer = fundTransfer;
        fundTransfer.registerObserver(this);
        display();
    }

    @Override
    public ATMObserverMain.Screen getNextScreen() {
        return nextScreen;
    }

    @Override
    public void display() {
        System.out.println("Please enter destination account and press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction:");
        Scanner sc = new Scanner(System.in);
        String beneficiaryAccount = sc.next();

        fundTransfer.setBeneficiaryAccountFundTransfer(beneficiaryAccount);

        nextScreen = ATMObserverMain.Screen.FUND_TRANSFER_STEP2_SCREEN;
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void update(FundTransfer fundTransfer) {

    }
}
