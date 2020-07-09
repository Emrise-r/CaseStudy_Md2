package Server;

import java.io.*;
import java.net.*;
import java.util.*;


public class Server_v2 extends Thread{
    List<ClientOnline> connectionlist = new ArrayList<ClientOnline>();

    public List<ClientOnline> getConnectionlist() {
        return connectionlist;
    }

    public void sendToServer(String mess) {
        for (ClientOnline clientOnline : this.connectionlist) {
            clientOnline.sendMessage(mess);
        }
    }

    @Override
    public void run() {

        try{
            ServerSocket serverSocket = new ServerSocket(4321);
            while (true) {
                System.out.println("Waiting for User");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client has accepted!");
                ClientOnline clientOnline = new ClientOnline(clientSocket, this);
                connectionlist.add(clientOnline);
                System.out.println("So client online" + connectionlist.size());
                clientOnline.start();
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
