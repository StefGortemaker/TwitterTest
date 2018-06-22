package com.example.tweeter.model;

public abstract class BaseEntity {

    private int startIndice;
    private int endIndice;

    public BaseEntity(int startIndice, int endIndice){
        this.startIndice = startIndice;
        this.endIndice = endIndice;
    }

    public int getStartIndice() {
        return startIndice;
    }

    public int getEndIndice() {
        return endIndice;
    }

    public void setStartIndice(int startIndice) {
        this.startIndice = startIndice;
    }

    public void setEndIndice(int endIndice) {
        this.endIndice = endIndice;
    }
}
