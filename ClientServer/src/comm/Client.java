package comm;

import java.net.*;
import java.io.*;

public class Client {
    private static String host;
    private static int port;

    public static void main(String[] args) {

    	//Aquí se debe ingresar la IP de la red (primer parámetro).
        try (Socket socket = new Socket("192.168.100.2", 4444)) {
 
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintStream ps = new PrintStream(socket.getOutputStream());
            BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String text;
 
            do {
                text = br.readLine();
                
                ps.println(text);
 
                String time = brs.readLine();
 
                System.out.println(time);
 
            } while (true);
 
           // socket.close();
 
        } catch (UnknownHostException ex) {
 
            System.out.println("Server not found: " + ex.getMessage());
 
        } catch (IOException ex) {
 
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}