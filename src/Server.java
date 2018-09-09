import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Server {
	
	public static void main(String args[]) throws IOException {
		int number;
		ServerSocket s1 = new ServerSocket(69);
		Socket ss = s1.accept();
		Scanner sc = new Scanner(ss.getInputStream());
		number = sc.nextInt();
		
		
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(number+" del cerber miher");
		
		sc.close();
		s1.close();
	}

}
