package gobang.step4.pad;


import java.awt.*;

/**
 * 白棋所属的棋盘
 */
public class FIRPointWhite extends Canvas {


    @Override
    public void paint(Graphics g) { // 画棋子
        g.setColor(Color.white);
        g.fillOval(0, 0, 14, 14);
    }
}
