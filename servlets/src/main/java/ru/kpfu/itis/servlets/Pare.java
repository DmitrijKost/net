package ru.kpfu.itis.servlets;

/**
* Created with IntelliJ IDEA.
* User: связной
* Date: 30.11.13
* Time: 16:27
* To change this template use File | Settings | File Templates.
*/
public class Pare {
    private int id;
    private  Double power;

    public Pare() {
        this(0, null);
    }

    public Pare(int id, Double power) {
        this.id = id;
        this.power = power;
    }

    public int getId() {
        return id;
    }

    public Double getPower() {
        return power;
    }

    public void setId(int id) {
        this.id += id;
    }
}
