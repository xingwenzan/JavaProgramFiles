package gobang.djr.chess.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class FIRServerThread extends Thread {
	// 保存客户端套接口信息
    Socket clientSocket;
	// 保存客户端端口与输出流对应的Hash
    Hashtable clientDataHash;
	// 保存客户端套接口和客户名对应的Hash
    Hashtable clientNameHash;
	// 保存游戏创建者和游戏加入者对应的Hash
    Hashtable chessPeerHash;
    ServerMsgPanel serverMsgPanel;
    boolean isClientClosed = false;

    public FIRServerThread(Socket clientSocket, Hashtable clientDataHash,
                           Hashtable clientNameHash, Hashtable chessPeerHash,
                           ServerMsgPanel server) {
        this.clientSocket = clientSocket;
        this.clientDataHash = clientDataHash;
        this.clientNameHash = clientNameHash;
        this.chessPeerHash = chessPeerHash;
        this.serverMsgPanel = server;
    }

    public void dealWithMsg(String msgReceived) {
        String clientName;
        String peerName;
        if (msgReceived.startsWith("/")) {
			// 收到的信息为更新用户列表
            if (msgReceived.equals("/list")) {
                Feedback(getUserList());
				// 收到的信息为创建游戏
            } else if (msgReceived.startsWith("/creatgame [inchess]")) {
				//取得服务器名
                String gameCreaterName = msgReceived.substring(20);
				// 将用户端口放到用户列表中
                synchronized (clientNameHash) {
                    clientNameHash.put(clientSocket, msgReceived.substring(11));
                }
				// 将主机设置为等待状态
                synchronized (chessPeerHash) {
                    chessPeerHash.put(gameCreaterName, "wait");
                }
                Feedback("/yourname " + clientNameHash.get(clientSocket));
                sendGamePeerMsg(gameCreaterName, "/OK");
                sendPublicMsg(getUserList());
				// 收到的信息为加入游戏时
            } else if (msgReceived.startsWith("/joingame ")) {
                StringTokenizer userTokens = new StringTokenizer(msgReceived, " ");
                String userToken;
                String gameCreatorName;
                String gamePaticipantName;
                String[] playerNames = {"0", "0"};
                int nameIndex = 0;
                while (userTokens.hasMoreTokens()) {
                    userToken = userTokens.nextToken(" ");
                    if (nameIndex >= 1 && nameIndex <= 2) {
						// 取得游戏者命
                        playerNames[nameIndex - 1] = userToken;
                    }
                    nameIndex++;
                }
                gameCreatorName = playerNames[0];
                gamePaticipantName = playerNames[1];
                if (chessPeerHash.containsKey(gameCreatorName)
						// 游戏已创建
                        && chessPeerHash.get(gameCreatorName).equals("wait")) {
					// 增加游戏加入者的套接口与名称的对应
                    synchronized (clientNameHash) {
                        clientNameHash.put(clientSocket,
                                ("[inchess]" + gamePaticipantName));
                    }
					// 增加或修改游戏创建者与游戏加入者的名称的对应
                    synchronized (chessPeerHash) {
                        chessPeerHash.put(gameCreatorName, gamePaticipantName);
                    }
                    sendPublicMsg(getUserList());
                    // 发送信息给游戏加入者
                    sendGamePeerMsg(gamePaticipantName,
                            ("/peer " + "[inchess]" + gameCreatorName));
                    // 发送游戏给游戏创建者
                    sendGamePeerMsg(gameCreatorName,
                            ("/peer " + "[inchess]" + gamePaticipantName));
                } else { // 若游戏未创建则拒绝加入游戏
                    sendGamePeerMsg(gamePaticipantName, "/reject");
                    try {
                        closeClient();
                    } catch (Exception ez) {
                        ez.printStackTrace();
                    }
                }
                // 收到的信息为游戏中时
            } else if (msgReceived.startsWith("/[inchess]")) {
                int firstLocation = 0, lastLocation;
                lastLocation = msgReceived.indexOf(" ", 0);
                peerName = msgReceived.substring((firstLocation + 1), lastLocation);
                msgReceived = msgReceived.substring((lastLocation + 1));
                if (sendGamePeerMsg(peerName, msgReceived)) {
                    Feedback("/error");
                }
                // 收到的信息为放弃游戏时
            } else if (msgReceived.startsWith("/giveup ")) {
                String chessClientName = msgReceived.substring(8);
                if (chessPeerHash.containsKey(chessClientName)
                        && !((String) chessPeerHash.get(chessClientName))
                        .equals("wait")) {
                    // 胜利方为游戏加入者，发送胜利信息
                    sendGamePeerMsg((String) chessPeerHash.get(chessClientName),
                            "/youwin");
                    // 删除退出游戏的用户
                    synchronized (chessPeerHash) {
                        chessPeerHash.remove(chessClientName);
                    }
                }
                // 胜利方为游戏创建者，发送胜利信息
                if (chessPeerHash.containsValue(chessClientName)) {
                    sendGamePeerMsg((String) getHashKey(chessPeerHash,
                            chessClientName), "/youwin");
                    // 删除退出游戏的用户
                    synchronized (chessPeerHash) {
                        chessPeerHash.remove((String) getHashKey(chessPeerHash,
                                chessClientName));
                    }
                }
            } else { // 收到的信息为其它信息时
                int lastLocation = msgReceived.indexOf(" ", 0);
                if (lastLocation == -1) {
                    Feedback("无效命令");
                    return;
                }
            }
        } else {
            msgReceived = clientNameHash.get(clientSocket) + ">" + msgReceived;
            serverMsgPanel.msgTextArea.append(msgReceived + "\n");
            sendPublicMsg(msgReceived);
            serverMsgPanel.msgTextArea.setCaretPosition(serverMsgPanel.msgTextArea.getText()
                    .length());
        }
    }

    // 发送公开信息
    public void sendPublicMsg(String publicMsg) {
        synchronized (clientDataHash) {
            for (Enumeration enu = clientDataHash.elements(); enu
                    .hasMoreElements(); ) {
                DataOutputStream outputData = (DataOutputStream) enu.nextElement();
                try {
                    outputData.writeUTF(publicMsg);
                } catch (IOException es) {
                    es.printStackTrace();
                }
            }
        }
    }

    // 发送信息给指定的游戏中的用户
    public boolean sendGamePeerMsg(String gamePeerTarget, String gamePeerMsg) {
        // 遍历以取得游戏中的用户的套接口
        for (Enumeration enu = clientDataHash.keys(); enu.hasMoreElements(); ) {
            Socket userClient = (Socket) enu.nextElement();
            if (gamePeerTarget.equals((String) clientNameHash.get(userClient))
                    && !gamePeerTarget.equals((String) clientNameHash
                    .get(clientSocket))) {
                // 找到要发送信息的用户时
                synchronized (clientDataHash) {
                    // 建立输出流
                    DataOutputStream peerOutData = (DataOutputStream) clientDataHash
                            .get(userClient);
                    try {
                        // 发送信息
                        peerOutData.writeUTF(gamePeerMsg);
                    } catch (IOException es) {
                        es.printStackTrace();
                    }
                }
                return false;
            }
        }
        return true;
    }

    // 发送反馈信息给连接到主机的人
    public void Feedback(String feedBackMsg) {
        synchronized (clientDataHash) {
            DataOutputStream outputData = (DataOutputStream) clientDataHash
                    .get(clientSocket);
            try {
                outputData.writeUTF(feedBackMsg);
            } catch (Exception eb) {
                eb.printStackTrace();
            }
        }
    }

    // 取得用户列表
    public String getUserList() {
        String userList = "/userlist";
        for (Enumeration enu = clientNameHash.elements(); enu.hasMoreElements(); ) {
            userList = userList + " " + (String) enu.nextElement();
        }
        return userList;
    }

    // 根据value值从Hashtable中取得相应的key
    public Object getHashKey(Hashtable targetHash, Object hashValue) {
        Object hashKey;
        for (Enumeration enu = targetHash.keys(); enu.hasMoreElements(); ) {
            hashKey = (Object) enu.nextElement();
            if (hashValue.equals((Object) targetHash.get(hashKey)))
                return hashKey;
        }
        return null;
    }

    // 刚连接到主机时执行的方法
    public void sendInitMsg() {
        sendPublicMsg(getUserList());
        Feedback("/yourname " + (String) clientNameHash.get(clientSocket));
        Feedback("Java 五子棋客户端");
        Feedback("/list --更新用户列表");
        Feedback("/<username> <talk> --私聊");
        Feedback("注意：命令必须对所有用户发送");
    }

    public void closeClient() {
        serverMsgPanel.msgTextArea.append("用户断开连接:" + clientSocket + "\n");
        synchronized (chessPeerHash) { //如果是游戏客户端主机
            if (chessPeerHash.containsKey(clientNameHash.get(clientSocket))) {
                chessPeerHash.remove((String) clientNameHash.get(clientSocket));
            }
            if (chessPeerHash.containsValue(clientNameHash.get(clientSocket))) {
                chessPeerHash.put((String) getHashKey(chessPeerHash,
                                (String) clientNameHash.get(clientSocket)),
                        "tobeclosed");
            }
        }
        // 删除客户数据
        synchronized (clientDataHash) {
            clientDataHash.remove(clientSocket);
        }
        // 删除客户数据
        synchronized (clientNameHash) {
            clientNameHash.remove(clientSocket);
        }
        sendPublicMsg(getUserList());
        serverMsgPanel.statusLabel.setText("当前连接数:" + clientDataHash.size());
        try {
            clientSocket.close();
        } catch (IOException exx) {
            exx.printStackTrace();
        }
        isClientClosed = true;
    }

    public void run() {
        DataInputStream inputData;
        synchronized (clientDataHash) {
            serverMsgPanel.statusLabel.setText("当前连接数:" + clientDataHash.size());
        }
        try {    // 等待连接到主机的信息
            inputData = new DataInputStream(clientSocket.getInputStream());
            sendInitMsg();
            while (true) {
                String message = inputData.readUTF();
                dealWithMsg(message);
            }
        } catch (IOException esx) {
        } finally {
            if (!isClientClosed) {
                closeClient();
            }
        }
    }
}
