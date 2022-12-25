package gobang.djr.chess.pad;

import java.io.IOException;
import java.util.StringTokenizer;

public class FIRThread extends Thread {
	// 当前线程的棋盘
    FIRPad currPad;

    public FIRThread(FIRPad currPad) {
        this.currPad = currPad;
    }


	/**
	 * 处理取得的信息
	 * @param msgReceived
	 */
    public void dealWithMsg(String msgReceived) {
		// 收到的信息为下棋
        if (msgReceived.startsWith("/chess ")) {
            StringTokenizer userMsgToken = new StringTokenizer(msgReceived, " ");
            // 表示棋子信息的数组、0索引为：x坐标；1索引位：y坐标；2索引位：棋子颜色
            String[] chessInfo = {"-1", "-1", "0"};
			// 标志位
            int i = 0;
            String chessInfoToken;
            while (userMsgToken.hasMoreTokens()) {
                chessInfoToken = (String) userMsgToken.nextToken(" ");
                if (i >= 1 && i <= 3) {
                    chessInfo[i - 1] = chessInfoToken;
                }
                i++;
            }
            currPad.paintNetFirPoint(Integer.parseInt(chessInfo[0]), Integer
                    .parseInt(chessInfo[1]), Integer.parseInt(chessInfo[2]));
			// 收到的信息为改名
        } else if (msgReceived.startsWith("/yourname ")) {
            currPad.chessSelfName = msgReceived.substring(10);
			// 收到的为错误信息
        } else if (msgReceived.equals("/error")) {
            currPad.statusText.setText("用户不存在，请重新加入!");
        }
    }


	/**
	 * 发送信息
	 * @param sndMessage
	 */
    public void sendMessage(String sndMessage) {
        try {
            currPad.outputData.writeUTF(sndMessage);
        } catch (Exception ea) {
            ea.printStackTrace();
            ;
        }
    }

    @Override
	public void run() {
        String msgReceived = "";
        try {
			// 等待信息输入
            while (true) {
                msgReceived = currPad.inputData.readUTF();
                dealWithMsg(msgReceived);
            }
        } catch (IOException es) {
        }
    }
}
