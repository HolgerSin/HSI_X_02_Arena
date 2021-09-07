package com.github.ccpt;
import java.awt.Rectangle;

import javax.swing.JFrame;
import java.awt.geom.Point2D;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello");

        Arena myArena = new Arena(1500, 1000);

        myArena.run();

        // double requiredHeading;
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(100, 200));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(-100, 100));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(-1000, 100));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(-1000, -100));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(-100, -100));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(-100, -1000));
        // System.out.println(requiredHeading);
        // requiredHeading = BasicDrone.calcRotationAngleInDegrees(new Point2D.Double(0,0), new Point2D.Double(1000, -100));
        // System.out.println(requiredHeading);
        
        
        
        System.out.println("Bye");
    }
}
