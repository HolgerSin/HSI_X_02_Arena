package com.github.TeamOfStudents.CTP_02_Arena;

import java.util.ArrayList;

public class StupidDroneLogic extends DroneLogic{
    /*
    public StupidDroneLogic(Drone drone) {
        super(drone);
    }
*/
    public CommandSet calculateNewCommand(double timeIndex, ArrayList<Waypoint> wayPointList) {

        double latestCommandThrust = 1;
        
               
        if (timeIndex > 30) {
            latestCommandThrust = 0;
        }

        //return new CommandSet(90, 1);
        
        return new CommandSet((int) timeIndex * -20 + 180, latestCommandThrust);

    }
}
