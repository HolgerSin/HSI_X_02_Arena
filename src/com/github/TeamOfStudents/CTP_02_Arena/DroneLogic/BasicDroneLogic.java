package com.github.TeamOfStudents.CTP_02_Arena.DroneLogic;

// import java.awt.Color;
import java.awt.geom.Point2D;
// import java.awt.Point;
// import java.util.ArrayList;

import com.github.TeamOfStudents.CTP_02_Arena.Arena;
import com.github.TeamOfStudents.CTP_02_Arena.CommandSet;
import com.github.TeamOfStudents.CTP_02_Arena.Waypoint;

public class BasicDroneLogic extends DroneLogic {

    public CommandSet calculateNewCommand(double timeIndex, Waypoint waypoint) {
        double requiredHeading;
        Point2D.Double position = super.getLocation();

       
        // if (position.distance(waypoint) < 50) {
            
        // }
        requiredHeading = Arena.calcRotationAngleInDegrees(position, waypoint);

        // System.out.println(firstWaypoint);
        // System.out.println(this.getLocation());
       

        return new CommandSet((int) requiredHeading, 1);
    }
}