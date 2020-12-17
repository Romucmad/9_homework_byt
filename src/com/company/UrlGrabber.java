package com.company;

import com.company.memento.CareTaker;
import com.company.memento.Memento;
import com.company.memento.Originator;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class UrlGrabber implements IGrabber {

    private CareTaker careTaker;
    private int indexCurrentMemento = 0;


    public UrlGrabber(CareTaker careTaker) {
        this.careTaker = careTaker;

        if(this.careTaker.mementos.size() > 0){
            this.indexCurrentMemento = this.careTaker.mementos.size()-1;
        }else{
            HashMap<String,UrlObservers> hashMap = new HashMap<>();
            Memento newMemento = new Memento(hashMap);
            this.careTaker.addMemento(newMemento);
        }
    }



    public CareTaker getCareTaker(){
        return this.careTaker;
    }

    public int getCurrentMementoIndex(){
        return  this.indexCurrentMemento;
    }


    public HashMap<String,UrlObservers>  getCurrentSubsMap(){
        return this.careTaker.mementos.get(this.indexCurrentMemento).getMemento();
    }

    @Override
    public void register(IUrlObserver o, String url) {
        Memento newMemento = new Memento((HashMap<String, UrlObservers>) this.careTaker.mementos.get(indexCurrentMemento).getMemento().clone());

        if (!newMemento.getMemento().containsKey(url)) {
            // getS data for website here
            try {
                Date newDate = getLastModified(url);
                newMemento.getMemento().put(url, new UrlObservers(newDate));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        newMemento.getMemento().get(url).addObserver(o);
        this.careTaker.addMemento(newMemento);
        this.indexCurrentMemento++ ;
    }

    @Override
    public void unregister(IUrlObserver o, String url) {
        Memento newMemento = new Memento((HashMap<String, UrlObservers>) this.careTaker.mementos.get(indexCurrentMemento).getMemento().clone());

        if (newMemento.getMemento().containsKey(url)) {
            newMemento.getMemento().get(url).removeObserver(o);

        }
        this.careTaker.addMemento(newMemento);
        this.indexCurrentMemento ++;
    }

    @Override
    public void notifyObservers(String url) {
        for (IUrlObserver subs : this.careTaker.mementos.get(this.indexCurrentMemento).getMemento().get(url).getSubs()) {
            subs.update(url, this.careTaker.mementos.get(this.indexCurrentMemento).getMemento().get(url).getLastModifiedDate());
        }
    }


    public void setNewDate(String url, Date newDate) {

        Memento newMemento = new Memento((HashMap<String, UrlObservers>) this.careTaker.mementos.get(indexCurrentMemento).getMemento().clone());

        if (newDate.after(newMemento.getMemento().get(url).getLastModifiedDate())) {
            newMemento.getMemento().get(url).setDate(newDate);
            this.notifyObservers(url);
        }

        this.careTaker.addMemento(newMemento);
        this.indexCurrentMemento ++;
    }

    public void updateDates() {


        Set<String> keys = this.careTaker.mementos.get(this.indexCurrentMemento).getMemento().keySet();

        for (String s : keys) {
            try {
                Date newDate = this.getLastModified(s);
                this.setNewDate(s, newDate);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public  Date getLastModified(String url) throws IOException {
        URL address = new URL(url);
        URLConnection connect = address.openConnection();
        long longtime = connect.getLastModified();
        Date modifiedDate = new Date(longtime);
        System.out.println("Got new date for " + url + " - " + modifiedDate.toString());
        return modifiedDate;

    }


    public  void undo(){
        if(this.indexCurrentMemento > 0){
            this.careTaker.removeMemento(this.careTaker.mementos.get(this.indexCurrentMemento));
            this.indexCurrentMemento--;
        }else{
            System.out.println("Can not undo");
        }
    }
}
