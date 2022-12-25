package gobang.djr.chess.pad;

import java.awt.*;

/**
 * 黑棋所属的棋盘
 */
public class FIRPointBlack extends Canvas {
	/**
	 * 黑棋所属的棋盘
	 */
    FIRPad padBelonged;

    public FIRPointBlack(FIRPad padBelonged) {
		// 设置棋子大小
        setSize(20, 20);
        this.padBelonged = padBelonged;
    }

    @Override
	public void paint(Graphics g) { // 画棋子
        g.setColor(Color.black);
        g.fillOval(0, 0, 14, 14);
    }
}
