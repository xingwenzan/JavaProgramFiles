package gobang.djr.chess.pad;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * FIRPad
 */
public class FIRPad extends Panel implements MouseListener, ActionListener {
	/**
	 *  鼠标是否能使用
	 */
    public boolean isMouseEnabled = false;
	/**
	 *  是否在下棋中
	 */
    public boolean isWinned = false;
	/**
	 *  是否在下棋中
	 */
    public boolean isGaming = false;
	/**
	 *  棋子的x轴坐标位
	 */
    public int chessX_POS = -1;

	/**
	 *	棋子的y轴坐标位
	 */
    public int chessY_POS = -1;
	/**
	 *	棋子的颜色
	 */
    public int chessColor = 1;
	/**
	 *	黑棋x轴坐标位数组
	 */
    public int chessBlack_XPOS[] = new int[200];
	/**
	 *	黑棋y轴坐标位数组
	 */
    public int chessBlack_YPOS[] = new int[200];
	/**
	 *	白棋x轴坐标位数组
	 */
    public int chessWhite_XPOS[] = new int[200];
	/**
	 *	白棋y轴坐标位数组
	 */
    public int chessWhite_YPOS[] = new int[200];
	/**
	 *	黑棋数量
	 */
    public int chessBlackCount = 0;
	/**
	 *	白棋数量
	 */
    public int chessWhiteCount = 0;
	/**
	 *	黑棋获胜次数
	 */
    public int chessBlackVicTimes = 0;
	/**
	 *	白棋获胜次数
	 */
    public int chessWhiteVicTimes = 0;
	/**
	 *	套接口
	 */
    public Socket chessSocket;
    public DataInputStream inputData;
    public DataOutputStream outputData;
    public String chessSelfName = null;
    public String chessPeerName = null;
    public String host = null;
    public int port = 4331;
    public TextField statusText = new TextField("请连接服务器！");
    public FIRThread firThread = new FIRThread(this);

    public FIRPad() {
        setSize(440, 440);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(this);
        add(statusText);
        statusText.setBounds(new Rectangle(40, 5, 360, 24));
        statusText.setEditable(false);
    }

	/**
	 * 连接到主机
	 * @param ServerIP
	 * @param ServerPort
	 * @return
	 * @throws Exception
	 */
    public boolean connectServer(String ServerIP, int ServerPort) throws Exception {
        try {
            // 取得主机端口
            chessSocket = new Socket(ServerIP, ServerPort);
            // 取得输入流
            inputData = new DataInputStream(chessSocket.getInputStream());
            // 取得输出流
            outputData = new DataOutputStream(chessSocket.getOutputStream());
            firThread.start();
            return true;
        } catch (IOException ex) {
            statusText.setText("连接失败! \n");
        }
        return false;
    }


	/**
	 * 设定胜利时的棋盘状态
	 * @param vicChessColor
	 */
    public void setVicStatus(int vicChessColor) {
        // 清空棋盘
        this.removeAll();
        // 将黑棋的位置设置到零点
        for (int i = 0; i <= chessBlackCount; i++) {
            chessBlack_XPOS[i] = 0;
            chessBlack_YPOS[i] = 0;
        }
        // 将白棋的位置设置到零点
        for (int i = 0; i <= chessWhiteCount; i++) {
            chessWhite_XPOS[i] = 0;
            chessWhite_YPOS[i] = 0;
        }
        // 清空棋盘上的黑棋数
        chessBlackCount = 0;
        // 清空棋盘上的白棋数
        chessWhiteCount = 0;
        add(statusText);
        statusText.setBounds(40, 5, 360, 24);
        if (vicChessColor == 1) { // 黑棋胜
            chessBlackVicTimes++;
            statusText.setText("黑方胜,黑:白 " + chessBlackVicTimes + ":" + chessWhiteVicTimes
                    + ",游戏重启,等待白方...");
        } else if (vicChessColor == -1) { // 白棋胜
            chessWhiteVicTimes++;
            statusText.setText("白方胜,黑:白 " + chessBlackVicTimes + ":" + chessWhiteVicTimes
                    + ",游戏重启,等待黑方...");
        }
    }


	/**
	 * 取得指定棋子的位置
	 * @param xPos
	 * @param yPos
	 * @param chessColor
	 */
    public void setLocation(int xPos, int yPos, int chessColor) {
		// 棋子为黑棋时
        if (chessColor == 1) {
            chessBlack_XPOS[chessBlackCount] = xPos * 20;
            chessBlack_YPOS[chessBlackCount] = yPos * 20;
            chessBlackCount++;
			// 棋子为白棋时
        } else if (chessColor == -1) {
            chessWhite_XPOS[chessWhiteCount] = xPos * 20;
            chessWhite_YPOS[chessWhiteCount] = yPos * 20;
            chessWhiteCount++;
        }
    }

