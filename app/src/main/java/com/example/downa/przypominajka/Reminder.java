package com.example.downa.przypominajka;

/**
 * Created by downa on 13.06.2016.
 */

import java.io.Serializable;

public class Reminder implements Serializable {

    private String name;
    private String place;

    public Reminder(String name, String place){
        this.name = name;
        this.place =place;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }
}
