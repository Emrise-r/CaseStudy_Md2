package Client;


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client3 {
    static int port = 5000;
    static InetAddress address;
    static DatagramPacket packet;
    static DatagramSocket socket;

    public static void main(String [] args) throws UnknownHostException, SocketException, IOException
    {
        while(true)
        {
            Scanner in = new Scanner(System.in);
            System.out.print ("Client: ");
            String str = in.nextLine(); //System.out.println();

            address = InetAddress.getByName("224.2.2.2");
            socket = new DatagramSocket();

            packet = new DatagramPacket(str.getBytes(),str.length(),address,port);
            socket.send(packet);

            //nhan chuoi tu server gui ve//
            byte [] temp = new byte[1000];
            DatagramPacket packet1 = new DatagramPacket(temp,temp.length);
            socket.receive(packet1);
            String str1 = new String (packet1.getData());
            System.out.println("Server: "+str1);

        }

    }
}