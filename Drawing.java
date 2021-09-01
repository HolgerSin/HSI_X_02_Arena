import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

import java.awt.Rectangle;

public class Drawing extends Canvas {
    public static void draw() {
        JFrame frame = new JFrame("My Arena Test");
        Canvas canvas = new Drawing();
        canvas.setSize(1500, 1000);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        //g.fillOval(100, 100, 200, 200);
        Rectangle r = new Rectangle(100, 100, 200, 200);
        mickey(g, r);
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