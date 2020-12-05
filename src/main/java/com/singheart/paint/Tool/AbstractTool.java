package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.List;

public class AbstractTool implements Tool {
    protected Point mPressedPoint = new Point(0, 0);
    protected PaintFrame paintFrame = null;

    public AbstractTool(PaintFrame paintFrame) {
        this.paintFrame = paintFrame;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        mPressedPoint = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * 创建图形，抽象方法
     * @param start
     * @param end
     */
    public void createShape(Point start, Point end) {

    }

    public Shape popShape() {
        List<Shape> shapes = paintFrame.getShapes();
        int lastIdx = shapes.size() - 1;
        Shape shape = shapes.get(lastIdx);
        shapes.remove(lastIdx);
        return shape;
    }

}
