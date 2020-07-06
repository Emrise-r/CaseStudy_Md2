package Server;

import java.io.*;
import java.net.*;

public class ServerProgram {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        int clientServer = 0;

        try {
            serverSocket = new ServerSocket(1205);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            while (true) {
                System.out.println("Server is online");
                Socket socket = serverSocket.accept();
                System.out.println("Client has accepted!");
                new ServerThread(socket,clientServer++).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
