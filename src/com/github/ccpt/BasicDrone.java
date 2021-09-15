package com.github.ccpt;

import java.awt.Color;
import java.awt.geom.Point2D;
// import java.awt.Point;
import java.util.ArrayList;

public class BasicDrone extends Drone {

    public BasicDrone(String name, Point2D.Double position, Color mycolor) {
        super(name, position, mycolor);
        // TODO Auto-generated constructor stub
    }

    public void calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {
        double requiredHeading;
        

        Waypoint myWaypoint = wayPointList.get(0);
        if (this.getLocation().distance(myWaypoint) < 50) {
            wayPointList.remove(0);
        }
        requiredHeading = Arena.calcRotationAngleInDegrees(this.getLocation(), myWaypoint);
        
        
        // System.out.println(firstWaypoint);
        // System.out.println(this.getLocation());
        // latestCommand[0] = (((int) timeIndex / 5) % 2) * 180 + 90;
        
        latestCommandHeading = (int)requiredHeading;
        // latestCommandSpeed = requestedSpeed;
        latestCommandThrust = 1;

        // System.out.println("cmdHDG: "+ latestCommandHeading+ " GS: " + (int)getGroundSpeed());
    }
}