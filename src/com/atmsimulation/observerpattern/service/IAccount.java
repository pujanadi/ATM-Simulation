package com.atmsimulation.observerpattern.service;


public interface IAccount {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}
