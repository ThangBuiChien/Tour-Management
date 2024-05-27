package com.example.tourmanagement.observer;

import com.example.tourmanagement.model.Invoice;

public interface Subject {
    void attachObserver(Observer o);
    void detachObserver(Observer o);
    void notifyObservers(Invoice invoice);
}
