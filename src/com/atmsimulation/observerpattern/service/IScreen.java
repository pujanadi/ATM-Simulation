package com.atmsimulation.observerpattern.service;

import com.atmsimulation.observerpattern.ATMObserverMain;

public interface IScreen {
    public ATMObserverMain.Screen getNextScreen();
    public void display();
}
