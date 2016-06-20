package com.example.downa.przypominajka;

/**
 * Created by downa on 20.06.2016.
 */
public class Cords {

    public static Reminder finger;
    private static double pion;
    private static double poziom;


    public static void initialize(double pion, double poziom) {
        Cords.pion = pion;
        Cords.poziom = poziom;
    }

    public static double getPion() {
        return pion;
    }


    public static double getPoziom() {
        return poziom;
    }


}
