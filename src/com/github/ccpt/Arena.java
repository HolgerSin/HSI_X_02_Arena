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
    private final int TICS_PER_SECOND = 100;

    private int droneHeading;
    private int requestedSpeed;
    
    private double dX;
    private double dY;

    private double droneActualSpeed;

    

    private boolean running = true;

    ArrayList<Drone> droneList = new ArrayList<Drone>();
    

    public Arena(int size_X, int size_Y) {
        this.size_X = size_X;
        this.size_Y = size_Y;
    }

    public void run (){

        Drone myDrone = new Drone("FirstDrone", new Point2D.Double(500,500), Color.BLUE);
        Drone droneB = new Drone("SecondDrone", new Point2D.Double(700,500), Color.RED);
        droneList.add(myDrone);
        droneList.add(droneB);

        JFrame f = new JFrame();
        DrawPanel dp = new DrawPanel(droneList);
        
        

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
            System.out.println(timeIndex);
           
            if (ticCounter % TICS_PER_SECOND == 0) {
                timeIndex++;
            }
            ticCounter++;
            dp.repaint();

        }
    }

    private void calculateDronePosition(Drone drone){
        if (ticCounter % TICS_PER_SECOND == 0) {           
            drone.calculateNewCommand(timeIndex);      
        }
        
        droneHeading = drone.getLatestCommand()[0];
        requestedSpeed = drone.getLatestCommand()[1];
        drone.setActualSpeed(requestedSpeed);
        dX = Math.sin(Math.toRadians(droneHeading))*drone.getActualSpeed()/TICS_PER_SECOND;
        dY = Math.cos(Math.toRadians(droneHeading))*drone.getActualSpeed()/TICS_PER_SECOND;
        drone.translate(dX, dY);
    }
}
