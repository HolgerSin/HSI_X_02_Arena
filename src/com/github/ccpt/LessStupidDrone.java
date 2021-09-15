package com.github.ccpt;

import java.awt.Color;
import java.awt.geom.Point2D;
// import java.awt.Point;
import java.util.ArrayList;

public class LessStupidDrone extends Drone {

    public LessStupidDrone(String name, Point2D.Double position, Color mycolor) {
        super(name, position, mycolor);
        // TODO Auto-generated constructor stub
    }

    public void calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {
        double requiredHeading;
        
        Waypoint firstWaypoint = wayPointList.get(0);
        // requiredHeading = calcRotationAngleInDegrees(this.getLocation(), firstWaypoint);
        // System.out.println(requiredHeading);
        
        // System.out.println(firstWaypoint);
        // System.out.println(this.getLocation());
        double dX = firstWaypoint.x - this.getX();
        double dY = firstWaypoint.y - this.getY();
        

        if (Math.abs(dX) > Math.abs(dY)) {
            if (dX > 0) {
                requiredHeading = 90;
            } else {
                requiredHeading = 270;
            }
        } else {
            if (dY > 0) {
                requiredHeading = 0;
            } else {
                requiredHeading = 180;
            }
            
        }
        // latestCommand[0] = (((int) timeIndex / 5) % 2) * 180 + 90;
        latestCommandHeading = (int)requiredHeading;
        // latestCommandSpeed = requestedSpeed;
        latestCommandThrust = 1;
        
    }


}
