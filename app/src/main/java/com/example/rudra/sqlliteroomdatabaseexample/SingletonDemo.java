package com.example.rudra.sqlliteroomdatabaseexample;

public class SingletonDemo {

    private static SingletonDemo instance;

    private SingletonDemo() {

    }

    public synchronized static SingletonDemo getInstance() {
        if(instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }


}
