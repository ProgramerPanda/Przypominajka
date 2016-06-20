package com.example.downa.przypominajka;

/**
 * Created by downa on 13.06.2016.
 */

import java.io.Serializable;

public class Reminder implements Serializable {

    private String name;
    private String place;
    private double pion;
    private double poziom;

    public double getPion() {
        return pion;
    }

    public void setPion(double pion) {
        this.pion = pion;
    }

    public double getPoziom() {
        return poziom;
    }

    public void setPoziom(double poziom) {
        this.poziom = poziom;
    }


    public Reminder(String name, String place, double pion, double poziom) {
        this.name = name;
        this.place = place;
        this.pion = pion;
        this.poziom = poziom;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}
