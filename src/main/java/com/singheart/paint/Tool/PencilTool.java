package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Line;
import com.singheart.paint.shape.PencilShape;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PencilTool extends AbstractTool {
    private static Tool tool = null;

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new PencilTool(paintFrame);
        }
        return tool;
    }

    public PencilTool(PaintFrame paintFrame) {
        super(paintFrame);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //创建一个空物体，供mouseDragged弹出
        PencilShape pencilShape = new PencilShape(new ArrayList<Line>());
        paintFrame.getShapes().add(pencilShape);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        createShape(mPressedPoint, new Point(e.getX(), e.getY()));
        mPressedPoint = new Point(e.getX(), e.getY());
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void createShape(Point start, Point end) {
        PencilShape pencilShape = (PencilShape)popShape();
        pencilShape.add(new Line(start.x, start.y, end.x, end.y));
        paintFrame.getShapes().add(pencilShape);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
