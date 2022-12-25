package BackgammonProgram;

public class PlayChess {
    public static void main(String[] args) {// 用来实验的 main 方法
        PlayChess pc = new PlayChess();
        pc.run();
    }

    public void run() {// 运行，为了可以在其他地方打开
        DisplayWindow dw = new DisplayWindow();
        dw.window();
    }

}