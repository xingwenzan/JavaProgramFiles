package BackgammonProgram;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class Rule {
    static String[][] board=new String[ChessBoard.rows][ChessBoard.cols];//声明一个字符串数组，用来判断输赢

    public static boolean win(int x,int y,boolean chessColor) {//输赢判断
        String turn=chessColor?"黑棋":"白棋";
        if(isWinCol(x,y,turn) || isWinRow(x,y,turn) || isWinLeftObl(x,y,turn) || isWinRightObl(x,y,turn))
            return true;
        return false;
    }
    public static boolean isWinCol(int x,int y,String turn){// 落子之后首先先判断在当前行中是否可以获胜
        int minCol = y - 4;
        int maxCol = y + 4;
        //当落子的左边或右边到达边界时，置为0或14
        if(minCol < 0)
            minCol = 0;
        if (maxCol >= 15)
            maxCol = 14;

        //遍历九个格子若有五个相同颜色的棋子连线则为胜
        int sum = 0;
        for(int i = minCol ; i <= maxCol ; i++){
            String piece = board[x][i];
            if(Objects.equals(piece, turn)){
                sum ++;
                if(sum ==5)  //五子连线判断 获胜
                    return true;
            }else{
                sum = 0;   //若不为同颜色 将sum置为0
            }
        }
        return false;
    }

    public static boolean isWinRow(int x,int y,String turn){// 判断在当前列中是否可以获胜
        int minRow = x - 4;
        int maxRow = x + 4;
        if(minRow < 0)
            minRow = 0;
        if(maxRow >= 15)
            maxRow = 14;

        int sum = 0;
        for (int i = minRow ; i <= maxRow ; i++){
            String piece = board[i][y];
            if(Objects.equals(piece, turn)){
                sum ++;
                if (sum == 5)
                    return true;
            }else{
                sum = 0;
            }
        }
        return false;
    }

    public static boolean isWinLeftObl(int x,int y,String turn){// 判断左斜（/）是否获胜
        int sum = 0;
        for(int i = -4 ; i <= 4 ; i++){
            if(x-i>0 && x-i<14 && y+i>0 && y+i<14){ //越界判断
                if(Objects.equals(board[x - i][y + i], turn)){
                    sum ++;
                }else{
                    sum = 0;
                }
            }
            if(sum == 5)
                return true;
        }
        return false;
    }

    public static boolean isWinRightObl(int x,int y,String turn){// 判断右斜（\）是否获胜
        int sum = 0;
        for(int i = -4 ; i <= 4 ; i++){
            if(x+i>0 && x+i<14 && y+i>0 && y+i<14){  //越界判断
                if(Objects.equals(board[x + i][y + i], turn)){
                    sum ++;
                }else{
                    sum = 0;
                }
             }
            if(sum == 5)
                return true;
        }
        return false;
    }

    public void restart() {// 开始功能
        //设置为初始状态
        Arrays.fill(ChessBoard.chessList, null);
        for(int i=0;i<ChessBoard.rows;i++) {//对board[][]赋初值
            for (int j = 0; j < ChessBoard.cols; j++) {
                Rule.board[i][j]="0";
            }
        }
        DisplayWindow.ModuleFunction.chessColor=true;
        DisplayWindow.ModuleFunction.gameOver=false;
        ChessBoard.chessCount=0;
        System.out.println("实现开始方法");
    }

    public void regret() {// 悔棋功能
        if(ChessBoard.chessCount==0) {
            return ;
        }
        int xindex = ChessBoard.chessList[ChessBoard.chessCount-1].getX();
        int yindex = ChessBoard.chessList[ChessBoard.chessCount-1].getY();
        board[xindex][yindex]="0";
        ChessBoard.chessList[ChessBoard.chessCount-1]=null;
        ChessBoard.chessCount--;
//        if(ChessBoard.chessCount>0) {
//            xindex=chessList[chessCount-1].getX();
//            yindex=chessList[chessCount-1].getY();
//        }
        DisplayWindow.ModuleFunction.chessColor = !DisplayWindow.ModuleFunction.chessColor;
        System.out.println("实现悔棋方法");

    }

}
