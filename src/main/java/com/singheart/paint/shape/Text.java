package com.singheart.paint.shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Text extends Shape {
    private String content = null;

    public Text(int x1, int y1, int x2, int y2, String content) {
        super(x1, y1, x2, y2);
        this.content = content;
    }

    public Text(FileReader fileReader) {
        char[] buffer = new char[10];
        try {
            fileReader.read();
            StringBuilder content = new StringBuilder();
            char c;
            while( (c=(char)fileReader.read()) != '*') {
                content.append(c);
            }
            this.content = content.toString();
            fileReader.read(buffer, 0, 10);
            this.x1 = Integer.parseInt(new String(buffer));
            fileReader.read();
            fileReader.read(buffer, 0, 10);
            this.y1 = Integer.parseInt(new String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void drawShape(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawString(content, x1, y1);
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.print('T');
        printWriter.print('*');
        printWriter.print(content);
        printWriter.print('*');
        printWriter.printf("%010d*%010d\n", x1, y1);
    }
}
