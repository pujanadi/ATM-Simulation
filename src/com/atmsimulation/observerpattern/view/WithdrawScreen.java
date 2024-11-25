package com.atmsimulation.observerpattern.view;


import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;
import com.atmsimulation.observerpattern.service.IScreen;

import java.util.Scanner;

public class WithdrawScreen implements IObserver, IScreen {
    private Account account;
    private ATMObserverMain.Screen nextScreen;
    private int withdrawAmount;

    public WithdrawScreen(Account account) {
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
        int nominal1 = 10;
        int nominal2 = 50;
        int nominal3 = 100;
        Scanner sc = new Scanner(System.in);

        System.out.println("1. $" + nominal1);
        System.out.println("2. $" + nominal2);
        System.out.println("3. $" + nominal3);
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.println("Please choose option[5]:");
        int option = sc.nextInt();

        switch (option){
            case 1, 2, 3 -> {
                // Deduct user balance
                int balance = account.getBalance();
                if(option == 1) {
                    balance = balance - nominal1;
                    withdrawAmount = nominal1;
                } else if(option == 2) {
                    balance = balance - nominal2;
                    withdrawAmount = nominal2;
                } else if(option == 3) {
                    balance = balance - nominal3;
                    withdrawAmount = nominal3;
                }

                System.out.println("withdraw amount: " + withdrawAmount);
                if(balance < 0) {
                    System.out.println("Insufficient balance $"+account.getBalance());
                    nextScreen = ATMObserverMain.Screen.WITHDRAW_SCREEN;
                }
                else {
                    account.withdraw(withdrawAmount);
                    account.setLastWithdrawAmount(withdrawAmount);
                }
                System.out.println("balance after withdraw:"+account.getBalance());
                nextScreen = ATMObserverMain.Screen.SUMMARY_SCREEN;
            }
            case 4 -> {
                nextScreen = ATMObserverMain.Screen.OTHER_WITHDRAW_SCREEN;
            }
            default -> {
                nextScreen = ATMObserverMain.Screen.TRANSACTION_SCREEN;
            }

        }
    }
}
