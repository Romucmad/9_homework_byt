package com.company.memento;

import java.io.*;
import java.util.ArrayList;

public class CareTaker {

    public ArrayList<Memento> mementos;
    private final String FILE_NAME = "mem.bin";

    public CareTaker() {
        try {
        ObjectInputStream is = new ObjectInputStream( new FileInputStream(this.FILE_NAME));
            this.mementos = (ArrayList<Memento>) is.readObject();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Memory pool created");
            this.mementos = new ArrayList<>();
        }
    }

    public void addMemento(Memento newMemento) {
        try {
            this.mementos.add(newMemento);
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream(this.FILE_NAME));
            os.writeObject(mementos);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeMemento(Memento newMemento) {
        try {
            this.mementos.remove(newMemento);
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream(this.FILE_NAME));
            os.writeObject(mementos);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Memento getMemento(int index) {
        return this.mementos.get(index);
    }



}
