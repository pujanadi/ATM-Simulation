package com.atmsimulation.observerpattern.entity;

import com.atmsimulation.observerpattern.service.IFundTransfer;
import com.atmsimulation.observerpattern.service.IObserver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundTransfer implements IFundTransfer {
    private List<IObserver> observers;
    private Account sourceAccount;
    private String beneficiaryAccount;
    private String referenceNumber;
    private String amount;
    private Date date;

    public FundTransfer() {
        observers = new ArrayList<IObserver>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.update(this);
        }
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public String getBeneficiaryAccount() {
        return beneficiaryAccount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setBeneficiaryAccountFundTransfer(String BeneficiaryAccount) {
        this.beneficiaryAccount = BeneficiaryAccount;
        notifyObservers();
    }

    public void setAmountFundTransfer(String Amount) {
        this.amount = Amount;
        notifyObservers();
    }

    public void setReferenceNumberFundTransfer(String ReferenceNumber) {
        this.referenceNumber = ReferenceNumber;
        notifyObservers();
    }
}
