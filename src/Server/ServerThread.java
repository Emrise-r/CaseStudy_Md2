package Server;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
    private int clientServer;
    private Socket socket;

    public ServerThread(Socket socket, int clientServer) {
        this.socket = socket;
        this.clientServer = clientServer;
        log("New connection " + this.clientServer + " at " + socket);
    }

    private void log(String message) {
        System.out.println(message);
    }

    @Override
    public void run() {
        try {
            BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (true) {
                String line = bis.readLine();
                bos.write("client1" + line);
                bos.newLine();
                bos.flush();

                if (line.equals("_Quit")) {
                    bos.write("Conversation End");
                    bos.newLine();
                    bos.flush();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
