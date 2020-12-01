import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import static Tool.Tool.*; //导入接口中的所有静态变量

public class PaintFrame  extends JFrame {
    private JPanel mDrawPanel;
    public PaintFrame() {
        this.init();
    }

    /**
     * 完成一些界面初始化的操作
     */
    private void init() {
        this.createMenuBar();
        this.createToolBar();
        this.createColorPanel();
        mDrawPanel = createDrawPanel();
        this.add(mDrawPanel, BorderLayout.CENTER);
        
    }

    /**
     * 创建上方的菜单栏
     */
    private void createMenuBar() {
        Map<String, String[]> menuDict = new HashMap<String, String[]>();
        menuDict.put("文件(F)",new String[]{"新建(N)","打开(O)","保存(S)","-","退出"});
        JMenuBar menuBar = new JMenuBar();
        for (String key : menuDict.keySet()) {
            //创建一级菜单
            JMenu menu = new JMenu(key);
            //创建二级菜单
            for(String value : menuDict.get(key)) {
                // 是否为分割线
                if (value == "-") {
                    menu.addSeparator();
                } else {
                    JMenuItem menuItem = new JMenuItem(value);
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
     * @return 承载工具栏的JPanel
     */
    public JPanel createToolBar() {
        JPanel panel = new JPanel();
        JToolBar toolBar = new JToolBar();
        String[] toolArr = new String[] {PENCIL_TOOL, RECT_TOOL, LINE_TOOL};
        for (int i = 0; i < toolArr.length; i++) {
            JButton toolButton = new JButton(new ImageIcon(
                    "img/" + toolArr[i] + ".jpg"));
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

    public JPanel createDrawPanel() {
        JPanel drawPanel = new JPanel();
        drawPanel.setPreferredSize(new Dimension(300, 300));
        return drawPanel;
    }
}
