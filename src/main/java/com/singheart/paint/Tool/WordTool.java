package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.shape.Text;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;

public class WordTool extends AbstractTool {
    private static Tool tool = null;
    private boolean isShow = false;
    private JTextArea textArea = null;

    public static Tool getInstance(PaintFrame paintFrame) {
        if (tool == null) {
            tool = new WordTool(paintFrame);
        }
        return tool;
    }

    private WordTool(PaintFrame paintFrame) {
        super(paintFrame);
    }
    private WordTool(PaintFrame paintFrame, String cursorPath) {
        super(paintFrame, cursorPath);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        // 已经添加了textArea
        if (isShow == true) {
            isShow = false;
            String content = textArea.getText();
            paintFrame.addNewShape(new Text(
                    textArea.getX(), textArea.getY()+13,
                    textArea.getWidth(), textArea.getHeight(), content));
            paintFrame.getmDrawPanel().remove(textArea);

        }
        paintFrame.repaintDrawPanel();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xDistance = Math.abs(mPressedX - e.getX());
        int yDistance = Math.abs(mPressedY - e.getY());
        int minX = Math.min(mPressedX, e.getX());
        int minY = Math.min(mPressedY, e.getY());
        if (xDistance < 20 || yDistance < 20) {
            return;
        }
        textArea = new JTextArea(5, 10);
        textArea.setBounds(minX, minY, xDistance, yDistance);
        textArea.setBackground(Color.WHITE);
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        paintFrame.getmDrawPanel().add(textArea);
        isShow = true;
        paintFrame.repaintDrawPanel();
    }
}
