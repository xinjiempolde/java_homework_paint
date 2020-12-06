package com.singheart.paint.shape;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.singheart.paint.Tool.PencilTool;
import com.singheart.paint.shape.Line;

public class PencilShape extends Shape {
    private List<Line> lines = null;

    public PencilShape(List<Line> lines) {
        this.lines = lines;
    }

    public PencilShape(FileReader fileReader) {
        lines = new ArrayList<Line>();
        try {
            while (fileReader.read() != 'E') {
                if (fileReader.read() != 'L') break; // 读取'L'
                lines.add(new Line(fileReader));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void add(Line line) {
        lines.add(line);
    }
    @Override
    public void drawShape(Graphics g) {
        for (Line line : lines) {
            line.drawShape(g);
        }
    }

    @Override
    public void writeFile(PrintWriter printWriter) {
        printWriter.println("P");
        for (Line line : lines) {
            line.writeFile(printWriter);
        }
        printWriter.println("E");
    }
}
