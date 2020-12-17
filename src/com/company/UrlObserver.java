package com.company;

import java.io.Serializable;
import java.util.Date;

public class UrlObserver implements  IUrlObserver, Serializable {

    private final String name;

    public UrlObserver(String name){
        this.name = name;
    }
    @Override
    public String update(String url, Date dateUpdated) {
        String message = "Subscriber "+name+" has got update that "+url+" has been updated to "+dateUpdated.toString();
        System.out.println(message);
        return message;
    }
}
