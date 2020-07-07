package Client;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class ClientProgram2 {
    public static void main(String[] args) {
        Scanner scan2 = new Scanner(System.in);
        final String serverHost = "localhost";
         Socket socketClient = null;
         BufferedWriter bos = null;
         BufferedReader bis = null;

        try {
            socketClient = new Socket(serverHost, 1205);
            System.out.println("Successful Connection");
            bos = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            bis = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
//            bos.write("Now is: " + new Date());
//            bos.newLine();
//            bos.flush();
//            bos.write("I'm Vinh");
//            bos.newLine();
//            bos.flush();
            String m = "1";
            while (!m.equals("_Quit")) {
//                System.out.println("Moi ban nhap");
                m = scan2.nextLine();
//                System.out.println(m);
                bos.write(m);
                bos.newLine();
                bos.flush();
                String responseLine;
                if ((responseLine = bis.readLine()) != null) {
                    if (responseLine.contains("Conversation End")) {
                        break;
                    }
                    System.out.println("Client" + responseLine);
                }
            }
            bos.close();
            bis.close();
            socketClient.close();
        } catch (UnknownHostException e) {
            System.err.println("Try to connect to unknown host: " + e);
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
