package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String args[]) throws IOException {

        ServerSocket s1 = new ServerSocket(69);
        Socket ss = s1.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(ss.getInputStream()));
        while(true) {
            Object s = br.readLine();
            if(s.equals("exit")==true) {
                System.out.println("CHAO");
                System.exit(1);
            }
            System.out.println("mensaje cliente " + s);
        }

    }

}