    /**
     * 判断当前状态是否为胜利状态
     *
     * @param xPos
     * @param yPos
     * @param chessColor
     * @return
     */
    public boolean checkVicStatus(int xPos, int yPos, int chessColor) {
        // 连接棋子数
        int chessLinkedCount = 1;
        // 用于比较是否要继续遍历一个棋子的相邻网格
        int chessLinkedCompare = 1;
        // 要比较的棋子在数组中的索引位置
        int chessToCompareIndex = 0;
        // 相邻网格的位置
        int closeGrid = 1;
        // 黑棋时
        if (chessColor == 1) {
            // 将该棋子自身算入的话，初始连接数为1
            chessLinkedCount = 1;
            //以下每对for循环语句为一组，因为下期的位置能位于中间而非两端
            // 遍历相邻4个网格
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                // 遍历棋盘上所有黑棋子
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的右边4个棋子是否都为黑棋
                    if (((xPos + closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos * 20) == chessBlack_YPOS[chessToCompareIndex])) {
                        // 连接数加1
                        chessLinkedCount = chessLinkedCount + 1;
                        // 五子相连时，胜利
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    // 若中间有一个棋子非黑棋，则会进入此分支，此时无需再遍历
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                // 遍历棋盘上所有黑棋子
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的左边4个棋子是否都为黑棋
                    if (((xPos - closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && (yPos * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            // 进入新的一组for循环时要将连接数等重置
            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的下边4个棋子是否都为黑棋
                    if ((xPos * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的上边4个棋子是否都为黑棋
                    if ((xPos * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的左上方向4个棋子是否都为黑棋
                    if (((xPos - closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的右下方向4个棋子是否都为黑棋
                    if (((xPos + closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }


            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的右上方向4个棋子是否都为黑棋
                    if (((xPos + closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessBlackCount; chessToCompareIndex++) {
                    if (((xPos - closeGrid) * 20 == chessBlack_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessBlack_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的左下方向4个棋子是否都为黑棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
        } else if (chessColor == -1) {
            // 白棋时
            chessLinkedCount = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if (((xPos + closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && (yPos * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的右边4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if (((xPos - closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && (yPos * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的左边4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if ((xPos * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的下边4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if ((xPos * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的上边4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if (((xPos - closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的左上方向4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    if (((xPos + closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        // 判断当前下的棋子的右下方向4个棋子是否都为白棋
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            chessLinkedCount = 1;
            chessLinkedCompare = 1;
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的右上方向4个棋子是否都为白棋
                    if (((xPos + closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos - closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
            for (closeGrid = 1; closeGrid <= 4; closeGrid++) {
                for (chessToCompareIndex = 0; chessToCompareIndex <= chessWhiteCount; chessToCompareIndex++) {
                    // 判断当前下的棋子的左下方向4个棋子是否都为黑棋
                    if (((xPos - closeGrid) * 20 == chessWhite_XPOS[chessToCompareIndex])
                            && ((yPos + closeGrid) * 20 == chessWhite_YPOS[chessToCompareIndex])) {
                        chessLinkedCount++;
                        if (chessLinkedCount == 5) {
                            return true;
                        }
                    }
                }
                if (chessLinkedCount == (chessLinkedCompare + 1)) {
                    chessLinkedCompare++;
                } else {
                    break;
                }
            }
        }
        return false;
    }


	/**
	 * 画棋盘
	 * @param g
	 */
    @Override
    public void paint(Graphics g) {
        // 画出横线
        for (int i = 40; i <= 400; i = i + 20) {
            g.drawLine(40, i, 400, i);
        }
        // 画出竖线
        for (int j = 40; j <= 400; j = j + 20) {
            g.drawLine(j, 40, j, 400);
        }
        g.drawLine(400, 40, 400, 400);
        g.fillOval(97, 97, 6, 6);
        g.fillOval(337, 97, 6, 6);
        g.fillOval(97, 337, 6, 6);
        g.fillOval(337, 337, 6, 6);
        g.fillOval(217, 217, 6, 6);
    }

	/**
	 * 画棋子
	 * @param xPos
	 * @param yPos
	 * @param chessColor
	 */
    public void paintFirPoint(int xPos, int yPos, int chessColor) {
        FIRPointBlack firPBlack = new FIRPointBlack(this);
        FIRPointWhite firPWhite = new FIRPointWhite(this);
		// 黑棋
        if (chessColor == 1 && isMouseEnabled) {
            // 设置棋子的位置
            setLocation(xPos, yPos, chessColor);
            // 取得当前局面状态
            isWinned = checkVicStatus(xPos, yPos, chessColor);
			// 非胜利状态
            if (isWinned == false) {
                firThread.sendMessage("/" + chessPeerName + " /chess "
                        + xPos + " " + yPos + " " + chessColor);
				// 将棋子添加到棋盘中
                this.add(firPBlack);
				// 设置棋子边界
                firPBlack.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                statusText.setText("黑(第" + chessBlackCount + "步)"
                        + xPos + " " + yPos + ",轮到白方.");
				// 将鼠标设为不可用
                isMouseEnabled = false;
            } else { // 胜利状态
                firThread.sendMessage("/" + chessPeerName + " /chess "
                        + xPos + " " + yPos + " " + chessColor);
                this.add(firPBlack);
                firPBlack.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                setVicStatus(1); // 调用胜利方法，传入参数为黑棋胜利
                isMouseEnabled = false;
            }
        } else if (chessColor == -1 && isMouseEnabled) { // 白棋
            setLocation(xPos, yPos, chessColor);
            isWinned = checkVicStatus(xPos, yPos, chessColor);
            if (isWinned == false) {
                firThread.sendMessage("/" + chessPeerName + " /chess "
                        + xPos + " " + yPos + " " + chessColor);
                this.add(firPWhite);
                firPWhite.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                statusText.setText("白(第" + chessWhiteCount + "步)"
                        + xPos + " " + yPos + ",轮到黑方.");
                isMouseEnabled = false;
            } else {
                firThread.sendMessage("/" + chessPeerName + " /chess "
                        + xPos + " " + yPos + " " + chessColor);
                this.add(firPWhite);
                firPWhite.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                setVicStatus(-1); // 调用胜利方法，传入参数为白棋
                isMouseEnabled = false;
            }
        }
    }

    // 画网络棋盘
    public void paintNetFirPoint(int xPos, int yPos, int chessColor) {
        FIRPointBlack firPBlack = new FIRPointBlack(this);
        FIRPointWhite firPWhite = new FIRPointWhite(this);
        setLocation(xPos, yPos, chessColor);
        if (chessColor == 1) {
            isWinned = checkVicStatus(xPos, yPos, chessColor);
            if (isWinned == false) {
                this.add(firPBlack);
                firPBlack.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                statusText.setText("黑(第" + chessBlackCount + "步)"
                        + xPos + " " + yPos + ",轮到白方.");
                isMouseEnabled = true;
            } else {
                firThread.sendMessage("/" + chessPeerName + " /victory "
                        + chessColor);//djr
                this.add(firPBlack);
                firPBlack.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                setVicStatus(1);
                isMouseEnabled = true;
            }
        } else if (chessColor == -1) {
            isWinned = checkVicStatus(xPos, yPos, chessColor);
            if (isWinned == false) {
                this.add(firPWhite);
                firPWhite.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                statusText.setText("白(第" + chessWhiteCount + "步)"
                        + xPos + " " + yPos + ",轮到黑方.");
                isMouseEnabled = true;
            } else {
                firThread.sendMessage("/" + chessPeerName + " /victory "
                        + chessColor);
                this.add(firPWhite);
                firPWhite.setBounds(xPos * 20 - 7,
                        yPos * 20 - 7, 16, 16);
                setVicStatus(-1);
                isMouseEnabled = true;
            }
        }
    }

	/**
	 * 捕获下棋事件
	 * @param e
	 */
	@Override
    public void mousePressed(MouseEvent e) {
        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
            chessX_POS = (int) e.getX();
            chessY_POS = (int) e.getY();
            int a = (chessX_POS + 10) / 20, b = (chessY_POS + 10) / 20;
            if (chessX_POS / 20 < 2 || chessY_POS / 20 < 2
                    || chessX_POS / 20 > 19 || chessY_POS / 20 > 19) {
                // 下棋位置不正确时，不执行任何操作
            } else {
				// 画棋子
                paintFirPoint(a, b, chessColor);
            }
        }
    }
	@Override
    public void mouseReleased(MouseEvent e) {
    }
	@Override
    public void mouseEntered(MouseEvent e) {
    }
	@Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
	public void mouseClicked(MouseEvent e) {
    }
	@Override
    public void actionPerformed(ActionEvent e) {
    }
}
