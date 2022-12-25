package gobang.djr.chess.gui;

import javax.swing.*;
import java.awt.*;

/**
 * 底部用户控制部分
 */
public class UserControlPad extends JPanel {
    public JLabel ipLabel = new JLabel("IP", JLabel.LEFT);
    public TextField ipInputted = new TextField("localhost", 10);
    public JButton connectButton = new JButton("连接到服务器");
    public JButton createButton = new JButton("创建游戏");
    public JButton joinButton = new JButton("加入游戏");
    public JButton cancelButton = new JButton("放弃游戏");
    public JButton exitButton = new JButton("退出程序");

    public UserControlPad() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.LIGHT_GRAY);
        add(ipLabel);
        add(ipInputted);
        add(connectButton);
        add(createButton);
        add(joinButton);
        add(cancelButton);
        add(exitButton);
    }
}
