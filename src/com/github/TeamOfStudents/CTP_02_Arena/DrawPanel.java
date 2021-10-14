package com.github.TeamOfStudents.CTP_02_Arena;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.geom.Point2D;

import javax.swing.*;

class DrawPanel extends JPanel {

    // Rectangle mr = new Rectangle(500, 500, 100, 100);
    Point displayPosition = new Point(500, 500);
    ArrayList<Drone> droneList;
    ArrayList<Waypoint> waypointList;
    RaceTrackMission raceTrackMission;

    public DrawPanel(ArrayList<Drone> droneList, ArrayList<Waypoint> waypointList, RaceTrackMission raceTrackMission) {
        this.droneList = droneList;
        this.waypointList = waypointList;
        this.raceTrackMission = raceTrackMission;
    }

    @Override
    protected void paintComponent(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);
        // g.drawLine(mr.x, mr.y, mr.width, mr.height);
        for (Drone drone : droneList) {

            drawDrone(g, drone);
            drawMissionTargets(g, drone, raceTrackMission.getNextWaypointMap());
        }
        for (Waypoint waypoint : waypointList) {
            displayPosition.setLocation(translateToDisplayCoordinates(waypoint.getLocation()));
            g.setColor(Color.BLACK);
            drawWaypoint(g, displayPosition, waypoint.getName());
        }
        for (Drone drone : droneList) {
            
        }

        // mickey(g, mr);
    }

    private void drawMissionTargets(Graphics2D g, Drone drone, HashMap<Drone, Waypoint> nextWaypointMap) {
        int size = 10;
        int halfSize = size / 2;
        g.setColor(drone.getMycolor());
        displayPosition.setLocation(translateToDisplayCoordinates(nextWaypointMap.get(drone).getLocation()));
        g.fillRect(displayPosition.x - halfSize, displayPosition.y - halfSize, size, size);
    }

    private void drawWaypoint(Graphics2D g, Point displayPosition, String string) {
        g.drawString(string, displayPosition.x, displayPosition.y);
    }

    public void drawDrone(Graphics2D g, Drone drone) {
        int size = 10;
        int halfSize = size / 2;
        int vectorLength = 5;
        g.setColor(drone.getMycolor());
        displayPosition.setLocation(translateToDisplayCoordinates(drone.getLocation()));
        g.fillOval(displayPosition.x - halfSize, displayPosition.y - halfSize, size, size);
        String[] infoText = { 
            "Speed: " + (int) drone.getGroundSpeed(),
            "Track: " + (int) drone.getGroundTrack() + " HDG: " + (int) drone.getLatestCommandHeading(),
            drone.getName(),
            "Credits: " + drone.getCredits() 
        };
        for (int i = 0; i < infoText.length; i++) {
            g.drawString(infoText[i], displayPosition.x, displayPosition.y + i * 12);
        }

        // g.drawString(infoText[0], displayPosition.x, displayPosition.y);
        // g.drawString(infoText[1], displayPosition.x, displayPosition.y + 12);
        Point2D.Double lineTarget = translateToDisplayCoordinates(
                new Point2D.Double(drone.getLocation().x + drone.getAccelerationVector().x * vectorLength,
                        drone.getLocation().y + drone.getAccelerationVector().y * vectorLength));
        // int lineTargetX = (int) (displayPosition.x + lineTarget.x * vectorLength);
        // int lineTargetY = (int) (displayPosition.y + lineTarget.y * vectorLength);
        g.drawLine(displayPosition.x, displayPosition.y, (int) lineTarget.x, (int) lineTarget.y);

    }

    private Point2D.Double translateToDisplayCoordinates(Point2D.Double point) {
        int height = getSize().height;
        return new Point2D.Double(point.x, height - point.y);

    }

    // public void boxOval(Graphics g, Rectangle bb) {
    // g.fillOval(bb.x, bb.y, bb.width, bb.height);
    // }

    // public void mickey(Graphics g, Rectangle bb) {
    // boxOval(g, bb);

    // int dx = bb.width / 2;
    // int dy = bb.height / 2;
    // Rectangle half = new Rectangle(bb.x, bb.y, dx, dy);

    // half.translate(-dx / 2, -dy / 2);
    // boxOval(g, half);

    // half.translate(dx * 2, 0);
    // boxOval(g, half);
    // }
}
