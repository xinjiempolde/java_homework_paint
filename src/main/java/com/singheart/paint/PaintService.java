package com.singheart.paint;

import com.singheart.paint.shape.*;
import com.singheart.paint.shape.Shape;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import static com.singheart.paint.PaintFrame.*;

import java.awt.*;
import java.io.*;
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
        switch (name) {
            case "撤销(Ctrl+Z)":
                revoke(paintFrame);
                break;
            case "重做(Ctrl+Y)":
                redo(paintFrame);
                break;
            case "保存(Ctrl+S)":
                savaFile(paintFrame);
                break;
            case "打开(Ctrl+O)":
                openFile(paintFrame);
                break;
            case "清空(Ctrl+L)":
                clear(paintFrame);
                break;
            default:
                break;
        }
    }

    /**
     * 撤销上一次操作
     * @param paintFrame
     */
    public void revoke(PaintFrame paintFrame) {
        // 绘制栈弹出到重做栈
        Shape shape = paintFrame.popShape(PAINT_SHAPES);
        if (shape != null) {
            paintFrame.addNewShape(REDO_SHAPES, shape);
        }
        paintFrame.repaintDrawPanel();
    }

    /**
     * 恢复撤销
     * @param paintFrame
     */
    public void redo(PaintFrame paintFrame) {
        // 重做栈弹出到绘制栈
        Shape shape = paintFrame.popShape(REDO_SHAPES);
        if (shape != null) {
            paintFrame.addNewShape(PAINT_SHAPES, shape);
        }
        paintFrame.repaintDrawPanel();
    }

    public void savaFile(PaintFrame paintFrame) {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showSaveDialog(null);
            File file = chooser.getSelectedFile();
            if (file == null) {
                return;
            }
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Shape shape : paintFrame.getShapes()) {
                shape.writeFile(printWriter);
            }
            printWriter.close();
            JOptionPane.showMessageDialog(null, "文件保存成功", "消息", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openFile(PaintFrame paintFrame) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            if (file == null) {
                return;
            }
            FileReader fileReader = new FileReader(file);
            paintFrame.clear();
            while (fileReader.ready()) {
                switch (fileReader.read()) {
                    case 'L':
                        paintFrame.addNewShape(new Line(fileReader));
                        break;
                    case 'C':
                        paintFrame.addNewShape(new Circle(fileReader));
                        break;
                    case 'R':
                        paintFrame.addNewShape(new Rect(fileReader));
                        break;
                    case 'P':
                        fileReader.read();  //去除换行符
                        paintFrame.addNewShape(new PencilShape(fileReader));
                        break;
                    case 'A':
                        fileReader.read(); //去除换行符
                        paintFrame.addNewShape(new AtomizerShape(fileReader));
                        break;
                    case 'T':
                        paintFrame.addNewShape(new Text(fileReader));
                        break;
                    default:
                        break;
                }
            }
            paintFrame.repaintDrawPanel();
            JOptionPane.showMessageDialog(null, "打开文件成功！", "消息", 1);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear(PaintFrame paintFrame) {
        paintFrame.clear();
        paintFrame.repaintDrawPanel();

    }


}
