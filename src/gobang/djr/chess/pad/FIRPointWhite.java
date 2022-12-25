package gobang.djr.chess.pad;

import java.awt.*;

/**
 * 白棋所属的棋盘
 */
public class FIRPointWhite extends Canvas {
	// 白棋所属的棋盘
    FIRPad padBelonged;

    public FIRPointWhite(FIRPad padBelonged) {
        setSize(20, 20);
        this.padBelonged = padBelonged;
    }

    public void paint(Graphics g) { // 画棋子
        g.setColor(Color.white);
        g.fillOval(0, 0, 14, 14);
    }
}
