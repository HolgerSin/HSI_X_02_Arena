import java.awt.Rectangle;

import javax.swing.JFrame;

public class App {

    public static void main(String[] args) {

        System.out.println("Hello");

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
        JFrame f = new JFrame();
        DrawPanel dp = new DrawPanel();

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000, 1000);
        f.add(dp);
        f.setVisible(true);

        for (int i = 0; i < 500; i++) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //dp.mr.setBounds(800, 200+i, 200, 200);
            dp.dronePos.setLocation(i, i);
            dp.repaint();
        }
        // dp.mr = mainRectangle;
        // dp.mr.setBounds(800, 200, 200, 800);
        dp.repaint();

        System.out.println("Bye");
    }
}
