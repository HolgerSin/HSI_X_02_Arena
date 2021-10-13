package com.github.TeamOfStudents.CTP_02_Arena;

public class CommandSet {
    private int heading;
    private double thrust;
    public CommandSet(int heading, double thrust) {
        this.heading = (heading % 360 + 360) % 360;
        this.thrust = thrust < 0 ? 0 : (thrust > 1 ? 1 : thrust);
    }
    public int getHeading() {
        return heading;
    }
    public void setHeading(int heading) {
        this.heading = heading;
    }
    public double getThrust() {
        return thrust;
    }
    public void setThrust(double thrust) {
        this.thrust = thrust;
    }
    

    
    
}
