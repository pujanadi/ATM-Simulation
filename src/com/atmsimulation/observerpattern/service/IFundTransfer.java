package com.atmsimulation.observerpattern.service;

public interface IFundTransfer {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
