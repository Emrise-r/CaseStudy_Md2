package Server;

import java.io.*;
import java.net.*;

public class SimpleServerProgram {
    public static void main(String[] args) {
        ServerSocket listener = null;
        String line;
        BufferedReader bis;
        BufferedWriter bos;
        Socket socketServer = null;

        try {
            //Mo port 1024
            listener = new ServerSocket(1024);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        try {
            //Chap nhan ket noi tu cilent va nhan obj socket tu server
            System.out.println("Server is Online");
            socketServer = listener.accept();
            System.out.println("client has accepted!");

            //Mo luong in/output
            bis = new BufferedReader(new InputStreamReader(socketServer.getInputStream()));
            bos = new BufferedWriter(new OutputStreamWriter(socketServer.getOutputStream()));

            //Nhan du lieu tu client va gui tra loi
            while (true) {
                //doc du lieu tu client
                line = bis.readLine();
                //Ghi vao Socket tai Server
                bos.write("client " + line);
                //Ket thuc dong nhap du lieu
                bos.newLine();
                //Day du lieu di
                bos.flush();

                //Dung _Quit de ket thuc tro chuyen
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
        System.out.println("Server Out");
    }
}
