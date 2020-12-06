package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Line;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LineTool extends AbstractTool {
    public static Tool tool = null;

    private LineTool(PaintFrame paintFrame) {
        super(paintFrame);
    }

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new LineTool(paintFrame);
        }
        return tool;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //创建一个空物体，供mouseDragged弹出
        paintFrame.getShapes().add(new Shape(){});
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        popShape();
        createShape(mPressedX, mPressedY, e.getX(), e.getY());
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void createShape(int x1, int y1, int x2, int y2) {
        Line line = new Line(x1, y1, x2, y2, color, width);
        paintFrame.addNewShape(line);
        paintFrame.repaintDrawPanel();
    }
}
