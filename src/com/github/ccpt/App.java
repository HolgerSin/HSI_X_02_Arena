package com.github.ccpt;
import java.awt.Rectangle;

import javax.swing.JFrame;
import java.awt.geom.Point2D;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello");

        Arena myArena = new Arena(1500, 1000);

        myArena.run();


        // double droneGroundTrack=10;
        // double droneGroundSpeed=50;
        // double TICS_PER_SECOND=1;

        // double droneTrackX = Math.sin(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;
        // double droneTrackY = Math.cos(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;

        // for (int i = 0; i < 72; i++) {
            
            
        //     int windDirection=10*i;
        //     double windSpeed=10;
        //     // the wind movement per frame in both axis
        //     double dXwind = Math.sin(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;
        //     double dYwind = Math.cos(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;

        //     double xWind = Math.sin(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;
        //     double hWind = Math.cos(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;

        //     System.out.println("WindDirection: "+ windDirection + " xWind: " + xWind);
        //     System.out.println("WindDirection: "+ windDirection + " hWind: " + hWind);
        
        // }
        
        System.out.println("Bye");
    }
}
