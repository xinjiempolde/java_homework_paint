package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Line;
import com.singheart.paint.shape.Rect;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

public class RectTool extends AbstractTool {
    private static RectTool tool = null;

    private RectTool(PaintFrame paintFrame) {
        super(paintFrame);
    }

    public static RectTool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new RectTool(paintFrame);
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
        Rect rect = new Rect(x1, y1, x2, y2, color, width);
        paintFrame.getShapes().add(rect);
        paintFrame.repaintDrawPanel();
    }
}
