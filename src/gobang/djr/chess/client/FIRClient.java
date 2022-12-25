package gobang.djr.chess.client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

import djr.chess.gui.UserChatPad;
import djr.chess.gui.UserControlPad;
import djr.chess.gui.UserInputPad;
import djr.chess.gui.UserListPad;
import djr.chess.pad.FIRPad;

/**
 * 五子棋客户端
 */
public class FIRClient extends JFrame implements ActionListener, KeyListener {
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
     * 用户名
     */
    String chessClientName = null;
    /**
     * 主机地址
     */
    String host = null;
    /**
     * 主机端口
     */
    int port = 4331;
    /**
     * 是否在聊天
     */
    boolean isOnChat = false;
    /**
     * 是否在下棋
     */
    boolean isOnChess = false;
    /**
     * 游戏是否进行中
     */
    boolean isGameConnected = false;
    /**
     * 是否为游戏创建者
     */
    boolean isCreator = false;
    /**
     * 是否为游戏加入者
     */
    boolean isParticipant = false;
    /**
     * 用户列表区
     */
    UserListPad userListPad = new UserListPad();
    /**
     * 用户聊天区
     */
    UserChatPad userChatPad = new UserChatPad();
    /**
     * 用户操作区
     */
    UserControlPad userControlPad = new UserControlPad();
    /**
     * 用户输入区
     */
    UserInputPad userInputPad = new UserInputPad();

	/**
	 *  下棋区
	 */
    FIRPad firPad = new FIRPad();

	/**
	 *  面板区
	 */
    Panel southPanel = new Panel();
    Panel northPanel = new Panel();
    Panel centerPanel = new Panel();
    Panel eastPanel = new Panel();

