package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Circle;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class CircleTool extends AbstractTool {
    public static Tool tool = null;
    private CircleTool(PaintFrame paintFrame) {
        super(paintFrame);
    }

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new CircleTool(paintFrame);
        }
        return tool;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //创建一个空物体，供mouseDragged弹出
        paintFrame.getShapes().add(new Shape() {
            @Override
            public void drawShape(Graphics g) {

            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        popShape();
        createShape(mPressedPoint, new Point(e.getX(), e.getY()));
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void createShape(Point start, Point end) {
        Circle circle = new Circle(start, end);
        paintFrame.getShapes().add(circle);
        paintFrame.repaintDrawPanel();
    }
}
