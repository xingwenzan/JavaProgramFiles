package gobang.step6.client;

import step6.pad.FIRPad;
import step6.pad.UserControlPad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 五子棋客户端
 */
public class FIRClient extends JFrame implements ActionListener, KeyListener {

    public JButton connectButton = new JButton("连接到服务器");
    public JButton createButton = new JButton("创建游戏");
    /**
     * 用户操作区
     */
    UserControlPad userControlPad = new UserControlPad();


    /**
     * 主机地址
     */
    String host = "localhost";
    /**
     * 主机端口
     */
    int port = 4331;

    /**
     * 客户端套接口
     */
    Socket clientSocket;
    /**
     * 数据输入流
     */
    DataInputStream inputStream;
    /**
     * 数据输出流
     */
    DataOutputStream outputStream;


	/**
	 *  下棋区
	 */
    FIRPad firPad = new FIRPad();

	/**
	 *  面板区
	 */
    Panel centerPanel = new Panel();
    Panel southPanel = new Panel();

	/**
	 * 构造方法，创建界面
	 */
	public FIRClient() {
        super("Java 五子棋客户端");
        setLayout(new BorderLayout());
        centerPanel.add(firPad, BorderLayout.CENTER);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        add(centerPanel, BorderLayout.CENTER);

        userControlPad.connectButton.addActionListener(this);
        userControlPad.createButton.addActionListener(this);

        southPanel.add(userControlPad, BorderLayout.CENTER);
        southPanel.setBackground(Color.LIGHT_GRAY);

        add(southPanel, BorderLayout.SOUTH);
        // 调整此窗口的大小，以适合其子组件的首选大小和布局。
        pack();
        setSize(670, 560);
        setVisible(true);
        //是否可以用户调整窗口大小
        setResizable(false);
        this.validate();
    }

    /**
     * 按指定的IP地址和端口连接到服务器
     * @param serverIP
     * @param serverPort
     * @return
     * @throws Exception
     */
    public boolean connectToServer(String serverIP, int serverPort) throws Exception {
        try {
            System.out.println(InetAddress.getLocalHost().toString());
            // 创建客户端套接口
            clientSocket = new Socket(serverIP, serverPort);
            // 创建输入流
            inputStream = new DataInputStream(clientSocket.getInputStream());
            // 创建输出流
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
            // 创建客户端线程
            FIRClientThread clientthread = new FIRClientThread(this);
            // 启动线程，等待聊天信息
            clientthread.start();
            return true;
        } catch (IOException ex) {

        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 连接到主机按钮单击事件
        if (e.getSource() == userControlPad.connectButton) {
            // 取得主机地址
            try {
                // 成功连接到主机时，设置客户端相应的界面状态
                if (connectToServer(host, port)) {
                    firPad.statusText.setText("连接成功，请等待!");
                }
            } catch (Exception ei) {

            }
        }



        if (e.getSource() == userControlPad.createButton) {
            try {
                this.outputStream.writeUTF("hello");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

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
