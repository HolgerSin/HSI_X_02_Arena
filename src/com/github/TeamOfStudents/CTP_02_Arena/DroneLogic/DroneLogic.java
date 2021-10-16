package com.github.TeamOfStudents.CTP_02_Arena.DroneLogic;

// import java.util.ArrayList;

import com.github.TeamOfStudents.CTP_02_Arena.CommandSet;
import com.github.TeamOfStudents.CTP_02_Arena.Drone;
import com.github.TeamOfStudents.CTP_02_Arena.Waypoint;

import java.awt.geom.Point2D;

public abstract class DroneLogic {

    private Drone drone;
    
    
    protected Point2D.Double getLocation(){
        
        return drone.getLocationFromDroneLogic();
    }

    protected CommandSet getCommandSet(){
        return drone.getCommandSet();
    }

/*
    public Drone getDrone() {
        return drone;
    }
*/
    public void setDrone(Drone drone) {
        this.drone = drone;
    }



    public abstract CommandSet calculateNewCommand(double timeIndex, Waypoint wayPoint);
}
