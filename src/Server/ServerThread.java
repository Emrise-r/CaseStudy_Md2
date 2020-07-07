package Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerThread extends Thread {
    private int clientServer;
    private Socket socket;
    Scanner scan = new Scanner(System.in);

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
            BufferedReader win = new BufferedReader(new InputStreamReader(System.in));

            String m = "1", line = "2";
            while (true) {

                m = win.readLine();
                bos.write(m);
                bos.newLine();
                line = bis.readLine();
                bos.flush();
                if (line != null) {
                    System.out.println("client" + clientServer + ": " + line);
                }


//                bos.newLine();
//                bos.flush();
//                System.out.println(line);
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
