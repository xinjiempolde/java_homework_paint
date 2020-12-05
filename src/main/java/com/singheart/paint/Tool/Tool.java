package com.singheart.paint.Tool;

import java.awt.event.MouseEvent;

public interface Tool {
    public static final String PENCIL_TOOL = "PencilTool";
    public static final String LINE_TOOL = "LineTool";
    public static final String RECT_TOOL = "RectTool";
    public static final String CIRCEL_TOOL = "CircleTool";
    public static final String ERASER_TOOL = "EraserTool";

    /**
     * 处理鼠标按下
     * @param e MouseEvent
     */
    public void mousePressed(MouseEvent e);

    /**
     * 处理鼠标松开
     * @param e MouseEvent
     */
    public void mouseReleased(MouseEvent e);

    /**
     * 处理鼠标拖动
     * @param e MouseEvent
     */
    public void mouseDragged(MouseEvent e);

    /**
     * 处理鼠标移动（非拖动）
     * @param e MouseEvent
     */
    public void mouseMoved(MouseEvent e);
}
