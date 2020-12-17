package com.company;

import com.company.memento.CareTaker;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CareTaker careTaker  = new CareTaker();
        UrlGrabber urlGrabber = new UrlGrabber(careTaker);

        UrlObserver roman = new UrlObserver("Roman Hrybinchuk");
        UrlObserver donald = new UrlObserver("Donald Trump");


        urlGrabber.register(donald,"https://www.google.com/");
        urlGrabber.register(donald,"http://www.pja.edu.pl/");

        urlGrabber.register(roman,"http://www.pja.edu.pl/");
        urlGrabber.register(donald,"https://www.theguardian.com/world/2020/dec/17/french-president-emmanuel-macron-tests-positive-for-coronavirus");


        while(true){
            System.out.println("New cycle");
            urlGrabber.updateDates();
            Thread.sleep(20 * 1000);

        }

    }
}
