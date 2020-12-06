package com.singheart.paint.listener;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.PaintService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
    private PaintFrame paintFrame = null;

    public MenuListener(PaintFrame paintFrame) {
        this.paintFrame = paintFrame;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        paintFrame.getService().handleMenuItem(paintFrame, actionEvent.getActionCommand());
    }
}
