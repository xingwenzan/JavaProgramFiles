package BackgammonProgram;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static java.awt.Color.orange;

public class DisplayWindow extends JFrame{
    Button startButton = new Button("start");// 开始按钮
    Button regretButton = new Button("regret");// 悔棋按钮
    JFrame jFrame = new JFrame();// 创建窗口
    JPanel jPanel = new JPanel();// 创建面板
    ChessBoard chessBoard = new ChessBoard();
    static {
        for(int i=0;i<ChessBoard.rows;i++) {//对board[][]赋初值
            for (int j = 0; j < ChessBoard.cols; j++) {
                Rule.board[i][j]="0";
            }
        }
    }

    public void window(){

        jFrame.setTitle("使用JPanel创建一个背景为橘色的面板");
        // 设置窗口位置及尺寸
        //jFrame.setBounds(300,100,600,600);
        jFrame.setSize(600, 600);// 设置窗体大小
        jFrame.setLocationRelativeTo(null);// 设置窗体打开处于屏幕中央
        // 设置单击“关闭”按钮时退出程序
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        JPanel jPanel = new JPanel(){// 一种将棋盘画到图上的方法
//            public void paint(Graphics g) {
//                for(int i = 0; i<= rows; i++) {//画横线
//                g.drawLine(margin, margin +i* gridSpan, margin + cols * gridSpan, margin +i* gridSpan);
//            }
//                for(int j = 0; j<= cols; j++) {//画竖线
//                    g.drawLine(margin +j* gridSpan, margin, margin +j* gridSpan, margin + rows * gridSpan);
//                }}
//        };
        jPanel.setBackground(orange);// 设置面板背景为橘色
        // 将面板添加到jFrame
        jFrame.add(jPanel);
        // 设置窗口是否可见，如果设置false或不设置那么窗口将不可见
        jFrame.setVisible(true);


        // 设置布局,流式布局
        jPanel.setLayout(new FlowLayout());
        // 添加按钮
        jPanel.add(startButton);
        jPanel.add(regretButton);
        // 事件源注册监视器，为按钮添加事件
        ModuleFunction moduleFunction = new ModuleFunction();
        startButton.addActionListener(moduleFunction);
        regretButton.addActionListener(moduleFunction);

        // 画棋盘
        chessBoard.paintChessBoard(jPanel.getGraphics());

        // 画棋子
        jPanel.addMouseListener(moduleFunction);
        //chessBoard.paintChess(g);
        //jPanel.repaint();//重画图型
//        JLabel lb = new JLabel("此处显示鼠标右键点击后的坐标");   //  创建一个 Label对象
//        jPanel.add(lb); // 添加标签到窗口上
//        jPanel.addMouseListener(new MouseListener() {   //为窗口添加鼠标事件监听器
//            @Override
//            public void mousePressed(MouseEvent e) {
//                // TODO Auto-generated method stub
//                if(e.getButton() == MouseEvent.BUTTON3){    // 判断获取的按钮是否为鼠标的右击
//                    lb.setText(e.getX()+","+e.getY());     // 获得鼠标点击位置的坐标并发送到标签的文字上
//                }//else if (e.getButton() == MouseEvent.BUTTON1){
//                    //chessBoard.paintChess(g);
//                //}
//            }
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                // TODO Auto-generated method stub
//
//            }
//
//        });
        //pack();// 自适应

    }


    public class ModuleFunction implements ActionListener,MouseListener {// 事件类
        Rule rule = new Rule();
        JLabel lb = new JLabel("此处显示鼠标右键点击后的坐标");   //  创建一个 Label对象
        static boolean chessColor = Chess.chessColor;
        static boolean gameOver = false;

        @Override
        public void actionPerformed(@NotNull ActionEvent e) {// 按钮事件类
            Object obj = e.getSource();//获取事件源
            if (obj == startButton) {//事件源是重新开始按钮
                System.out.println("重新开始");
//                jPanel.repaint();
                Graphics g = jPanel.getGraphics();
                g.setColor(orange);
                g.fillRect(0,0,600,600);// 画面填充
//                g.clearRect(0,0,600,600);
//                jPanel.getGraphics().clearRect(0,0,600,600);// 画面清除
                rule.restart();
                chessBoard.paintChessBoard(jPanel.getGraphics());
//                jFrame.repaint();// 重画
//                repaint();
//                chessBoard.paintChessBoard(jPanel.getGraphics());
            } else if (obj == regretButton) {//事件源是悔棋按钮
                System.out.println("悔棋！");
                Graphics g = jPanel.getGraphics();
                g.setColor(orange);
                g.fillRect(0,0,600,600);// 画面填充
                rule.regret();
                chessBoard.paintChessBoard(jPanel.getGraphics());
                chessBoard.paintChess(jPanel.getGraphics());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            jPanel.add(lb); // 添加标签到窗口上
            if(e.getButton() == MouseEvent.BUTTON1){    // 判断获取的按钮是否为鼠标的右击
                lb.setText((e.getX()-ChessBoard.margin)/ChessBoard.gridSpan+","+(e.getY()-ChessBoard.margin)/ChessBoard.gridSpan+",左键");     // 获得鼠标点击位置的坐标并发送到标签的文字上
            }else if(e.getButton() == MouseEvent.BUTTON3){    // 判断获取的按钮是否为鼠标的左击
                lb.setText(e.getX()+","+e.getY()+",右键");     // 获得鼠标点击位置的坐标并发送到标签的文字上
            }else if(e.getButton() == MouseEvent.BUTTON2){    // 判断获取的按钮是否为鼠标的中击
                lb.setText(e.getX()+","+e.getY()+",按滚轮");     // 获得鼠标点击位置的坐标并发送到标签的文字上
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1){    // 判断获取的按钮是否为鼠标的左击
                if(gameOver)//游戏结束，不能按
                    return ;

                Graphics graphics =jPanel.getGraphics();
                String colorName=chessColor?"黑棋":"白棋";//判断是什么颜色的棋子
                int chessX = (e.getX()-ChessBoard.margin)/ChessBoard.gridSpan;
                int chessY = (e.getY()-ChessBoard.margin)/ChessBoard.gridSpan;
                if (chessY>=15 || chessX>=15){//棋盘外，不能按
                    return;
                }
                if (Chess.checkChess(chessX,chessY)){//有棋子，不能按
                    System.out.println("这里有棋子");
                    return;
                }
                Rule.board[chessX][chessY] = colorName;
                System.out.println(Rule.board[chessX][chessY]);

                Chess chess =new Chess(chessX,chessY,chessColor?Color.black:Color.white);//对棋子对象进行初始化
                ChessBoard.chessList[ChessBoard.chessCount++] = chess;//将棋子对象添加到棋子数组中
//                chessBoard.paintChess(graphics);

                Graphics g = jPanel.getGraphics();
                g.setColor(orange);
                g.fillRect(0,0,600,600);// 画面填充
                chessBoard.paintChessBoard(jPanel.getGraphics());
                chessBoard.paintChess(jPanel.getGraphics());


                if(Rule.win(chessX,chessY,chessColor)) {//判断是否胜利
                    String msg=String.format("恭喜 %s赢了",colorName);
                    //JOptionPane.showMessageDialog(jFrame.getComponent(1), msg);
                    System.out.println(msg);
                    gameOver=true;
                }else if(ChessBoard.chessCount==(ChessBoard.cols+1)*(ChessBoard.rows+1)) {//判断是否全部下满
                    String msg=String.format("恭喜 %s赢了",colorName);
//                    JOptionPane.showMessageDialog(jPanel.getComponent(0), msg);
                    JOptionPane.showMessageDialog(jFrame.getComponent(0), msg);
                    System.out.println(msg);
                    gameOver=true;
                }
                chessColor = !chessColor;
            }
            jPanel.add(lb); // 添加标签到窗口上
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

    }
}