package com.singheart.paint.shape;

import java.awt.*;

public class FillRect extends Shape {
    private int x, y, w, h;

    public FillRect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.fillRect(x, y, w, h);
    }
}
