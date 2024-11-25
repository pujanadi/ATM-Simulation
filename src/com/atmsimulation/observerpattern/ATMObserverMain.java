package com.atmsimulation.observerpattern;


import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.view.*;

import java.util.Arrays;
import java.util.List;

public class ATMObserverMain {
    private static List<Account> accountList;
    private static Screen displayScreen;


    public enum Screen {
        WELCOME_SCREEN, TRANSACTION_SCREEN, WITHDRAW_SCREEN, OTHER_WITHDRAW_SCREEN, SUMMARY_SCREEN, EXIT_SCREEN,
        FUND_TRANSFER_STEP1_SCREEN, FUND_TRANSFER_STEP2_SCREEN, FUND_TRANSFER_STEP3_SCREEN, FUND_TRANSFER_STEP4_SCREEN,
        FUND_TRANSFER_SUMMARY_SCREEN
    }

    public static void main(String[] args) {

        init();

        Account currentAccount = new Account();
        FundTransfer fundTransfer = new FundTransfer();

        do{
            switch (displayScreen) {
                case WELCOME_SCREEN -> {
                    WelcomeScreen currentDisplay = new WelcomeScreen(accountList);
                    currentAccount = currentDisplay.getCurrentAccount();
                    displayScreen = currentDisplay.getNextScreen();
                }
                case TRANSACTION_SCREEN -> {
                    TransactionScreen currentDisplay = new TransactionScreen(currentAccount);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case WITHDRAW_SCREEN -> {
                    WithdrawScreen currentDisplay = new WithdrawScreen(currentAccount);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case OTHER_WITHDRAW_SCREEN -> {
                    OtherWithdrawScreen currentDisplay = new OtherWithdrawScreen(currentAccount);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case SUMMARY_SCREEN -> {
                    SummaryScreen currentDisplay = new SummaryScreen(currentAccount);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case FUND_TRANSFER_STEP1_SCREEN -> {
                    FundTransferStep1Screen currentDisplay = new FundTransferStep1Screen(fundTransfer);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case FUND_TRANSFER_STEP2_SCREEN -> {
                    FundTransferStep2Screen currentDisplay = new FundTransferStep2Screen(fundTransfer);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case FUND_TRANSFER_STEP3_SCREEN -> {
                    FundTransferStep3Screen currentDisplay = new FundTransferStep3Screen(fundTransfer);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case FUND_TRANSFER_STEP4_SCREEN -> {
                    FundTransferStep4Screen currentDisplay = new FundTransferStep4Screen(accountList, currentAccount, fundTransfer);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case FUND_TRANSFER_SUMMARY_SCREEN -> {
                    FundTransferSummaryScreen currentDisplay = new FundTransferSummaryScreen(currentAccount, fundTransfer);
                    displayScreen = currentDisplay.getNextScreen();
                }
                case EXIT_SCREEN -> exitScreen();
            }
        } while(displayScreen != Screen.EXIT_SCREEN);

    }

    public static void init(){
        // Init Screen state
        displayScreen = Screen.WELCOME_SCREEN;

        // Init account data
        accountList = Arrays.asList(
            new Account("John Doe", "012108", 100, "112233"),
            new Account("Jane Doe", "932012", 30, "112244")
        );

    }

    public static void  exitScreen() {
        System.out.println("Thank you");
        System.exit(0);
    }
}
