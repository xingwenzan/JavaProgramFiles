package BackgammonProgram;

import java.awt.*;

public class Chess {
    private int x;//棋子的x坐标索引
    private int y;//棋子的y坐标索引
    private Color color;//棋子颜色
    static boolean chessColor = true;
    public static int diameter = 30;//直径

    public Chess(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public static boolean checkChess(int xindex,int yindex){// 检测是否有棋子
        for (Chess c :   ChessBoard.chessList) {
            if(c!=null&&c.getX()==xindex && c.getY()==yindex)
                return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Color getColor() {
        return color;
    }
}
