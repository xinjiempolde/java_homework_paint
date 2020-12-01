import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        PaintFrame f = new PaintFrame();
        //pack是调整窗口大小，尽量满足每一个组件的最优大小的情况下让窗口最小化。
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
