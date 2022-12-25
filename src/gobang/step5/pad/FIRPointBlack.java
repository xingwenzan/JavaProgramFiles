package gobang.step5.pad;


import java.awt.*;

/**
 * 黑棋所属的棋盘
 */
public class FIRPointBlack extends Canvas {

    @Override
    public void paint(Graphics g) { // 画棋子
        g.setColor(Color.black);
        g.fillOval(0, 0, 14, 14);
    }
}
