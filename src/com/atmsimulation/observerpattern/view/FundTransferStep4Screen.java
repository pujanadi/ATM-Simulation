package com.atmsimulation.observerpattern.view;

import com.atmsimulation.observerpattern.ATMObserverMain;
import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;
import com.atmsimulation.observerpattern.service.IScreen;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.List;
import java.util.Scanner;

public class FundTransferStep4Screen implements IObserver, IScreen {
    private List<Account> accountList;
    private Account account;
    private FundTransfer fundTransfer;
    private ATMObserverMain.Screen nextScreen;

    public FundTransferStep4Screen(List<Account> accountList, Account account, FundTransfer fundTransfer) {
        this.accountList = accountList;
        this.account = account;
        this.fundTransfer = fundTransfer;
        account.registerObserver(this);
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
        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account : " + fundTransfer.getBeneficiaryAccount());
        System.out.println("Transfer Amount     : $" + fundTransfer.getAmount());
        System.out.println("Reference Number    : " + fundTransfer.getReferenceNumber());
        System.out.println("\r\n1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.println("Choose option[2]:");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();
        boolean isValidTransaction = (option == 1) ? validateFundTransferAmount(account, fundTransfer) : false;

        nextScreen = isValidTransaction ? ATMObserverMain.Screen.FUND_TRANSFER_SUMMARY_SCREEN : ATMObserverMain.Screen.TRANSACTION_SCREEN;
    }

    boolean validateFundTransferAmount(Account account, FundTransfer fundTransfer) {
        boolean isValidTransaction = false;

        if(!fundTransfer.getBeneficiaryAccount().matches("\\d+")) System.out.println("Invalid account");
        else if(!Account.validateAccountNumber(fundTransfer.getBeneficiaryAccount())) System.out.println("Invalid account");
        else if(Integer.parseInt(fundTransfer.getAmount()) > 1000)  System.out.println("Maximum amount to transfer is $1000");
        else if(Integer.parseInt(fundTransfer.getAmount()) < 1) System.out.println("Minimum amount to transfer is $1");
        else if(!fundTransfer.getAmount().matches("\\d+")) System.out.println("Invalid amount");
        else if((account.getBalance() < Integer.parseInt(fundTransfer.getAmount()))) System.out.println("Insufficient balance");
        else {
            Account destAccount = findAccountByAccountNumber(fundTransfer.getBeneficiaryAccount());
            if(destAccount != null) {
                doTransaction(destAccount);
            }

            isValidTransaction = true;
        }

        return isValidTransaction;
    }

    void doTransaction(Account destAccount) {
        // Deducted source account balance
        account.fundTransfer(Integer.parseInt(fundTransfer.getAmount()));

        // Credit to destination account
        destAccount.creditAccount(Integer.parseInt(fundTransfer.getAmount()));

        // Update account
        updateAccount(accountList, destAccount);
    }

    Account findAccountByAccountNumber(String accountNumber) {
        for(Account acc : accountList) {
            if(acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }

        return new Account();
    }

    void updateAccount(List<Account> accountList, Account updatedAccount) {
        accountList.replaceAll(acc -> acc.getAccountNumber() == updatedAccount.getAccountNumber() ? updatedAccount : acc);
    }


}
