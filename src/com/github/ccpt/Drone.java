package com.github.ccpt;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

public class Drone {

    private String name = "Nobody";
    private Point2D.Double position = new Point2D.Double(100.0, 100.0);
    private double actualSpeed;
    private int requestedSpeed = 100;
    private int[] latestCommand = new int[2];

    // private int counter = 0;
    private Color mycolor;

    public Drone(String name, Double position, Color mycolor) {
        this.name = name;
        this.position = position;
        this.mycolor = mycolor;
    }

    public int[] getLatestCommand() {
        return latestCommand;
    }

    public void setLatestCommand(int[] latestCommand) {
        this.latestCommand = latestCommand;
    }

    public double getActualSpeed() {
        return actualSpeed;
    }

    public void setActualSpeed(double actualSpeed) {
        this.actualSpeed = actualSpeed;
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

    public Point2D.Double getLocation(){
        return position;
    }

    public void setLocation(double x, double y) {
        position.setLocation(x, y);
    }

    public void translate(double dx, double dy) {
        position.setLocation(position.x + dx, position.y + dy);
    }

    public int[] calculateNewCommand(double timeIndex) {
        // int[] answer = new int[2];
        // counter++;
        
        latestCommand[0] = (int)timeIndex*20;
        latestCommand[1] = requestedSpeed;
        return latestCommand;
    }

}
