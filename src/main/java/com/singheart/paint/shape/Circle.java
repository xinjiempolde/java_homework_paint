package com.singheart.paint.shape;

import java.awt.*;

public class Circle extends Shape {
    private Point startPoint;
    private Point stopPoint;
    private int width;
    private int height;
    private int x, y;

    public Circle(Point startPoint, Point stopPoint) {
        this.startPoint = startPoint;
        this.stopPoint = stopPoint;
        //计算椭圆的宽和高
        this.width = Math.abs(startPoint.x - stopPoint.x);
        this.height = Math.abs(startPoint.y - stopPoint.y);

        //计算椭圆最左上角的坐标
        this.x = Math.min(startPoint.x, stopPoint.x);
        this.y = Math.min(startPoint.y, stopPoint.y);
    }
    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawOval(x, y, width, height);
    }
}

