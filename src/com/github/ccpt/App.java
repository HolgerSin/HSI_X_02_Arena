package com.github.ccpt;
import java.awt.Rectangle;

import javax.swing.JFrame;
import java.awt.geom.Point2D;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello");

        Arena myArena = new Arena(1500, 1000);

        // myArena.run();


        double droneGroundTrack=90;
        double droneGroundSpeed=50;
        double TICS_PER_SECOND=1;

        double droneTrackX = Math.sin(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;
        double droneTrackY = Math.cos(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;

        for (int i = 0; i < 180; i++) {
            
        
        int windDirection=120;
        double windSpeed=10;
        // the wind movement per frame in both axis
        double dXwind = Math.sin(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;
        double dYwind = Math.cos(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;

        double xWind = Math.sin(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;
        double hWind = Math.cos(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;

        System.out.println(xWind);
        
        }
        
        System.out.println("Bye");
    }
}
