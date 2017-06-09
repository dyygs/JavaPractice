package SocketPractice.Udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by dy on 2017/5/28.
 */
public class UdpServer {

    public static void main(String[] args) {
        try{
            byte[] receive =  new byte[1024];
            DatagramSocket datagramSocket =  new DatagramSocket(9000);
            DatagramPacket dpReceive = new DatagramPacket(receive, 1024);
            datagramSocket.receive(dpReceive);
            String receiveString =  new String(dpReceive.getData(), 0, dpReceive.getLength());
            System.out.println(receiveString);
            dpReceive.setLength(1024);
            datagramSocket.close();
        } catch (Exception e) {

        }
    }
}
