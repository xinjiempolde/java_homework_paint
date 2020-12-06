package com.singheart.paint.listener;

import com.singheart.paint.PaintFrame;
import com.singheart.paint.Tool.Tool;
import com.singheart.paint.Tool.ToolFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * JButton的构造方法中有Action，而AbstractAction集成自Action
 */
public class ToolButtonClickAction extends AbstractAction {
    //工具的名称
    private String toolName = null;
    private PaintFrame paintFrame = null;
    private Tool tool = null;

    public ToolButtonClickAction(PaintFrame paintFrame, String toolName) {
        this.paintFrame = paintFrame;
        this.toolName = toolName;
    }

    public ToolButtonClickAction(ImageIcon icon, PaintFrame paintFrame, String toolName) {
        super("", icon);
        //super()和this()不能一起用
        this.paintFrame = paintFrame;
        this.toolName = toolName;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (tool == null) {
            tool = ToolFactory.getInstance(paintFrame, toolName);
        }
        paintFrame.setTool(tool);
    }
}
