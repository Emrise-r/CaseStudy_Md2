package Server;

import java.io.*;

import java.net.Socket;

public class ServerReaderThread implements Runnable {
    Socket serverSocket;
    private BufferedReader bis;
    private BufferedWriter bos;

    public ServerReaderThread(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                bis = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                bos = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
                String line;
                if ((line = bis.readLine()) != null) {
                    System.out.println("Client: " + line);
                    bos.write("server: " + line);
                    bos.newLine();
                    bos.flush();
//                    if (line.equals("_Quit")) {
//                        bos.write("Conversation End");
//                        bos.newLine();
//                        bos.flush();
//                        break;
//                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
