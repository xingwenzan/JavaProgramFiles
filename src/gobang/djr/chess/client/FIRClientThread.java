package gobang.djr.chess.client;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * FIRClientThread
 */
public class FIRClientThread extends Thread {
    public FIRClient firClient;

    public FIRClientThread(FIRClient firClient) {
        this.firClient = firClient;
    }

    public void dealWithMsg(String msgReceived) {
		// 若取得的信息为用户列表
        if (msgReceived.startsWith("/userlist ")) {
            StringTokenizer userToken = new StringTokenizer(msgReceived, " ");
            int userNumber = 0;
            // 清空客户端用户列表
            firClient.userListPad.userList.removeAll();
            // 清空客户端用户下拉框
            firClient.userInputPad.userChoice.removeAll();
            // 给客户端用户下拉框添加一个选项
            firClient.userInputPad.userChoice.addItem("所有用户");
            // 当收到的用户信息列表中存在数据时
            while (userToken.hasMoreTokens()) {
                // 取得用户信息
                String user = (String) userToken.nextToken(" ");
                // 用户信息有效时
                if (userNumber > 0 && !user.startsWith("[inchess]")) {
                    // 将用户信息添加到用户列表中
                    firClient.userListPad.userList.add(user);
					// 将用户信息添加到用户下拉框中
                    firClient.userInputPad.userChoice.addItem(user);
                }
                userNumber++;
            }
			// 下拉框默认选中所有人
            firClient.userInputPad.userChoice.setSelectedIndex(0);
			// 收到的信息为用户本名时
        } else if (msgReceived.startsWith("/yourname ")) {
			// 取得用户本名
            firClient.chessClientName = msgReceived.substring(10);
			// 设置程序Frame的标题
			firClient.setTitle("Java 五子棋客户端 " + "用户名:"
                    + firClient.chessClientName);
			// 收到的信息为拒绝用户时
        } else if (msgReceived.equals("/reject")) {
            try {
                firClient.firPad.statusText.setText("不能加入游戏!");
                firClient.userControlPad.cancelButton.setEnabled(false);
                firClient.userControlPad.joinButton.setEnabled(true);
                firClient.userControlPad.createButton.setEnabled(true);
            } catch (Exception ef) {
                firClient.userChatPad.chatTextArea
                        .setText("Cannot close!");
            }
            firClient.userControlPad.joinButton.setEnabled(true);
			// 收到信息为游戏中的等待时
        } else if (msgReceived.startsWith("/peer ")) {
            firClient.firPad.chessPeerName = msgReceived.substring(6);
			// 若用户为游戏建立者
            if (firClient.isCreator) {
				// 设定其为黑棋先行
                firClient.firPad.chessColor = 1;
                firClient.firPad.isMouseEnabled = true;
                firClient.firPad.statusText.setText("黑方下...");
				// 若用户为游戏加入者
            } else if (firClient.isParticipant) {
				// 设定其为白棋后性
                firClient.firPad.chessColor = -1;
                firClient.firPad.statusText.setText("游戏加入，等待对手.");
            }
			// 收到信息为胜利信息
        } else if (msgReceived.equals("/youwin")) {
            firClient.isOnChess = false;
            firClient.firPad.setVicStatus(firClient.firPad.chessColor);
            firClient.firPad.statusText.setText("对手退出");
            firClient.firPad.isMouseEnabled = false;
			// 收到信息为成功创建游戏
        } else if (msgReceived.equals("/OK")) {
            firClient.firPad.statusText.setText("游戏创建等待对手");
			// 收到信息错误
        } else if (msgReceived.equals("/error")) {
            firClient.userChatPad.chatTextArea.append("错误，退出程序.\n");
        } else {
            firClient.userChatPad.chatTextArea.append(msgReceived + "\n");
            firClient.userChatPad.chatTextArea.setCaretPosition(
                    firClient.userChatPad.chatTextArea.getText().length());
        }
    }

    @Override
	public void run() {
        String message = "";
        try {
            while (true) {
                // 等待聊天信息，进入wait状态
                message = firClient.inputStream.readUTF();
                dealWithMsg(message);
            }
        } catch (IOException es) {
        }
    }
}
