package gobang.step6.client;


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
        System.out.println("客户端收到的消息: "+msgReceived);
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
