package com.company.memento;

import com.company.UrlObservers;

import java.util.HashMap;

public class Originator {

    private  HashMap<String, UrlObservers> urlUpdates;


    public Originator(HashMap<String, UrlObservers> urlUpdates) {
        this.urlUpdates = urlUpdates;
    }

    public void set( HashMap<String, UrlObservers> urlUpdates){
        this.urlUpdates = urlUpdates;
    }

    public Memento storeInMemento(){
        return  new Memento(urlUpdates);
    }

    public HashMap<String,UrlObservers> restoreFromMemento(Memento memento){
        this.urlUpdates = memento.getMemento();
        return  memento.getMemento();
    }
}
