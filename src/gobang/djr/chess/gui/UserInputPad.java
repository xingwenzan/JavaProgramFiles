package gobang.djr.chess.gui;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * 用户输入区
 */
public class UserInputPad extends JPanel
{
	public JTextField contentInputted = new JTextField("", 26);
	public JComboBox userChoice = new JComboBox();

	public UserInputPad()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < 50; i++)
		{
			userChoice.addItem(i + "." + "无用户");
		}
		userChoice.setSize(60, 24);
		add(userChoice);
		add(contentInputted);
	}
}
