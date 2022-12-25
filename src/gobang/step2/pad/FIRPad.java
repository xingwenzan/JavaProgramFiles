package gobang.step2.pad;

import java.awt.*;


/**
 * FIRPad
 */
public class FIRPad extends Panel {

    public FIRPad() {
        setSize(440, 440);
        setLayout(null);
        // 画布背景颜色
        setBackground(Color.WHITE);
    }
	/**
	 * 画棋盘
	 * @param g
     *
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
}
