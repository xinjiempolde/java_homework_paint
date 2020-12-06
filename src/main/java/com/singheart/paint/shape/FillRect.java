package com.singheart.paint.shape;

import java.awt.*;
import java.io.FileReader;
import java.io.PrintWriter;

public class FillRect extends Shape {

    public FillRect(int x1, int y1, int x2, int y2, Color color, int width) {
        super(x1, y1, x2, y2, color, width);
    }
    public FillRect(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    public FillRect(FileReader fileReader) {
        super(fileReader);
    }

    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(width));
        g2d.fillRect(x, y, width, height);
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.print('F');
        super.writeFile(printWriter);
    }
}
