package com.github.TeamOfStudents.CTP_02_Arena;

import java.awt.*;
import java.awt.geom.Point2D;
// import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Drone {

    private String name;
    private Color mycolor;
    private DroneLogic droneLogic;
    private Point2D.Double position = new Point2D.Double(100.0, 100.0);
    private CommandSet commandSet = new CommandSet(180, 0.1);
    

    /** actual speed above ground in m/s */
    private double groundSpeed;

    /** actual direction of movement of drone in relation to the surface, given in degrees [0-360] */ 
    private double groundTrack;

    // private double currentHeading;

    /**  drone mass in kg */
    private int mass = 100;

    /** available horizontal thrust in Newton */ 
    private int thrustHorizontal = 1000;

    /** Coefficient of Drag from front/ German: Cw Wert */
    private double cDfront = 0.2;


    // private double cDcross = 0.2;



    /**
     * Cross Section Area of Drone in m²
     * <p>
     * used to determine the drag force Fd
     */
    private double crossSectionAreaFwd = 1; // in m²

    // private double crossSectionAreaCross = 1; // in m²


    public Drone(String name, DroneLogic droneLogic, Point2D.Double position, Color mycolor) {
        this.name = name;
        this.position = position;
        this.mycolor = mycolor;
        this.droneLogic = droneLogic;
    }


    /*------ Methoden -----------------*/


    public void translate(double dx, double dy) {
        position.setLocation(position.x + dx, position.y + dy);
    }

    public Point2D.Double getAccelerationVector(){
        double thrustSpeedChange = thrustHorizontal * commandSet.getThrust() / mass;
        return Arena.vectorEndPoint(commandSet.getHeading(), thrustSpeedChange);
    }

    public void calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {

        commandSet = droneLogic.calculateNewCommand(timeIndex, wayPointList);
        
    }

    /*------ Getters / Setters -----------------*/


    public CommandSet getCommandSet() {
        return commandSet;
    }

    public void setCommandSet(CommandSet commandSet) {
        this.commandSet = commandSet;
    }



    public double getcDfront() {
        return cDfront;
    }

    public double getCrossSectionAreaFwd() {
        return crossSectionAreaFwd;
    }
/*
    public double getCurrentHeading() {
        return currentHeading;
    }

    public void setCurrentHeading(double currentHeading) {
        this.currentHeading = currentHeading;
    }
*/
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public double getGroundTrack() {
        return groundTrack;
    }

    public void setGroundTrack(double groundTrack) {
        this.groundTrack = groundTrack;
    }

    public int getMass() {
        return mass;
    }

    // public void setMass(int mass) {
    //     this.mass = mass;
    // }

    public int getThrustHorizontal() {
        return thrustHorizontal;
    }

    // public void setThrustHorizontal(int thrustHorizontal) {
    //     this.thrustHorizontal = thrustHorizontal;
    // }

    public int getLatestCommandHeading() {
        return commandSet.getHeading();
    }

    // public int getLatestCommandSpeed() {
    //     return latestCommandSpeed;
    // }

    public double getLatestCommandThrust() {
        return commandSet.getThrust();
    }

    public double getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(double groundSpeed) {
        this.groundSpeed = groundSpeed;
    }

    public Color getMycolor() {
        return mycolor;
    }

    public void setMycolor(Color mycolor) {
        this.mycolor = mycolor;
    }

    public double getX() {
        return position.getX();
    }

    public double getY() {
        return position.getY();
    }

    public Point2D.Double getLocation() {
        return position;
    }

    public void setLocation(double x, double y) {
        position.setLocation(x, y);
    }


}
