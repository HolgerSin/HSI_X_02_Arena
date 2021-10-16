package com.github.TeamOfStudents.CTP_02_Arena.DroneLogic;

// import java.util.ArrayList;

import com.github.TeamOfStudents.CTP_02_Arena.CommandSet;
import com.github.TeamOfStudents.CTP_02_Arena.Waypoint;

public class StupidDroneLogic extends DroneLogic{
    /*
    public StupidDroneLogic(Drone drone) {
        super(drone);
    }
*/
    public CommandSet calculateNewCommand(double timeIndex, Waypoint waypoint) {

        //double requiredThrust = 1;
        //int requiredHeading = 90;

        double requiredThrust = (timeIndex % 10) / 10;
        int requiredHeading = (int) timeIndex * -20 + 180;

               
        if (timeIndex > 30) {
            // requiredThrust = 0;
        }

        return new CommandSet(requiredHeading, requiredThrust);

    }
}
