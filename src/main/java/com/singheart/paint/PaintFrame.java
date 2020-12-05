package com.singheart.paint;

import com.singheart.paint.Tool.*;
import com.singheart.paint.listener.QuickKeyListener;
import com.singheart.paint.shape.Shape;

import static com.singheart.paint.Tool.Tool.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class PaintFrame extends JFrame{
    private PaintService service = null;
    private DrawPanel mDrawPanel = null;
    //当前正在使用的工具
    private Tool mTool = null;
    //画板上已绘制的图形
    private List<Shape> shapes = new ArrayList<Shape>();
    //保存画板上撤销的图形
    private List<Shape> tmp_shapes = new ArrayList<Shape>();

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
                System.out.println(shapes.size());
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
        menuDict.put("文件(F)", new String[]{"新建(N)", "打开(O)", "保存(S)", "-", "退出"});
        menuDict.put("编辑(E)", new String[]{"撤销(Ctrl+Z)", "重做(Ctrl+Y)"});
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
                    menuItem.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            service.handleMenuItem(PaintFrame.this, actionEvent.getActionCommand());
                        }
                    });
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
        String[] toolArr = {Tool.PENCIL_TOOL, Tool.RECT_TOOL, Tool.LINE_TOOL,
                CIRCEL_TOOL, ERASER_TOOL};
        for (int i = 0; i < toolArr.length; i++) {
            JButton toolButton = new JButton(new ToolButtonClickAction(
                    new ImageIcon("img/" + toolArr[i] + ".jpg"),
                    this,
                    toolArr[i]));
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
        //流式布局，并设置左对齐,不设置会跑到中间去
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JToolBar toolBar = new JToolBar("颜色");
        toolBar.setLayout(new GridLayout(1, 6));
        JButton button1 = new JButton("hello");
        JButton button2 = new JButton("hello");
        JButton button3 = new JButton("hello");
        toolBar.add(button1);
        toolBar.add(button2);
        toolBar.add(button3);
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
        drawPanel.setPreferredSize(new Dimension(300, 300));
        return drawPanel;
    }

    public List<Shape> getShapes() {
        if (shapes == null) {
            shapes = new ArrayList<Shape>();
        }
        return shapes;
    }

    public List<Shape> getTmp_shapes() {
        if (tmp_shapes == null) {
            tmp_shapes = new ArrayList<Shape>();
        }
        return tmp_shapes;
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



    public class DrawPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            service.paint((Graphics2D) g.create(), shapes);
        }
    }


}
