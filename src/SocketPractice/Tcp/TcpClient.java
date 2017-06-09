package SocketPractice.Tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by dy on 2017/5/27.
 */
public class TcpClient {

    private static int port = 9999;
    private static String serverIp = "192.168.6.94";
    private static int timeout = 30*1000;
    private static String endSignal = "88";

    public static void main(String[] args) throws IOException {
        System.out.println("请求连接服务端!");
        System.out.println(InetAddress.getLocalHost().getHostAddress());
        Socket client = new Socket(serverIp, port);
        client.setSoTimeout(timeout);
        //获取键盘输入
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        //获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        while(flag){
            System.out.print("输入信息：");
            String str = input.readLine();
            //发送数据到服务端
            out.println(str);
            System.out.println("等待回复消息");
            if(endSignal.equals(str)){
                flag = false;
            }else{
                try{
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                    String echo = buf.readLine();
                    System.out.println(echo);
                    if (echo.equals(endSignal)) {
                        out.print(endSignal);
                        break;
                    }
                }catch(SocketTimeoutException e){
                    System.out.println("Time out, No response");
                }
            }
        }
        input.close();
        if(client != null){
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭
        }
    }
}
