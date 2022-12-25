package gobang.step6.pad;

import javax.swing.*;
import java.awt.*;

/**
 * 底部用户控制部分
 */
public class UserControlPad extends JPanel {
    public JButton connectButton = new JButton("连接到服务器");
    public JButton createButton = new JButton("创建游戏");

    public UserControlPad() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.LIGHT_GRAY);
        add(connectButton);
        add(createButton);
    }
}
