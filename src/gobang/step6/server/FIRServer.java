package gobang.step6.server;




import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 服务器界面类
 */
public class FIRServer  {

    ServerSocket serverSocket;
    public FIRServer() {

        try {
            createServer(4331);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 用指定端口和面板创建服务器
     *
     * @param port
     * @throws IOException
     */
    public void createServer(int port) {
        // 客户端套接口
        Socket clientSocket;
        // 连接到主机的客户数量
        long clientAccessNumber = 1;
        // 设定当前主机
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务器启动于:"
                    + InetAddress.getLocalHost() + ":" //djr
                    + serverSocket.getLocalPort() + "\n");


                // 监听客户端套接口的信息
                clientSocket = serverSocket.accept();
                // 建立客户端输出流
                System.out.println("已连接用户:" + clientSocket + "\n");
                System.out.println(clientSocket.getInputStream().toString());
                DataInputStream inputData = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputData = new DataOutputStream(clientSocket.getOutputStream());

                while (true) {
                    String message = inputData.readUTF();
                    System.out.println("服务端收到的消息: "+ message);
                    outputData.writeUTF("[服务端收到] " + message);
                }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String args[]) {
        FIRServer firServer = new FIRServer();
    }
}
