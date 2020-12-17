package com.company.memento;

import com.company.UrlObservers;

import java.io.Serializable;
import java.util.HashMap;

public class Memento implements Serializable {

    private final HashMap<String, UrlObservers> urlUpdates;


    public Memento(HashMap<String, UrlObservers> urlUpdates) {
        this.urlUpdates = urlUpdates;

    }

    public HashMap<String,UrlObservers> getMemento(){
        return this.urlUpdates;
    }
}
