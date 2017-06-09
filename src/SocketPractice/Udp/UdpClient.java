package SocketPractice.Udp;

import java.net.*;

/**
 * Created by dy on 2017/5/28.
 */
public class UdpClient {

    public static void main(String[] args) {
        try{
            String send = "hello world!";
            InetAddress inetAddress = InetAddress.getLocalHost();
            DatagramSocket datagramSocket =  new DatagramSocket(3000);
            DatagramPacket dpSend = new DatagramPacket(send.getBytes(), send.length(), inetAddress, 9000);
            datagramSocket.send(dpSend);
            datagramSocket.close();
        } catch (Exception e) {

        }
    }
}
