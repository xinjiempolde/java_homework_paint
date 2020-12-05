package com.singheart.paint.listener;

import com.singheart.paint.PaintFrame;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

/**
 * 用于监听快捷键，如Ctrl+Z和Ctrl+Y
 */
public class QuickKeyListener implements AWTEventListener
{
    private PaintFrame paintFrame = null;

    public QuickKeyListener(PaintFrame paintFrame) {
        this.paintFrame = paintFrame;
    }


    @Override
    public void eventDispatched(AWTEvent event) {
        if(event instanceof KeyEvent){
            KeyEvent key = (KeyEvent)event;
            // 按键被按下
            if(key.getID()==KeyEvent.KEY_PRESSED){
                // 撤销快捷键
                if (key.isControlDown() && key.getKeyCode() == KeyEvent.VK_Z) {
                    paintFrame.getService().revoke(paintFrame);
                }

                // 恢复快捷键
                if (key.isControlDown() && key.getKeyCode() == KeyEvent.VK_Y) {
                    paintFrame.getService().redo(paintFrame);
                }
            }
        }
    }
}
