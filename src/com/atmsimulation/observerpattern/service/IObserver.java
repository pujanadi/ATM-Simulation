package com.atmsimulation.observerpattern.service;

import com.atmsimulation.observerpattern.entity.Account;
import com.atmsimulation.observerpattern.entity.FundTransfer;

public interface IObserver {
    public void update(Account account);
    public void update(FundTransfer fundTransfer);
}
