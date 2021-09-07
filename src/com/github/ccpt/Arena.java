package com.github.ccpt;
import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Arena {
    
    private int size_X;
    private int size_Y;
    private double timeIndex = 0;
    private int ticCounter = 0;  
    private static final int TICS_PER_SECOND = 100;

    
    
    private int windDirection = 180;
    private int windSpeed = 10;
    
    private double airDensity = 1.2;   // in kg/m³
    private double coefficientOfDrag = 0.2;
    private double crossSectionArea = 1;  // in m²
    private double Vrel = 1;  // Velocity relative to the surrounding air mass
    


    

    private ArrayList<Waypoint> wayPointList = new ArrayList<Waypoint>();
    private ArrayList<Drone> droneList = new ArrayList<Drone>();
    

    private boolean running = true;
    
    

    public Arena(int size_X, int size_Y) {
        this.size_X = size_X;
        this.size_Y = size_Y;
    }

    public void run (){

        Drone myDrone = new Drone("FirstDrone", new Point2D.Double(500,500), Color.BLUE);
        Drone droneB = new LessStupidDrone("SecondDrone", new Point2D.Double(700,500), Color.RED);
        droneList.add(myDrone);
        droneList.add(droneB);
        droneList.add( new BasicDrone("BasicDrone", new Point2D.Double(300,500), Color.GREEN));

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
                Thread.sleep(1000/TICS_PER_SECOND);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //dp.mr.setBounds(800, 200+i, 200, 200);

            for (Drone drone : droneList) {
                calculateDronePosition(drone);
            }
            // System.out.println(timeIndex);
           
            if (ticCounter % TICS_PER_SECOND == 0) {
                timeIndex++;
            }
            ticCounter++;
            dp.repaint();

        }
    }

    private void calculateDronePosition(Drone drone){
        // drag force in Newton
         
        
        double dX;
        double dY;
        double dXwind;
        double dYwind;
        
        
       

    
        if (ticCounter % TICS_PER_SECOND == 0) {           
            drone.calculateNewCommand(timeIndex, wayPointList);      
        }
        
        int commandedHeading = drone.getLatestCommandHeading();
        int commandedSpeed = drone.getLatestCommandSpeed();
        double commandedThrust = drone.getLatestCommandThrust();
        double droneGroundSpeed = drone.getGroundSpeed();
        double droneGroundTrack = drone.getGroundTrack();

        // drone.setGroundSpeed(commandedSpeed);
        double droneTrackX = Math.sin(Math.toRadians(droneGroundTrack))*droneGroundSpeed/TICS_PER_SECOND;
        double droneTrackY = Math.cos(Math.toRadians(droneGroundTrack))*droneGroundSpeed/TICS_PER_SECOND;

        double dragForce;

        double CommandedVectorChangeX = Math.sin(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;
        double CommandedVectorChangeY = Math.cos(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;
        
        // double CommandedVectorChangeX = Math.sin(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;
        // double CommandedVectorChangeY = Math.cos(Math.toRadians(commandedHeading))*commandedSpeed/TICS_PER_SECOND;
        
        // dX = Math.sin(Math.toRadians(commandedHeading))*drone.getGroundSpeed()/TICS_PER_SECOND;
        // dY = Math.cos(Math.toRadians(commandedHeading))*drone.getGroundSpeed()/TICS_PER_SECOND;
        dXwind = Math.sin(Math.toRadians((windDirection+180)%360))*windSpeed/TICS_PER_SECOND;
        dYwind = Math.cos(Math.toRadians((windDirection+180)%360))*windSpeed/TICS_PER_SECOND;
        
        double totaldX = CommandedVectorChangeX + dXwind;
        double totaldY = CommandedVectorChangeY + dYwind;
        
        drone.setGroundSpeed(Point2D.Double.distance(0, 0, totaldX, totaldY) * TICS_PER_SECOND);
        // drone.setGroundTrack(groundTrack);
        drone.translate(CommandedVectorChangeX + dXwind, CommandedVectorChangeY + dYwind);
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
        // double theta = Math.atan2(targetPt.y - centerPt.y, targetPt.x - centerPt.x);  //orginal
        
        // NOTE: By switching y and x with regard to the atan2 function, we get clockwise rotation and 0 points NORTH
        double theta = Math.atan2(targetPt.x - centerPt.x, targetPt.y - centerPt.y); 
        
        // convert from radians to degrees
        // the ange might be negative
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
