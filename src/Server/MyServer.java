package Server;

import java.net.*;
import java.io.*;
import java.util.Scanner;

class MyServer {
    public static void main(String args[]) throws Exception {
//        Scanner scan = new Scanner(System.in);
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String br = scan.nextLine();

        String str = "", str2 = "";
        while (!str.equals("stop")) {
            str = dis.readUTF();
            System.out.println("client says: " + str);
            str2 = br.readLine();
            dos.writeUTF(str2);
            dos.flush();
        }
        dis.close();
        s.close();
        ss.close();
    }
}