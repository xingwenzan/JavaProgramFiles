package gobang.djr.chess.server;

import javax.swing.*;
import java.awt.*;


/**
 * 主机面板类
 */
public class ServerMsgPanel extends Panel {
    public TextArea msgTextArea = new TextArea("", 22, 50,
            TextArea.SCROLLBARS_VERTICAL_ONLY);
    public JLabel statusLabel = new JLabel("当前连接数:", Label.LEFT);
    public Panel msgPanel = new Panel();
    public Panel statusPanel = new Panel();

    public ServerMsgPanel() {
        setSize(350, 300);
        setBackground(Color.LIGHT_GRAY);
        setLayout(new BorderLayout());
        msgPanel.setLayout(new FlowLayout());
        msgPanel.setSize(210, 210);
        statusPanel.setLayout(new BorderLayout());
        statusPanel.setSize(210, 50);
        msgPanel.add(msgTextArea);
        statusPanel.add(statusLabel, BorderLayout.WEST);
        add(msgPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.NORTH);
    }
}
