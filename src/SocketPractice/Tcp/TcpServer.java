package SocketPractice.Tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/5/27.
 * 关闭正在使用的端口:lsof -i:9999 && kill -9 ....
 */
public class TcpServer {

    private static int port = 9999;
    private static ServerSocket serverSocket;
    private static List<Thread> list = new ArrayList<>();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(port);
            boolean result = true;
            Socket socket = null;
            String endSignal = "88";
            int i = 0;
            System.out.println("等待客户端连接");
            while (result) {
                socket = serverSocket.accept();
                System.out.println("新建客户端连接!");
                TcpServerThread tcpServerThread = new TcpServerThread(socket, endSignal, "客户端" + i++);
                tcpServerThread.start();
                list.add(tcpServerThread);
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("客户端已断开连接!");
        }

    }
}
