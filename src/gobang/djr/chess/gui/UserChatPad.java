package gobang.djr.chess.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 用户聊天面板
 */
public class UserChatPad extends JPanel
{
	public JTextArea chatTextArea = new JTextArea("命令区域", 18, 20);
	public UserChatPad()
	{
		setLayout(new BorderLayout());
		chatTextArea.setAutoscrolls(true);
		chatTextArea.setLineWrap(true);
		add(chatTextArea, BorderLayout.CENTER);
	}
}
