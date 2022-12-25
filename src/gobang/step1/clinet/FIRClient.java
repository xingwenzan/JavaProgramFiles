package gobang.step1.clinet;

import javax.swing.*;
import java.awt.*;


/**
 * 五子棋客户端
 */
public class FIRClient extends JFrame {
	
	/**
	 * 构造方法，创建界面
	 */
	public FIRClient() {
		super("Java 五子棋客户端");
		setLayout(new BorderLayout());
		setSize(670, 560);
		setVisible(true);
		// 是否可以用户调整窗口大小
		setResizable(false);
		this.validate();
	}
	
	public static void main(String[] args) {
		FIRClient chessClient = new FIRClient();
	}
}
