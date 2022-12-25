package gobang.step2.client;

import step2.pad.FIRPad;

import javax.swing.*;
import java.awt.*;

/**
 * 五子棋客户端
 */
public class FIRClient extends JFrame {

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




    public static void main(String[] args) {
        FIRClient chessClient = new FIRClient();
    }
}
