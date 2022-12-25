package gobang.step4.pad;


import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	 *	套接口
	 */
    public FIRPad() {
        setSize(440, 440);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(this);
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
            System.out.println("黑棋子数量为: " + chessBlackCount);
            System.out.println("黑棋子的位置: ");
            System.out.println(Arrays.toString(chessBlack_XPOS));
            // 棋子为白棋时
        } else if (chessColor == -1) {
            chessWhite_XPOS[chessWhiteCount] = xPos * 20;
            chessWhite_YPOS[chessWhiteCount] = yPos * 20;
            chessWhiteCount++;
            System.out.println("白棋子数量为: " + chessWhiteCount);
            System.out.println("白棋子的位置: ");
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
	 * 画棋子
	 * @param xPos
	 * @param yPos
	 * @param chessColor
	 */
    public void paintFirPoint(int xPos, int yPos, int chessColor) {
        // 黑棋子的画布
        FIRPointBlack firPBlack = new FIRPointBlack();
        FIRPointWhite firPWhite = new FIRPointWhite();
		// 当棋子颜色是黑色并且当前的鼠标状态是可以点击时
        if (chessColor == 1 && isMouseEnabled) {
                setLocation(xPos, yPos, chessColor);
                this.add(firPBlack);
                // 设置棋子边界 使棋子在两条线的交叉处
                // 每个棋子的大小是 14 * 14
                // 要把棋子放到两条线的中心点所以 X 和 Y 乘以网格的大小之后 需要减掉 7
                // 最后两个参数的 16 是棋子所占块的大小
                firPBlack.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);

                // 设置下一次的棋子颜色为白色
                this.chessColor = -1;
        }else{
            setLocation(xPos, yPos, chessColor);
            this.add(firPWhite);
            firPWhite.setBounds(xPos * 20 - 7, yPos * 20 - 7, 16, 16);
            // 设置下一次的棋子颜色为白色
            this.chessColor = 1;
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
