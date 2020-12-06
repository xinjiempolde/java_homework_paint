package com.singheart.paint.shape;

import com.singheart.paint.Tool.AbstractTool;

import java.awt.*;
import java.io.FileReader;
import java.io.PrintWriter;

public class Circle extends Shape {

    public Circle(int x1, int y1, int x2, int y2, Color color, int width) {
        super(x1, y1, x2, y2, color, width);
    }
    public Circle(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public Circle(FileReader fileReader) {
        super(fileReader);
    }
    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.setColor(color);
        g2d.drawOval(x, y, width, height);
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.print("C");
        super.writeFile(printWriter);
    }
}

