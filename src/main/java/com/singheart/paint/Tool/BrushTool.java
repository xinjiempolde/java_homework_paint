package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Line;
import com.singheart.paint.shape.PencilShape;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class BrushTool extends AbstractTool {
    private static Tool tool = null;

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new BrushTool(paintFrame, "img/brushcursor.gif");
        }
        return tool;
    }

    private BrushTool(PaintFrame paintFrame, String cursorPath) {
        super(paintFrame, cursorPath);
    }
    private BrushTool(PaintFrame paintFrame) {
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
        createShape(mPressedX, mPressedY, e.getX(), e.getY());
        mPressedX = e.getX();
        mPressedY = e.getY();
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void createShape(int x1, int y1, int x2, int y2) {
        PencilShape pencilShape = (PencilShape)popShape();
        pencilShape.add(new Line(x1, y1, x2, y2, color, 8));
        paintFrame.getShapes().add(pencilShape);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
