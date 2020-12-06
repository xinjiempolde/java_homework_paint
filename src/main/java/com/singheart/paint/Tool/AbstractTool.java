package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;

import java.util.List;

public class AbstractTool implements Tool {
    protected int mPressedX = 0;
    protected int mPressedY = 0;
    public static Color color = Color.BLACK; // 所有工具共用
    public static int width = 1;
    protected PaintFrame paintFrame = null;
    protected Cursor mCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    public AbstractTool(PaintFrame paintFrame, Color color, int width) {
        this.paintFrame = paintFrame;
        AbstractTool.color = color;
        AbstractTool.width = width;
    }

    public AbstractTool(PaintFrame paintFrame, String cursorPath) {
        this.paintFrame = paintFrame;
        Image cursorImage = Toolkit.getDefaultToolkit().createImage(cursorPath);
        mCursor=  Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,
                new Point(10, 10), "myCursor");
    }
    public AbstractTool(PaintFrame paintFrame) {
        this.paintFrame = paintFrame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mPressedX = e.getX();
        mPressedY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paintFrame.getmDrawPanel().setCursor(mCursor);
    }


    /**
     * 创建图形，抽象方法
     */
    public void createShape(int x1, int y1, int x2, int y2, Color color, int linewidth) {

    }

    /**
     * 创建图形，抽象方法
     */
    public void createShape(int x1, int y1, int x2, int y2) {

    }

    public Shape popShape() {
        List<Shape> shapes = paintFrame.getShapes();
        int lastIdx = shapes.size() - 1;
        Shape shape = shapes.get(lastIdx);
        shapes.remove(lastIdx);
        return shape;
    }

}
