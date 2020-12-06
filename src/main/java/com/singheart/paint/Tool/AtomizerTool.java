package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.FillRect;
import com.singheart.paint.shape.AtomizerShape;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class AtomizerTool extends AbstractTool {
    private static Tool tool = null;
    // 喷枪大小
    private int size = 8;
    // 喷枪点数
    private int count = 10;

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new AtomizerTool(paintFrame, "img/atomizercursor.gif");
        }
        return tool;
    }

    private AtomizerTool(PaintFrame paintFrame, String cursorPath) {
        super(paintFrame, cursorPath);
    }
    private AtomizerTool(PaintFrame paintFrame) {
        super(paintFrame);
    }


    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        //创建一个空物体，供mouseDragged弹出
        AtomizerShape atomizerShape = new AtomizerShape(new ArrayList<FillRect>());
        paintFrame.getShapes().add(atomizerShape);
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
        AtomizerShape atomizerShape = (AtomizerShape)popShape();
        for (int i = 0; i < count; i++) {
            int x = new Random().nextInt(size) + 1;
            int y = new Random().nextInt(size) + 1;
            atomizerShape.add(new FillRect(x2+x, y2+y, x2+x+1, y2+y+1, color, width));
        }
        paintFrame.getShapes().add(atomizerShape);
    }
}
