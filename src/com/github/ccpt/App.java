package com.github.ccpt;
import java.awt.Rectangle;

import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello");

        Arena myArena = new Arena(1500, 1000);
        myArena.run();
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
