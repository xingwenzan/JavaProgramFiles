package gobang.step3.pad;


import java.awt.*;

/**
 * 黑棋所属的棋盘
 */
public class FIRPointBlack extends Canvas {


    /**
     * 画棋子
     * @param g
     */
    @Override
	public void paint(Graphics g) {
        // 设置棋子颜色
        g.setColor(Color.black);
        // 设置棋子大小
        g.fillOval(0, 0, 14, 14);
    }
}
