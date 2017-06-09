package SocketPractice.Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by dy on 2017/5/27.
 */
public class TcpServerThread extends Thread{

    private Socket client;
    private String endSignal = "88";
    private String clientName;

    public TcpServerThread(Socket socket, String endSignal, String clientName) {
        this.client = socket;
        this.endSignal = endSignal;
        this.clientName = clientName;
    }

    @Override
    public void run() {
        try {
            BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
            PrintStream outStream = new PrintStream(client.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                String inString = bufferedReader.readLine();
                if (inString == null || inString.isEmpty()) {
                    continue;
                }
                if (inString.equals(endSignal)) {
                    System.out.println(clientName + "收到结束信号");
                    outStream.print(endSignal);
                    break;
                }
                System.out.println(clientName + "收到消息：" + inString);
                System.out.println("请回复消息:");
                String outString = inReader.readLine();
                outStream.println(outString);
                System.out.println("等待回复消息");
            }
            if (client != null) {
                client.close();
            }
            outStream.close();
        } catch (IOException e) {
            System.out.println("客户端" + clientName + "已停止!");
        }
    }
}
