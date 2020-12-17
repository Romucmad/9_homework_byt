package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class UrlObservers implements Serializable {

    private Date dateModified;
    private final ArrayList<IUrlObserver> observers;


    public UrlObservers(Date date){
        this.dateModified = date;
        this.observers = new ArrayList<>();
    }

    public void  addObserver(IUrlObserver IUrlObserver){
        this.observers.add(IUrlObserver);
    }

    public void  removeObserver(IUrlObserver IUrlObserver){
        this.observers.remove(IUrlObserver);
    }

    public void setDate(Date newDate){
        this.dateModified = newDate;
    }

    public Date getLastModifiedDate(){
        return this.dateModified;
    }

    public ArrayList<IUrlObserver> getSubs(){
        return  this.observers;
    }


}
