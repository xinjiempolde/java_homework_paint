package com.singheart.paint.Tool;

import com.singheart.paint.PaintFrame;

import java.util.HashMap;
import java.util.Map;
import static com.singheart.paint.Tool.Tool.*;

public class ToolFactory {
    private static Map<String, Tool> toolMap = null;

    public static Tool getInstance(PaintFrame paintFrame, String toolType) {
        if (toolMap == null) {
            toolMap = new HashMap<String, Tool>();
            toolMap.put(LINE_TOOL, LineTool.getInstance(paintFrame));
            toolMap.put(RECT_TOOL, RectTool.getInstance(paintFrame));
            toolMap.put(CIRCEL_TOOL, CircleTool.getInstance(paintFrame));
            toolMap.put(PENCIL_TOOL, PencilTool.getInstance(paintFrame));
            toolMap.put(ERASER_TOOL, EraserTool.getInstance(paintFrame));
            toolMap.put(ATOMIZER_TOOL, AtomizerTool.getInstance(paintFrame));
            toolMap.put(BRUSH_TOOL, BrushTool.getInstance(paintFrame));
            toolMap.put(WORD_TOOL, WordTool.getInstance(paintFrame));
        }
        return toolMap.get(toolType);
    }
}
