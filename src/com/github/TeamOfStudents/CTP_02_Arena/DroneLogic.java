package com.github.TeamOfStudents.CTP_02_Arena;

import java.util.ArrayList;
import java.awt.geom.Point2D;

public abstract class DroneLogic {

    private Drone drone;
    private int credits = 0;
    
    protected Point2D.Double getLocation(){
        credits -= 10;
        return drone.getLocation();
    }
/*
    public Drone getDrone() {
        return drone;
    }
*/
    public void setDrone(Drone drone) {
        this.drone = drone;
    }



    public abstract CommandSet calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList);
}
