package com.singheart.paint.shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 基本形状，抽象类
 */
public abstract class Shape {
    // x1,y1,x2,y2为绘制图形的开始点和结束点
    protected int x1, y1, x2, y2;
    protected Color color = null;
    protected int linewidth = 1;

    // 左上角坐标和长、宽
    protected int width, height, x, y;

    protected static int readAttribute(FileReader fileReader, char[] buffer) {
        try {
            char c = (char)fileReader.read(); // 读取分隔符
            fileReader.read(buffer, 0, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(new String(buffer));
    }

    public Shape(FileReader fileReader) {
        char[] buffer = new char[10];
        color = new Color(readAttribute(fileReader, buffer));
        linewidth = readAttribute(fileReader, buffer);
        x1 = readAttribute(fileReader, buffer);
        y1 = readAttribute(fileReader, buffer);
        x2 = readAttribute(fileReader, buffer);
        y2 = readAttribute(fileReader, buffer);

        //计算宽和高
        this.width = Math.abs(x1 - x2);
        this.height = Math.abs(y1 - y2);

        //计算最左上角的坐标
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);

    }
    public Shape(int x1, int y1, int x2, int y2, Color color, int width) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.linewidth = width;

        //计算宽和高
        this.width = Math.abs(x1 - x2);
        this.height = Math.abs(y1 - y2);

        //计算最左上角的坐标
        this.x = Math.min(x1, x2);
        this.y = Math.min(y1, y2);
    }

    public Shape(int x1, int y1, int x2, int y2) {
        this(x1, y1, x2, y2, Color.BLACK, 1);
    }

    public Shape() {
        this(0, 0, 0, 0);
    }
    public void drawShape(Graphics g){
    }

    public void writeFile(PrintWriter printWriter){
        printWriter.printf("*%010d*%010d*%010d*%010d*%010d*%010d\n"
                ,color.getRGB(),linewidth, x1, y1, x2, y2);
    }

}
