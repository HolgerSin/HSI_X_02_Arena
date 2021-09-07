package com.github.ccpt;

import java.awt.*;
import java.awt.geom.Point2D;
// import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

public class Drone {

    private String name;
    private Color mycolor;

    private Point2D.Double position = new Point2D.Double(100.0, 100.0);

    // actual speed above ground in m/s
    private double groundSpeed;

    // actual direction of movement in relation to the surface, given in degrees
    // [0-360]
    private double groundTrack;

    // drone mass in kg
    private int mass = 100;

    // available horizontal thrust in Newton
    private int thrustHorizontal = 1000;

    protected int requestedSpeed = 100;

    /*---- Latest commands calculated in calculateNewCommand()  --------*/
    // Heading in degrees [0-360]
    protected int latestCommandHeading = 0;

    // Speed in m/s
    protected int latestCommandSpeed = 0;

    // Thrust commanded by drone in %/100
    protected double latestCommandThrust = 1.0;

    public Drone(String name, Point2D.Double position, Color mycolor) {
        this.name = name;
        this.position = position;
        this.mycolor = mycolor;
    }

    /*------ Getters / Setters -----------------*/

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

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getThrustHorizontal() {
        return thrustHorizontal;
    }

    public void setThrustHorizontal(int thrustHorizontal) {
        this.thrustHorizontal = thrustHorizontal;
    }

    public int getLatestCommandHeading() {
        return latestCommandHeading;
    }

    public int getLatestCommandSpeed() {
        return latestCommandSpeed;
    }

    public double getLatestCommandThrust() {
        return latestCommandThrust;
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

    public void translate(double dx, double dy) {
        position.setLocation(position.x + dx, position.y + dy);
    }

    public void calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {

        latestCommandHeading = (int) timeIndex * 20;
        latestCommandSpeed = requestedSpeed;
        latestCommandThrust = 0;

    }

}
