package com.singheart.paint.shape;

import java.awt.*;
import java.util.List;
import com.singheart.paint.shape.Line;

public class PencilShape extends Shape {
    private List<Line> lines = null;
    public PencilShape(List<Line> lines) {
        this.lines = lines;
    }

    public void add(Line line) {
        lines.add(line);
    }
    @Override
    public void drawShape(Graphics g) {
        for (Line line : lines) {
            line.drawShape(g);
        }
    }
}
