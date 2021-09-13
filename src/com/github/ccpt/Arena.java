package com.github.ccpt;

import java.awt.Color;
// import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Arena {

    private int size_X;
    private int size_Y;
    private double timeIndex = 0.0;
    private int ticCounter = 0;
    private static final int TICS_PER_SECOND = 1;

    private int windDirection = 180;
    private int windSpeed = 10;

    /** Air Density in kg/m³ */
    private double airDensity = 1.2;
    /** Coefficient of Drag from front/ German: Cw Wert */
    private double cDfront = 0.2;
    private double cDcross = 0.2;
    /**
     * Cross Section Area of Drone in m²
     * <p>
     * used to determine the drag force Fd
     */
    private double crossSectionAreaFwd = 1; // in m²
    private double crossSectionAreaCross = 1; // in m²

    private ArrayList<Waypoint> wayPointList = new ArrayList<Waypoint>();
    private ArrayList<Drone> droneList = new ArrayList<Drone>();

    private boolean running = true;
    

    public Arena(int size_X, int size_Y) {
        this.size_X = size_X;
        this.size_Y = size_Y;
    }

    public void run() {

        Drone myDrone = new Drone("FirstDrone", new Point2D.Double(500, 500), Color.BLUE);
        Drone droneB = new LessStupidDrone("SecondDrone", new Point2D.Double(700, 500), Color.RED);
        droneList.add(myDrone);
        droneList.add(droneB);
        droneList.add(new BasicDrone("BasicDrone", new Point2D.Double(300, 500), new Color(0, 100, 0)));

        wayPointList.add(new Waypoint("WP1", 500, 500, 100));
        wayPointList.add(new Waypoint("WP2", 700, 500, 100));
        wayPointList.add(new Waypoint("WP3", 200, 200, 100));
        wayPointList.add(new Waypoint("WP4", size_X - 200, 200, 100));
        wayPointList.add(new Waypoint("WP5", size_X - 200, size_Y - 200, 100));
        wayPointList.add(new Waypoint("WP6", 200, size_Y - 200, 100));

        JFrame f = new JFrame();
        DrawPanel dp = new DrawPanel(droneList, wayPointList);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(size_X, size_Y);
        f.add(dp);
        f.setVisible(true);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (running) {

            try {
                Thread.sleep(1000 / TICS_PER_SECOND);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // dp.mr.setBounds(800, 200+i, 200, 200);

            for (Drone drone : droneList) {
                calculateDronePosition(drone);
            }
            // System.out.println(timeIndex);

            // if (ticCounter % TICS_PER_SECOND == 0) {
            // timeIndex++;
            // }
            ticCounter++;
            timeIndex = (double) ticCounter / (double) TICS_PER_SECOND;
            dp.repaint();

        }
    }

    private void calculateDronePosition(Drone drone) {

        if (ticCounter % TICS_PER_SECOND == 0) {
            drone.calculateNewCommand(timeIndex, wayPointList);
        }

        int commandedHeading = drone.getLatestCommandHeading();
        int commandedSpeed = drone.getLatestCommandSpeed();
        double commandedThrust = drone.getLatestCommandThrust();
        double droneGroundSpeed = drone.getGroundSpeed();
        double droneGroundTrack = drone.getGroundTrack();
        int droneThrustHorizontal = drone.getThrustHorizontal();
        int droneMass = drone.getMass();

        String droneName = drone.getName();

         // change of droneSpeed in m/s² due to thrust
         double thrustSpeedChange = droneThrustHorizontal * commandedThrust / droneMass;
         // contains the drones change in the velocity vector due to acceleration ----
         // this is not equal to the position change
         double accelerationVectorX = Math.sin(Math.toRadians(commandedHeading)) * thrustSpeedChange
                 / (TICS_PER_SECOND * TICS_PER_SECOND);
         double accelerationVectorY = Math.cos(Math.toRadians(commandedHeading)) * thrustSpeedChange
                 / (TICS_PER_SECOND * TICS_PER_SECOND);
 
         // contains the drone displacement in both axis caused by acceleration (==
         // thrust == change of track/speed due to drone actions)
         double accelerationPosChangeX = accelerationVectorX * 0.5;
         double accelerationPosChangeY = accelerationVectorY * 0.5;
 

        // the drone displacement per frame in both axis caused by inertia (previous
        // ground speed+track)
                    double droneTrackX = Math.sin(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;
                    double droneTrackY = Math.cos(Math.toRadians(droneGroundTrack)) * droneGroundSpeed / TICS_PER_SECOND;

        // the wind movement per frame in both axis
                    double dXwind = Math.sin(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;
                    double dYwind = Math.cos(Math.toRadians((windDirection + 180) % 360)) * windSpeed / TICS_PER_SECOND;

        // the air vector of the drone = movement of drone relative to air mass
        double droneAirVectorX = droneTrackX - dXwind;
        double droneAirVectorY = droneTrackY - dYwind;

       
        // the movement of the air mass per frame relative to the drone in both axis
        //             double relVelOfAirToDroneX = dXwind - droneTrackX;// - accelerationPosChangeX;   // using accelerationPosChangeX probably inaccurate, maybe use accelerationVectorX / 3
        //             double relVelOfAirToDroneY = dYwind - droneTrackY;// - accelerationPosChangeY;
        // double relVelOfAirToDroneX = dXwind - droneTrackX - accelerationVectorX;  
        // double relVelOfAirToDroneY = dYwind - droneTrackY - accelerationVectorY;
       

        /************************** Third wind drag implementation */

        double relVelOfStillAirToDroneX = - droneAirVectorX;// - accelerationPosChangeX;   // using accelerationPosChangeX probably inaccurate, maybe use accelerationVectorX / 3
        double relVelOfStillAirToDroneY = - droneAirVectorY;// - accelerationPosChangeY;

        // Velocity of the drone relative to the surrounding air mass in m/s
        double relVelTotal = (Point2D.Double.distance(0, 0, relVelOfStillAirToDroneX, relVelOfStillAirToDroneY) * TICS_PER_SECOND);

        // drag force in Newton
        double dragForce = cDfront * crossSectionAreaFwd * airDensity * relVelTotal * relVelTotal / 2;

        // change of drone speed due to drag in m/s²
        double dragSpeedChange = dragForce / droneMass;
        double dragDirection = calcRotationAngleInDegrees(0, 0, relVelOfStillAirToDroneX, relVelOfStillAirToDroneY);
        // drone displacement per frame in both axis due to drag
        double dragDisplacementX =Math.sin(Math.toRadians(dragDirection)) * dragSpeedChange/ (TICS_PER_SECOND * TICS_PER_SECOND);
        double dragDisplacementY =Math.cos(Math.toRadians(dragDirection)) * dragSpeedChange/ (TICS_PER_SECOND * TICS_PER_SECOND);






/*********************** SECOND wind drag implementation******************* */
/*
        double xWind = Math.sin(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;
        double hWind = Math.cos(Math.toRadians(windDirection - droneGroundTrack)) * windSpeed;
        // true airspeed (but based on Track, not heading) in m/s
        double tas = droneGroundSpeed + hWind;
        double dragForce = cDfront * crossSectionAreaFwd * airDensity * tas * tas / 2;
        double dragForceCross = cDcross * crossSectionAreaCross * airDensity * xWind * xWind / 2;

        // drag induced deceleration/ acceleration in m/s²
        double dragDeceleration = tas > 0 ? dragForce / droneMass : - dragForce / droneMass;
        double dragAccelerationCross = dragForceCross / droneMass;

        // in m/s
        double speedLoss = dragDeceleration/ (TICS_PER_SECOND);
        double speedGainDueXWind = dragAccelerationCross/ (TICS_PER_SECOND);

        double droneTrackXnew = Math.sin(Math.toRadians(droneGroundTrack)) * (droneGroundSpeed - speedLoss) / TICS_PER_SECOND;
        double droneTrackYnew = Math.cos(Math.toRadians(droneGroundTrack)) * (droneGroundSpeed - speedLoss) / TICS_PER_SECOND;

        double dX_Xwind = Math.sin(Math.toRadians(droneGroundTrack - 90)) * speedGainDueXWind / TICS_PER_SECOND;
        double dY_Xwind = Math.cos(Math.toRadians(droneGroundTrack - 90)) * speedGainDueXWind / TICS_PER_SECOND;

*/


        /*********************** FIRST wind drag implementation******************* */
        /*
        // Velocity of the drone relative to the surrounding air mass in m/s
        double relVelTotal = (Point2D.Double.distance(0, 0, relVelOfAirToDroneX, relVelOfAirToDroneY) * TICS_PER_SECOND);

        // drag force in Newton
        double dragForce = cDIDT * crossSectionAreaIDT * airDensity * relVelTotal * relVelTotal / 2;

        // change of drone speed due to drag in m/s²
        double dragSpeedChange = dragForce / droneMass;
        double dragDirection = calcRotationAngleInDegrees(0, 0, relVelOfAirToDroneX, relVelOfAirToDroneY);
        // drone displacement per frame in both axis due to drag
        double dragDisplacementX =Math.sin(Math.toRadians(dragDirection)) * dragSpeedChange/ (TICS_PER_SECOND * TICS_PER_SECOND);
        double dragDisplacementY =Math.cos(Math.toRadians(dragDirection)) * dragSpeedChange/ (TICS_PER_SECOND * TICS_PER_SECOND);
        double dragDisplacementX_old = (relVelOfAirToDroneX * (dragSpeedChange / relVelTotal)) / (TICS_PER_SECOND);
        double dragDisplacementY_old = (relVelOfAirToDroneY * (dragSpeedChange / relVelTotal)) / (TICS_PER_SECOND);
        **********************************************/

        if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " GS: " + String.format("%.3f", droneGroundSpeed) 
                    + " TK: " + String.format("%.3f", droneGroundTrack) 
                    + " relVelTotal: " + String.format("%.3f", relVelTotal)
                    + " dragSpeedChange: " + String.format("%.3f", dragSpeedChange) 
                    + " dragDisplacementX: " + String.format("%.3f", dragDisplacementX) 
                    + " dragDisplacementY: " + String.format("%.3f", dragDisplacementY));

/*        if (drone.getName().equals("FirstDrone"))
        System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
        + " GS: " + String.format("%.3f", droneGroundSpeed) 
        + " TK: " + String.format("%.3f", droneGroundTrack) 
        + " tas: " + String.format("%.3f", tas)
        + " dragDeceleration: " + String.format("%.3f", dragDeceleration) 
        + " dX_Xwind: " + String.format("%.3f", dX_Xwind) 
        + " dY_Xwind: " + String.format("%.3f", dY_Xwind));
*/

        // if (drone.getName() == "BasicDrone") System.out.println("dragSpeedChange: "+
        // dragSpeedChange + " relVelTotal: " + relVelTotal);

        // double CommandedVectorChangeX =
        // Math.sin(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;
        // double CommandedVectorChangeY =
        // Math.cos(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;

        // contains the sum of drone displacement in both axis caused by intertia, drag,
        // and accelaration
                    // double totaldX = droneTrackX + dragDisplacementX + accelerationPosChangeX;
                    // double totaldY = droneTrackY + dragDisplacementY + accelerationPosChangeY;
        // double totaldX = droneTrackXnew + dX_Xwind + accelerationPosChangeX;
        // double totaldY = droneTrackYnew + dY_Xwind + accelerationPosChangeY;
                    
        // double totalSpeedX = droneTrackXnew + dX_Xwind + accelerationVectorX;
        // double totalSpeedY = droneTrackYnew + dY_Xwind + accelerationVectorY;
                    // double totalSpeedX = droneTrackX + dragDisplacementX + accelerationVectorX;
                    // double totalSpeedY = droneTrackY + dragDisplacementY + accelerationVectorY;

    
    
        double totaldX = droneAirVectorX + dragDisplacementX + accelerationPosChangeX + dXwind;
        double totaldY = droneAirVectorY + dragDisplacementY + accelerationPosChangeY + dYwind;
        double totalSpeedX = droneAirVectorX + dragDisplacementX + accelerationVectorX + dXwind;
        double totalSpeedY = droneAirVectorY + dragDisplacementY + accelerationVectorY + dYwind;



        // if (ticCounter % TICS_PER_SECOND == 0) {
            if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " droneGroundSpeed: " + String.format("%.3f", droneGroundSpeed) 
                    + " totaldX: " + String.format("%.3f", totaldX)
                    + " droneAirVectorX: " + String.format("%.3f", droneAirVectorX) 
                    + " dragDisplacementX: " + String.format("%.3f", dragDisplacementX) 
                    + " accelerationPosChangeX: " + String.format("%.3f", accelerationPosChangeX)
                    + " dXwind: " + String.format("%.3f", dXwind));
        // }

        if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " droneGroundSpeed: " + String.format("%.3f", droneGroundSpeed) 
                    + " totaldY: " + String.format("%.3f", totaldY)
                    + " droneAirVectorY: " + String.format("%.3f", droneAirVectorY) 
                    + " dragDisplacementY: " + String.format("%.3f", dragDisplacementY) 
                    + " accelerationPosChangeY: " + String.format("%.3f", accelerationPosChangeY)
                    + " dYwind: " + String.format("%.3f", dYwind));


        /*
        // if (ticCounter % TICS_PER_SECOND == 0) {
        if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " droneGroundSpeed: " + String.format("%.3f", droneGroundSpeed) 
                    + " totaldX: " + String.format("%.3f", totaldX)
                    + " droneTrackX: " + String.format("%.3f", droneTrackXnew) 
                    + " dX_Xwind: " + String.format("%.3f", dX_Xwind) 
                    + " accelerationPosChangeX: " + String.format("%.3f", accelerationPosChangeX));
        // }

        if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " droneGroundSpeed: " + String.format("%.3f", droneGroundSpeed) 
                    + " totaldY: " + String.format("%.3f", totaldY)
                    + " droneTrackY: " + String.format("%.3f", droneTrackYnew) 
                    + " dY_Xwind: " + String.format("%.3f", dY_Xwind) 
                    + " accelerationPosChangeY: " + String.format("%.3f", accelerationPosChangeY));
        */
    
        // if (ticCounter % TICS_PER_SECOND == 0) {
        if (drone.getName().equals("FirstDrone"))
            System.out.println("timeIndex: " + String.format("%.2f", timeIndex) 
                    + " droneGroundSpeed: " + String.format("%.3f", droneGroundSpeed) 
                    + " totalSpeedX: " + String.format("%.3f", totalSpeedX)
                    + " totalSpeedY: " + String.format("%.3f", totalSpeedY) 
                    + " accelerationVectorX: " + String.format("%.3f", accelerationVectorX) 
                    + " accelerationVectorY: " + String.format("%.3f", accelerationVectorY));
        // }

        // double totaldX = CommandedVectorChangeX + dXwind;
        // double totaldY = CommandedVectorChangeY + dYwind;

        drone.setGroundSpeed(Point2D.Double.distance(0, 0, totalSpeedX, totalSpeedY) * TICS_PER_SECOND);
        // drone.setGroundSpeed(Point2D.Double.distance(0, 0, totaldX, totaldY) *
        // TICS_PER_SECOND);
        // drone.setGroundSpeed(Point2D.Double.distance(0, 0, totaldX, totaldY));
        drone.setGroundTrack(calcRotationAngleInDegrees(0, 0, totaldX, totaldY));
        drone.translate(totaldX, totaldY);

    }

    private static double calcRotationAngleInDegrees(double x1, double y1, double x2, double y2) {
        return calcRotationAngleInDegrees(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2));
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
    private static double calcRotationAngleInDegrees(Point2D.Double centerPt, Point2D.Double targetPt) {
        // calculate the angle theta from the deltaY and deltaX values
        // (atan2 returns radians values from [-PI,PI])
        // 0 currently points EAST.
        // double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x); //orginal

        // NOTE: By switching y and x with regard to the atan2 function, we get
        // clockwise rotation and 0 points NORTH
        double theta = Math.atan2(targetPt.x - centerPt.x, targetPt.y - centerPt.y);

        // convert from radians to degrees
        // the angle might be negative
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
