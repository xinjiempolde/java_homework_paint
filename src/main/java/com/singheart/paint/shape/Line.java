package com.singheart.paint.shape;


import com.singheart.paint.Tool.AbstractTool;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Line extends Shape {

    public Line(int x1, int y1, int x2, int y2, Color color, int width) {
        super(x1, y1, x2, y2, color, width);
    }
    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public Line(FileReader fileReader) {
        super(fileReader);
    }


    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setStroke(new BasicStroke(linewidth));
        g2d.setColor(color);
        g2d.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.print("L");
        super.writeFile(printWriter);
    }
}
