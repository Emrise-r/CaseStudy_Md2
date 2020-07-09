package Server;

import java.io.*;
import java.net.*;
import java.util.List;

public class ClientOnline extends Thread {
    private Socket clientSocket;
    private Server_v2 server;
    BufferedWriter bos;
    BufferedReader bis;

    public ClientOnline(Socket clientSocket, Server_v2 server) {
        this.clientSocket = clientSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            bos = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            bis = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true) {

                //doc tin tu 1 client roi gui lai cho cac client dang ket noi
                String line;
                if ((line = bis.readLine()) != null) {
                    System.out.println("Client: " + line);
                   server.serverSendAll(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //gui tin den 1 client trong list
    public void sendMessage(String mess) {
        try {
            bos.write("Client: " + mess);
            bos.newLine();
            bos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
