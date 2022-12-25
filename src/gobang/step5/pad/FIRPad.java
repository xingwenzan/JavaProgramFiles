package gobang.step5.pad;


import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


/**
 * FIRPad
 */
public class FIRPad extends Panel implements MouseListener{

	/**
	 *  鼠标是否能使用
	 */
    public boolean isMouseEnabled = true;

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
     * 判断输赢
     */
    public boolean isWinned = false;


    /**
     * 黑棋获胜次数
     */
    public int chessBlackVicTimes = 0;
    /**
     * 白棋获胜次数
     */
    public int chessWhiteVicTimes = 0;

    public TextField statusText = new TextField("");

	/**
	 *	套接口
	 */
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
            System.out.println("黑棋子数量为: "+ chessBlackCount);
            System.out.println("黑棋子的位置: ");
            System.out.println(Arrays.toString(chessBlack_XPOS));
            System.out.println(Arrays.toString(chessBlack_YPOS));
			// 棋子为白棋时
        } else if (chessColor == -1) {
            chessWhite_XPOS[chessWhiteCount] = xPos * 20;
            chessWhite_YPOS[chessWhiteCount] = yPos * 20;
            chessWhiteCount++;
            System.out.println("白棋子数量为: "+ chessWhiteCount);
            System.out.println("白棋子的位置: ");
            System.out.println(Arrays.toString(chessBlack_XPOS));
            System.out.println(Arrays.toString(chessWhite_YPOS));
        }
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
        // 画出五个中心点
        g.fillOval(97, 97, 6, 6);
        g.fillOval(337, 97, 6, 6);
        g.fillOval(97, 337, 6, 6);
        g.fillOval(337, 337, 6, 6);
        g.fillOval(217, 217, 6, 6);
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
     * 设定胜利时的棋盘状态
     *
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

        statusText = new TextField();
        add(statusText);
        statusText.setBounds(40, 5, 360, 24);
        // 黑棋胜
        if (vicChessColor == 1) {
            chessBlackVicTimes++;
            statusText.setText("黑方胜,黑:白 " + chessBlackVicTimes + ":" + chessWhiteVicTimes
                    + ",游戏重启,等待白方...");
            // 白棋胜
        } else if (vicChessColor == -1) {
            chessWhiteVicTimes++;
            statusText.setText("白方胜,黑:白 " + chessBlackVicTimes + ":" + chessWhiteVicTimes
                    + ",游戏重启,等待黑方...");
        }
    }

    /**
	 * 画棋子
	 * @param xPos
	 * @param yPos
	 * @param chessColor
	 */
    public void paintFirPoint(int xPos, int yPos, int chessColor) {
        FIRPointBlack firPBlack = new FIRPointBlack();
        FIRPointWhite firPWhite = new FIRPointWhite();
        // 处理黑棋
        if (chessColor == 1 && isMouseEnabled) {
            setLocation(xPos, yPos, chessColor);
            isWinned = checkVicStatus(xPos, yPos, chessColor);
            if (isWinned == false) {
                this.add(firPBlack);
                // 设置棋子边界 使棋子在两条线的交叉处
                firPBlack.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);
                // 设置下一次的棋子颜色为白色
                this.chessColor = -1;
            } else { // 胜利状态
                this.add(firPBlack);
                // 设置棋子边界 使棋子在两条线的交叉处
                firPBlack.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);
                // 设置下一次的棋子颜色为白色
                this.chessColor = -1;
                // 调用胜利方法，传入参数为黑棋胜利
                setVicStatus(1);
            }
        }else{
            // 处理白棋
            setLocation(xPos, yPos, chessColor);
            isWinned = checkVicStatus(xPos, yPos, chessColor);
            if (isWinned == false) {
                this.add(firPWhite);
                // 设置棋子边界 使棋子在两条线的交叉处
                firPWhite.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);
                // 设置下一次的棋子颜色为黑色
                this.chessColor = 1;
            } else { // 胜利状态
                this.add(firPWhite);
                // 设置棋子边界 使棋子在两条线的交叉处
                firPWhite.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);
                // 设置下一次的棋子颜色为黑色
                this.chessColor = 1;
                // 调用胜利方法，传入参数为白棋胜利
                setVicStatus(-1);
            }
        }
    }

    /**
     * 捕获下棋事件
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // 当鼠标点击时
        if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
            // 获取鼠标点击的 X 坐标
            chessX_POS =  e.getX();
            // 获取鼠标点击的 Y 坐标
            chessY_POS =  e.getY();
            System.out.println("chessX_POS:");
            System.out.println(chessX_POS);
            System.out.println("chessY_POS:");
            System.out.println(chessY_POS);

            // 每个格子的大小是 20 * 20 所以需要除以 20
            // 因为鼠标点击棋盘会有一些偏移量, 为了使棋子能在两条线的交叉处, 所以我们需要对坐标系取整
            // int 会向下取整 1.95 ---> 1 , 为了使 X 和 Y 取整后坐标更精确, 我们在 X 和 Y 坐标中分别加上半个格子的大小 10
            int a = (chessX_POS + 10 ) / 20, b = (chessY_POS + 10) / 20;
            System.out.println(a);
            System.out.println(b);
            // 判断当前坐标是否越界了
            if (chessX_POS / 20 < 2 || chessY_POS / 20 < 2 || chessX_POS / 20 > 19 || chessY_POS / 20 > 19) {
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

}
