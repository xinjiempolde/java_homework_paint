package com.singheart.paint.shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AtomizerShape extends Shape {
    private List<FillRect> rects = null;

    public AtomizerShape(List<FillRect> rects) {
        this.rects = rects;
    }

    public AtomizerShape(FileReader fileReader) {
        rects = new ArrayList<FillRect>();
        try {
            while (fileReader.read() != 'E') {
                if (fileReader.read() != 'F') break; // 读取'F'
                rects.add(new FillRect(fileReader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void add(FillRect rect) {
        rects.add(rect);
    }
    @Override
    public void drawShape(Graphics g) {
        for (Shape rect : rects) {
            rect.drawShape(g);
        }
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.println("A");
        for (FillRect rect : rects) {
            rect.writeFile(printWriter);
        }
        printWriter.println("E");
    }
}
