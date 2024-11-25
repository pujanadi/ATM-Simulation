package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;
import com.atmsimulation.observerpattern.service.IScreen;

import java.util.Scanner;

public class OtherWithdrawScreen implements IObserver, IScreen {
    private Account account;
    private ATMObserverMain.Screen nextScreen;

    public OtherWithdrawScreen(Account account) {
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
        System.out.println("Other Withdraw");
        System.out.println("Enter amount to withdraw:");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();

        if(amount > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            nextScreen = ATMObserverMain.Screen.OTHER_WITHDRAW_SCREEN;
        } else if (amount % 10 > 0){
            System.out.println("Invalid ammount");
            nextScreen = ATMObserverMain.Screen.OTHER_WITHDRAW_SCREEN;
        } else if (amount > account.getBalance()) {
            System.out.println("Insufficient balance $" + account.getBalance());
            nextScreen = ATMObserverMain.Screen.OTHER_WITHDRAW_SCREEN;
        } else{
            account.withdraw(amount);

            nextScreen = ATMObserverMain.Screen.SUMMARY_SCREEN;
        }

    }
}
