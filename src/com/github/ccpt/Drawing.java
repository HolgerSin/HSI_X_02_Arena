package com.github.ccpt;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.awt.Rectangle;
import java.awt.Color;

public class Drawing extends Canvas {

    public static Rectangle drawingRectangle = new Rectangle(100, 100, 200, 300);
    JFrame frame;
    
    public void draw() {
        frame = new JFrame("My Arena Test");
        Canvas canvas = new Drawing();
        canvas.setSize(1500, 1000);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    

    public void paint(Graphics g) {
        //g.fillOval(100, 100, 200, 200);
        // Rectangle r = new Rectangle(100, 100, 200, 200);
        mickey(g, drawingRectangle);
        // try {
        //     Thread.sleep(100);
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        // repaint();
    }

    public void myRepaint(){
        System.out.println("repaint called");
        repaint();
        System.out.println("repaint done");
    }

    // public void paint(Graphics g) {   
    //     // Dynamically calculate size information 
    //         Dimension size = getSize();         // diameter    
    //         int d = Math.min(size.width, size.height);    
    //         int x = (size.width - d)/2;    
    //         int y = (size.height - d)/2;       
    //         // draw circle (color already set to foreground) 
    //             g.fillOval(x, y, d, d);     
    //         g.setColor(Color.black);   
    //         g.drawOval(x, y, d, d); 
    //         }

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