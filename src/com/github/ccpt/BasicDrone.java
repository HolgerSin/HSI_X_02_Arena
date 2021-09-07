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
        requiredHeading = calcRotationAngleInDegrees(this.getLocation(), myWaypoint);
        
        
        // System.out.println(firstWaypoint);
        // System.out.println(this.getLocation());
        // latestCommand[0] = (((int) timeIndex / 5) % 2) * 180 + 90;
        
        latestCommandHeading = (int)requiredHeading;
        latestCommandSpeed = requestedSpeed;

        // System.out.println("cmdHDG: "+ latestCommandHeading+ " GS: " + (int)getGroundSpeed());
    }

    /**
     * Calculates the angle from centerPt to targetPt in degrees. The return should
     * range from [0,360), rotating CLOCKWISE, 0 and 360 degrees represents NORTH,
     * 90 degrees represents EAST, etc...
     *
     * Assumes all points are in the same coordinate space. If they are not, you
     * will need to call SwingUtilities.convertPointToScreen or equivalent on all
     * arguments before passing them to this function.
     *
     * @param centerPt Point we are rotating around.
     * @param targetPt Point we want to calcuate the angle to.
     * @return angle in degrees. This is the angle from centerPt to targetPt.
     */
    public static double calcRotationAngleInDegrees(Point2D.Double centerPt, Point2D.Double targetPt) {
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently points EAST.
        // NOTE: By preserving Y and X param order to atan2, we are expecting
        // a CLOCKWISE angle direction.
        // double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);  //orginal
        
        double theta = Math.atan2(targetPt.x - centerPt.x, targetPt.y - centerPt.y); 
        
        // double theta = Math.atan2(centerPt.y - targetPt.y, centerPt.x - targetPt.x);
        // System.out.println("    X" + (targetPt.x - centerPt.x)+"   Y:" + (targetPt.y - centerPt.y));
        // rotate the theta angle clockwise by 90 degrees
        // (this makes 0 point NORTH)
        // NOTE: adding to an angle rotates it clockwise.
        // subtracting would rotate it counter-clockwise
        // theta -= Math.PI / 2.0;

        // convert from radians to degrees
        // this will give you an angle from [0->270],[-180,0]
        double angle = Math.toDegrees(theta);

        // convert to positive range [0-360)
        // since we want to prevent negative angles, adjust them now.
        // we can assume that atan2 will not return a negative value
        // greater than one partial rotation
        if (angle < 0) {
            angle += 360;
        }

        return angle;
    }

}
