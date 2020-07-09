package Server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerThread extends Thread {
    private int clientServer;
    private Socket socket;
//    Scanner scan = new Scanner(System.in);

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
//            BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bos = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader win = new BufferedReader(new InputStreamReader(System.in));

            ServerReaderThread srt = new ServerReaderThread(socket);
            Thread thread2 = new Thread(srt);
            thread2.start();

            String m = "1";
            while (true) {

                m = win.readLine();
                bos.write(m);
                bos.newLine();
                bos.flush();

            }
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
