package Client;

import java.io.*;
import java.net.*;

public class SimpleClientProgramDemo {
    public static void main(String[] args) {
        //May chu hien tai
        final String serverHost = "LocalHost";

        Socket socketClient = null;
        BufferedWriter bos = null;
        BufferedReader bis = null;

        try {
            //Gui yeu cau ket noi server (serverHost) va cong port cua server
            socketClient = new Socket(serverHost,1024);

            //Ghi vao dau ra client (Gui du lieu den server)
            bos = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            //Doc du lieu gui ve client (du lieu do server gui ve
            bis = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        } catch (UnknownHostException e) {
            System.err.println("Dont't know about this Host " + serverHost);
            return;
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverHost);
            return;
        }

        //Ghi va doc du lieu vao Socket
        try {
            //Ghi vao Socket
            bos.write("I like to dance but the death go faster");
            bos.newLine();
            bos.flush();
            bos.write("I'm -3E-");
            bos.newLine();
            bos.flush();
            bos.write("_Quit");
            bos.newLine();
            bos.flush();

            //Doc vao Socket
            String reportLine;
            while ((reportLine = bis.readLine()) != null) {
                if (reportLine.contains("Conversation End")) {
                    System.out.println("Server Say: Conversation End!");
                    break;
                }
                System.out.println("Client Say: " + reportLine);
            }
            bos.close();
            bis.close();
            socketClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Trying to connect to unknown Host " + serverHost);
        } catch (IOException e) {
            System.err.println("IOException " + e);
        }
    }
}
