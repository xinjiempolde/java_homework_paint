package com.singheart.paint;

import com.singheart.paint.Tool.*;
import com.singheart.paint.listener.ColorAction;
import com.singheart.paint.listener.MenuListener;
import com.singheart.paint.listener.QuickKeyListener;
import com.singheart.paint.listener.ToolButtonClickAction;
import com.singheart.paint.shape.Line;
import com.singheart.paint.shape.Shape;

import static com.singheart.paint.Tool.Tool.*;
import static java.awt.Color.*;
import static java.awt.Color.YELLOW;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class PaintFrame extends JFrame{
    public static final int PAINT_SHAPES = 0;
    public static final int REDO_SHAPES = 1;
    private PaintService service = null;
    private DrawPanel mDrawPanel = null;
    private JPanel mCurColorPnl = null;
    //当前正在使用的工具
    private Tool mTool = null;
    //画板上已绘制的图形
    private List<Shape> paint_shapes = new ArrayList<Shape>();
    //保存画板上撤销的图形
    private List<Shape> redo_shapes = new ArrayList<Shape>();

    public PaintFrame() {
        this.init();
    }


    /**
     * 完成一些界面初始化的操作
     */
    private void init() {
        // 添加按键监听事件
        this.getToolkit().addAWTEventListener(new QuickKeyListener(this)
                , AWTEvent.KEY_EVENT_MASK);
        this.createMenuBar();
        this.createToolBar();
        this.createColorPanel();
        this.setFocusable(true);
        mDrawPanel = createDrawPanel();
        service = new PaintService();
        mTool = ToolFactory.getInstance(this, LINE_TOOL);
        this.add(mDrawPanel, BorderLayout.CENTER);
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mTool.mousePressed(e);
                System.out.println(paint_shapes.size());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mTool.mouseReleased(e);
            }
        };
        MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mTool.mouseDragged(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mTool.mouseMoved(e);
            }
        };
        mDrawPanel.addMouseListener(mouseListener);
        mDrawPanel.addMouseMotionListener(mouseMotionListener);
    }

    /**
     * 创建上方的菜单栏
     */
    private void createMenuBar() {
        Map<String, String[]> menuDict = new LinkedHashMap<String, String[]>();
        menuDict.put("文件(F)", new String[]{"新建(Ctrl+N)", "打开(Ctrl+O)", "保存(Ctrl+S)", "-", "退出(Ctrl+Q)"});
        menuDict.put("编辑(E)", new String[]{"撤销(Ctrl+Z)", "重做(Ctrl+Y)", "清空(Ctrl+L)"});
        JMenuBar menuBar = new JMenuBar();
        for (String key : menuDict.keySet()) {
            //创建一级菜单
            JMenu menu = new JMenu(key);
            //创建二级菜单
            for (String value : menuDict.get(key)) {
                // 是否为分割线
                if (value == "-") {
                    menu.addSeparator();
                } else {
                    JMenuItem menuItem = new JMenuItem(value);
                    menuItem.addActionListener(new MenuListener(this));
                    menu.add(menuItem);
                }
            }
            menuBar.add(menu);
        }
        //这个类就是重写的JFrame;
        this.setJMenuBar(menuBar);
    }

    /**
     * 创建左侧工具栏
     *
     * @return 承载工具栏的JPanel
     */
    public JPanel createToolBar() {
        JPanel panel = new JPanel();
        JToolBar toolBar = new JToolBar();
        toolBar.setOrientation(JToolBar.VERTICAL);
        String[] toolArr = {PENCIL_TOOL, RECT_TOOL, LINE_TOOL,
                CIRCEL_TOOL, ERASER_TOOL, ATOMIZER_TOOL, BRUSH_TOOL,
                WORD_TOOL};
        for (int i = 0; i < toolArr.length; i++) {
            JButton toolButton = new JButton(new ToolButtonClickAction(
                    new ImageIcon("img/" + toolArr[i] + ".jpg"),
                    this,
                    toolArr[i]));
            toolButton.setPreferredSize(new Dimension(25, 25));
            toolBar.add(toolButton);
        }
        panel.add(toolBar);
        this.add(panel, BorderLayout.WEST);
        return panel;
    }

    /**
     * 构造下方颜色选择菜单栏
     */
    public JPanel createColorPanel() {
        JPanel panel = new JPanel();
        mCurColorPnl = new JPanel();
        //流式布局，并设置左对齐,不设置会跑到中间去
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mCurColorPnl.setBackground(AbstractTool.color);
        mCurColorPnl.setPreferredSize(new Dimension(20, 20));
        JToolBar toolBar = new JToolBar("颜色");
        toolBar.setFloatable(false);
        toolBar.setLayout(new GridLayout(2, 6));
        // Color类中的已有颜色
        Color[] colorArr = {BLACK, BLUE, CYAN, GRAY, GREEN, LIGHT_GRAY,
                MAGENTA, ORANGE, PINK, RED, WHITE, YELLOW};

        //设置一个往上凸起的边框
        BevelBorder border = new BevelBorder(1,Color.WHITE,Color.WHITE);
        for (int i = 0; i < colorArr.length; i++) {
            JButton button = new JButton();
            button.setBorder(border);
            button.setPreferredSize(new Dimension(15, 15));
            button.setBackground(colorArr[i]);
            button.addActionListener(new ColorAction(this, colorArr[i]));
            toolBar.add(button);
        }
        panel.add(mCurColorPnl);
        panel.add(toolBar);
        this.add(panel, BorderLayout.SOUTH);
        return panel;
    }

    /**
     * 创建绘图区域
     *
     * @return DrawPanel
     */
    public DrawPanel createDrawPanel() {
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setBackground(Color.WHITE);
        // 设置绝对布局
        drawPanel.setLayout(null);
        drawPanel.setPreferredSize(new Dimension(500, 500));
        return drawPanel;
    }
    
    

    public void repaintDrawPanel() {
        mDrawPanel.repaint();
    }

    public void setTool(Tool tool) {
        this.mTool = tool;
    }

    public PaintService getService() {
        return this.service;
    }

    public List<Shape> getShapes() {
        return paint_shapes;
    }

    public JPanel getCurColorPnl() {
        return mCurColorPnl;
    }

    public DrawPanel getmDrawPanel() {
        return mDrawPanel;
    }

    public void addNewShape(int whichShapes, Shape shape) {
        if (whichShapes == PAINT_SHAPES) {
            paint_shapes.add(shape);
        } else if (whichShapes == REDO_SHAPES) {
            redo_shapes.add(shape);
        }
    }

    public void addNewShape(Shape shape) {
        paint_shapes.add(shape);
    }
    
    public Shape popShape(int whichShapes) {
        List<Shape> shapes = null;
        if (whichShapes == PAINT_SHAPES) {
            shapes = paint_shapes;
        } else if (whichShapes == REDO_SHAPES) {
            shapes = redo_shapes;
        }
        int lastIdx = shapes.size() - 1;
        if (lastIdx < 0) {
            return null; //没有任何物体
        }
        Shape shape = shapes.get(lastIdx);
        shapes.remove(lastIdx);
        return shape;
    }

    public void clear() {
        paint_shapes.clear();
        redo_shapes.clear();
    }



    public class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            service.paint((Graphics2D) g.create(), paint_shapes);
        }
    }


}
