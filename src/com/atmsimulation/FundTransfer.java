package com.atmsimulation;

import java.util.Date;

public class FundTransfer {
    private Account sourceAccount;
//    private Account beneficiaryAccount;
    private String beneficiaryAccount;
    private String referenceNumber;
    private String amount;
    private Date date;

    public FundTransfer() {
        sourceAccount = null;
        beneficiaryAccount = null;
        amount = "";
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

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
