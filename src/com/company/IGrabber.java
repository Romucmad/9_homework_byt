package com.company;

public interface IGrabber {

    public void register(IUrlObserver o, String url);
    public void unregister(IUrlObserver o, String url);
    public void notifyObservers(String url);



}
