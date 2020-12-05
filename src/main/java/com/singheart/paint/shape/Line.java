package com.singheart.paint.shape;


import java.awt.*;

public class Line extends Shape {
    private int x1, y1;
    private int x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(10));
        g2d.setColor(Color.WHITE);
        g2d.drawLine(x1, y1, x2, y2);
    }
}
