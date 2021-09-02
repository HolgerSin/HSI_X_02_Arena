import java.awt.*;

import javax.swing.*;

class DrawPanel extends JPanel {

    Rectangle mr = new Rectangle(500, 500, 100, 100);
    Point dronePos = new Point(500,500);

    @Override
    protected void paintComponent(Graphics g1) {

        Graphics2D g = (Graphics2D) g1;
        super.paintComponent(g);
        //g.drawLine(mr.x, mr.y, mr.width, mr.height);
        drawDrone(g, dronePos);
        //mickey(g, mr);
    }

    public void drawDrone(Graphics2D g, Point pos){
        int size = 10;
        int halfSize = 10;
        
        g.fillOval(pos.x - halfSize, pos.y + halfSize, size, size);

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
