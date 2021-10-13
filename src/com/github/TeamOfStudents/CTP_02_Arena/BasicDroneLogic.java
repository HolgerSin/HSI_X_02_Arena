package com.github.TeamOfStudents.CTP_02_Arena;

// import java.awt.Color;
import java.awt.geom.Point2D;
// import java.awt.Point;
import java.util.ArrayList;

public class BasicDroneLogic extends DroneLogic {

    public CommandSet calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {
        double requiredHeading;
        Point2D.Double position = super.getLocation();

        Waypoint myWaypoint = wayPointList.get(0);
        if (position.distance(myWaypoint) < 50) {
            wayPointList.remove(0);
        }
        requiredHeading = Arena.calcRotationAngleInDegrees(this.getLocation(), myWaypoint);

        // System.out.println(firstWaypoint);
        // System.out.println(this.getLocation());
       

        return new CommandSet((int) requiredHeading, 1);
    }
}