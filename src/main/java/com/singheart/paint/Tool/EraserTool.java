package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.FillRect;

import java.awt.*;
import java.awt.event.MouseEvent;

public class EraserTool extends AbstractTool {
    private static Tool tool = null;

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new EraserTool(paintFrame);
        }
        return tool;
    }

    private EraserTool(PaintFrame paintFrame) {
        super(paintFrame);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //获取矩形左上角坐标
        int x = Math.min(mPressedPoint.x, e.getX());
        int y = Math.min(mPressedPoint.y, e.getY());
        int w = Math.abs(mPressedPoint.x - e.getX());
        int h = Math.abs(mPressedPoint.y - e.getY());
        createShape(new Point(x, y), new Point(w, h));
        mPressedPoint = new Point(e.getX(), e.getY());
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void createShape(Point start, Point end) {
        paintFrame.getShapes().add(new FillRect(start.x, start.y, end.x, end.y));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
