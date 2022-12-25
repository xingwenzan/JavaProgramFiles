package BackgammonProgram;

import javax.swing.*;
import java.awt.*;

public class ChessBoard extends JFrame {
    public static int margin = 30;//定义边距
    public static int rows = 15;//定义行数
    public static int cols = 15;//定义列数
    public static int gridSpan = 35;//网格间距

    public void paintChessBoard(Graphics g){// 绘制棋盘
        //super.paintComponents(g);// JFrame 的爷爷类的父类的方法 (JFrame - Frame - Window - Container)
        for(int i = 0; i<= rows; i++) {//画横线
            g.drawLine(margin, margin +i* gridSpan, margin + cols * gridSpan, margin +i* gridSpan);
        }
        for(int j = 0; j<= cols; j++) {//画竖线
            g.drawLine(margin +j* gridSpan, margin, margin +j* gridSpan, margin + rows * gridSpan);
        }
    }

    static int chessCount = 0;
    static Chess[] chessList = new Chess[(rows+1)*(cols+1)];

    public void paintChess(Graphics g){
        for(int i=0;i<chessCount;i++) {//画棋子
            int xpos=chessList[i].getX()*gridSpan+margin+gridSpan/2;//得到棋子x坐标
            int ypos=chessList[i].getY()*gridSpan+margin+gridSpan/2;//得到棋子y坐标
            g.setColor(chessList[i].getColor());//设置棋子颜色
            g.fillOval(xpos- Chess.diameter/2, ypos- Chess.diameter/2, Chess.diameter, Chess.diameter);//画棋子
            if(i==chessCount-1){
                g.setColor(Color.red);//标记最后一个棋子为红色
                g.drawRect(xpos- Chess.diameter/2, ypos- Chess.diameter/2, Chess.diameter, Chess.diameter  );
            }
        }
    }
}
