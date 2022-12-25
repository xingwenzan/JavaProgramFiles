package gobang.step5.client;

import step5.pad.FIRPad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 五子棋客户端
 */
public class FIRClient extends JFrame implements ActionListener, KeyListener {

	/**
	 *  下棋区
	 */
    FIRPad firPad = new FIRPad();

	/**
	 *  面板区
	 */
    Panel centerPanel = new Panel();

	/**
	 * 构造方法，创建界面
	 */
	public FIRClient() {
        super("Java 五子棋客户端");
        setLayout(new BorderLayout());
        centerPanel.add(firPad, BorderLayout.CENTER);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        add(centerPanel, BorderLayout.CENTER);
        // 调整此窗口的大小，以适合其子组件的首选大小和布局。
        pack();
        setSize(670, 560);
        setVisible(true);
        //是否可以用户调整窗口大小
        setResizable(false);
        this.validate();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public static void main(String[] args) {
        FIRClient chessClient = new FIRClient();
    }


}
