package com.singheart.paint.listener;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.Tool.AbstractTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ColorAction extends AbstractAction {
    private PaintFrame paintFrame = null;
    private Color color = null;

    public ColorAction(PaintFrame paintFrame, Color color) {
        this.paintFrame = paintFrame;
        this.color = color;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        AbstractTool.color = color;
        paintFrame.getCurColorPnl().setBackground(color);
    }
}