	/**
	 * 构造方法，创建界面
	 */
	public FIRClient() {
        super("Java 五子棋客户端");
        setLayout(new BorderLayout());
        host = userControlPad.ipInputted.getText();

        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(userListPad, BorderLayout.NORTH);
        eastPanel.add(userChatPad, BorderLayout.CENTER);
        eastPanel.setBackground(Color.LIGHT_GRAY);

        userInputPad.contentInputted.addKeyListener(this);

        firPad.host = userControlPad.ipInputted.getText();
        centerPanel.add(firPad, BorderLayout.CENTER);
        centerPanel.add(userInputPad, BorderLayout.SOUTH);
        centerPanel.setBackground(Color.LIGHT_GRAY);

        userControlPad.connectButton.addActionListener(this);
        userControlPad.createButton.addActionListener(this);
        userControlPad.joinButton.addActionListener(this);
        userControlPad.cancelButton.addActionListener(this);
        userControlPad.exitButton.addActionListener(this);

        userControlPad.createButton.setEnabled(false);
        userControlPad.joinButton.setEnabled(false);
        userControlPad.cancelButton.setEnabled(false);

        southPanel.add(userControlPad, BorderLayout.CENTER);
        southPanel.setBackground(Color.LIGHT_GRAY);

        addWindowListener(new WindowAdapter() {
            @Override
			public void windowClosing(WindowEvent e) {
				// 聊天中
                if (isOnChat) {
					// 关闭客户端套接口
                    try {
                        clientSocket.close();
                    } catch (Exception ed) {
                    }
                }
				// 下棋中
                if (isOnChess || isGameConnected) {
                    try {
						// 关闭下棋端口
                        firPad.chessSocket.close();
                    } catch (Exception ee) {
                    }
                }
                System.exit(0);
            }
        });

        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);
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
            isOnChat = true;
            return true;
        } catch (IOException ex) {
            userChatPad.chatTextArea
                    .setText("不能连接!\n");
        }
        return false;
    }


	/**
	 * 客户端事件处理
	 * @param e
	 */
    @Override
	public void actionPerformed(ActionEvent e) {
		// 连接到主机按钮单击事件
        if (e.getSource() == userControlPad.connectButton) {
			// 取得主机地址
            host = firPad.host = userControlPad.ipInputted.getText();
            try {
				// 成功连接到主机时，设置客户端相应的界面状态
                if (connectToServer(host, port)) {
                    userChatPad.chatTextArea.setText("");
                    userControlPad.connectButton.setEnabled(false);
                    userControlPad.createButton.setEnabled(true);
                    userControlPad.joinButton.setEnabled(true);
                    firPad.statusText.setText("连接成功，请等待!");
                }
            } catch (Exception ei) {
                userChatPad.chatTextArea.setText("不能连接!\n");
            }
        }

		// 离开游戏按钮单击事件
        if (e.getSource() == userControlPad.exitButton) {
            if (isOnChat) {
				// 若用户处于聊天状态中
                try {
					// 关闭客户端套接口
                    clientSocket.close();
                } catch (Exception ed) {
                }
            }
			// 若用户处于游戏状态中
            if (isOnChess || isGameConnected) {
                try {
					// 关闭游戏端口
                    firPad.chessSocket.close();
                } catch (Exception ee) {
                }
            }
            System.exit(0);
        }
		// 加入游戏按钮单击事件
        if (e.getSource() == userControlPad.joinButton) {
			// 取得要加入的游戏
            String selectedUser = (String) userListPad.userList.getSelectedItem();
            if (selectedUser == null || selectedUser.startsWith("[inchess]") ||
                    selectedUser.equals(chessClientName)) {
				// 若未选中要加入的用户，或选中的用户已经在游戏，则给出提示信息
                firPad.statusText.setText("必须选择一个用户!");
            } else {
				// 执行加入游戏的操作
                try {
					// 若游戏套接口未连接
                    if (!isGameConnected) {
						// 若连接到主机成功
                        if (firPad.connectServer(firPad.host, firPad.port)) {
                            isGameConnected = true;
                            isOnChess = true;
                            isParticipant = true;
                            userControlPad.createButton.setEnabled(false);
                            userControlPad.joinButton.setEnabled(false);
                            userControlPad.cancelButton.setEnabled(true);
                            firPad.firThread.sendMessage("/joingame "
                                    + (String) userListPad.userList.getSelectedItem() + " "
                                    + chessClientName);
                        }
                    } else { // 若游戏端口连接中
                        isOnChess = true;
                        isParticipant = true;
                        userControlPad.createButton.setEnabled(false);
                        userControlPad.joinButton.setEnabled(false);
                        userControlPad.cancelButton.setEnabled(true);
                        firPad.firThread.sendMessage("/joingame "
                                + (String) userListPad.userList.getSelectedItem() + " "
                                + chessClientName);
                    }
                } catch (Exception ee) {
                    isGameConnected = false;
                    isOnChess = false;
                    isParticipant = false;
                    userControlPad.createButton.setEnabled(true);
                    userControlPad.joinButton.setEnabled(true);
                    userControlPad.cancelButton.setEnabled(false);
                    userChatPad.chatTextArea
                            .setText("不能连接: \n" + ee);
                }
            }
        }
        if (e.getSource() == userControlPad.createButton) {
        	// 创建游戏按钮单击事件
            try {
                if (!isGameConnected) {
                	// 若游戏端口未连接
                    if (firPad.connectServer(firPad.host, firPad.port)) {
                    	// 若连接到主机成功
                        isGameConnected = true;
                        isOnChess = true;
                        isCreator = true;
                        userControlPad.createButton.setEnabled(false);
                        userControlPad.joinButton.setEnabled(false);
                        userControlPad.cancelButton.setEnabled(true);
                        firPad.firThread.sendMessage("/creatgame "
                                + "[inchess]" + chessClientName);
                    }
                } else {
                	// 若游戏端口连接中
                    isOnChess = true;
                    isCreator = true;
                    userControlPad.createButton.setEnabled(false);
                    userControlPad.joinButton.setEnabled(false);
                    userControlPad.cancelButton.setEnabled(true);
                    firPad.firThread.sendMessage("/creatgame "
                            + "[inchess]" + chessClientName);
                }
            } catch (Exception ec) {
                isGameConnected = false;
                isOnChess = false;
                isCreator = false;
                userControlPad.createButton.setEnabled(true);
                userControlPad.joinButton.setEnabled(true);
                userControlPad.cancelButton.setEnabled(false);
                ec.printStackTrace();
                userChatPad.chatTextArea.setText("不能连接: \n"
                        + ec);
            }
        }
        if (e.getSource() == userControlPad.cancelButton) {
        	// 退出游戏按钮单击事件
            if (isOnChess) {
            	// 游戏中
                firPad.firThread.sendMessage("/giveup " + chessClientName);
                firPad.setVicStatus(-1 * firPad.chessColor);
                userControlPad.createButton.setEnabled(true);
                userControlPad.joinButton.setEnabled(true);
                userControlPad.cancelButton.setEnabled(false);
                firPad.statusText.setText("请创建或加入游戏!");
            }
            if (!isOnChess) {
            	// 非游戏中
                userControlPad.createButton.setEnabled(true);
                userControlPad.joinButton.setEnabled(true);
                userControlPad.cancelButton.setEnabled(false);
                firPad.statusText.setText("请创建或加入游戏!");
            }
            isParticipant = isCreator = false;
        }
    }

    @Override
	public void keyPressed(KeyEvent e) {
        TextField inputwords = (TextField) e.getSource();
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	// 处理回车按键事件
            if (userInputPad.userChoice.getSelectedItem().equals("所有用户")) {
            	// 给所有人发信息
                try {
                    // 发送信息
                    outputStream.writeUTF(inputwords.getText());
                    inputwords.setText("");
                } catch (Exception ea) {
                    userChatPad.chatTextArea
                            .setText("不能连接到服务器!\n");
                    userListPad.userList.removeAll();
                    userInputPad.userChoice.removeAll();
                    inputwords.setText("");
                    userControlPad.connectButton.setEnabled(true);
                }
            } else {
            	// 给指定人发信息
                try {
                    outputStream.writeUTF("/" + userInputPad.userChoice.getSelectedItem()
                            + " " + inputwords.getText());
                    inputwords.setText("");
                } catch (Exception ea) {
                    userChatPad.chatTextArea
                            .setText("不能连接到服务器!\n");
                    userListPad.userList.removeAll();
                    userInputPad.userChoice.removeAll();
                    inputwords.setText("");
                    userControlPad.connectButton.setEnabled(true);
                }
            }
        }
    }

    @Override
	public void keyTyped(KeyEvent e) {
    }

    @Override
	public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        FIRClient chessClient = new FIRClient();
    }
}
