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
        
        
        // Drawing myDrawing = new Drawing();
        Rectangle mainRectangle = new Rectangle(300, 100, 300, 300);
        // // myDrawing.setDrawingRectangle(mainRectangle);
        // Drawing.drawingRectangle.setBounds(100, 500, 100, 100);
        // myDrawing.draw();
        // try {
        // Thread.sleep(1000);
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // Drawing.drawingRectangle.setBounds(500, 500, 100, 100);
        // // Drawing.draw();
        // myDrawing.myRepaint();

        Rectangle myRectangle = new Rectangle(300, 100, 300, 300);
        
        System.out.println("Bye");
    }
}
