package com.github.ccpt;
import java.awt.*;
import java.util.ArrayList;
import java.awt.geom.Point2D;

import javax.swing.*;

class DrawPanel extends JPanel {

    // Rectangle mr = new Rectangle(500, 500, 100, 100);
    Point displayPosition = new Point(500,500);
    ArrayList<Drone> droneList;
    ArrayList<Waypoint> waypointList;

    public DrawPanel(ArrayList<Drone> droneList, ArrayList<Waypoint> waypointList) {
        this.droneList = droneList;
        this.waypointList = waypointList;
    }

    @Override
    protected void paintComponent(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);
        //g.drawLine(mr.x, mr.y, mr.width, mr.height);
        for (Drone drone : droneList) {
            displayPosition.setLocation(translateToDisplayCoordinates(drone.getLocation()));
            g.setColor(drone.getMycolor());
            drawDrone(g, displayPosition);
        }
        for (Waypoint waypoint : waypointList) {
            displayPosition.setLocation(translateToDisplayCoordinates(waypoint.getLocation()));
            g.setColor(Color.BLACK);
            drawWaypoint(g, displayPosition, waypoint.getName());
        }
        
        //mickey(g, mr);
    }

    private void drawWaypoint(Graphics2D g, Point displayPosition, String string) {
        g.drawString(string, displayPosition.x, displayPosition.y);
    }

    

    public void drawDrone(Graphics2D g, Point pos){
        int size = 10;
        int halfSize = 10;
        
        g.fillOval(pos.x - halfSize, pos.y - halfSize, size, size);
    }

    
    private Point2D.Double translateToDisplayCoordinates(Point2D.Double point){
        int height = getSize().height;
        return new Point2D.Double(point.x, height - point.y);

    }


    public void boxOval(Graphics g, Rectangle bb) {
        g.fillOval(bb.x, bb.y, bb.width, bb.height);
    }

    public void mickey(Graphics g, Rectangle bb) {
        boxOval(g, bb);
    
        int dx = bb.width / 2;
        int dy = bb.height / 2;
        Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);
    
        half.translate(-dx / 2, -dy / 2);
        boxOval(g, half);
    
        half.translate(dx * 2, 0);
        boxOval(g, half);
    }
}
