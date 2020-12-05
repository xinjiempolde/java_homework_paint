package com.singheart.paint;

import com.singheart.paint.shape.Shape;

import java.awt.*;
import java.util.List;

/**
 * 处理业务逻辑
 */
public class PaintService {
    public void paint(Graphics2D g2d, List<Shape> shapes) {
        for (Shape shape : shapes) {
            shape.drawShape(g2d);
        }
    }

    public void handleMenuItem(PaintFrame paintFrame, String name) {
        System.out.println(name);
        if (name == "撤销(Ctrl+Z)") {
            revoke(paintFrame);
        } else if (name == "重做(Ctrl+Y)") {
            redo(paintFrame);
        }
    }

    /**
     * 撤销上一次操作
     * @param paintFrame
     */
    public void revoke(PaintFrame paintFrame) {
        int lastIdx = paintFrame.getShapes().size() - 1;
        //已撤销到最初状态
        if (lastIdx < 0) {
            return;
        }
        paintFrame.getTmp_shapes().add(paintFrame.getShapes().get(lastIdx));
        paintFrame.getShapes().remove(lastIdx);
        paintFrame.repaintDrawPanel();
    }

    /**
     * 恢复撤销
     * @param paintFrame
     */
    public void redo(PaintFrame paintFrame) {
        int lastIdx = paintFrame.getTmp_shapes().size() - 1;
        if (lastIdx < 0) {
            return;
        }
        paintFrame.getShapes().add(paintFrame.getTmp_shapes().get(lastIdx));
        paintFrame.getTmp_shapes().remove(lastIdx);
        paintFrame.repaintDrawPanel();
    }


}
