package comm;

import java.net.*;
import java.nio.charset.StandardCharsets;

import json_conversion.Conversion;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.Game;
import json_parse.Parse;
import linkedlist.LinkedList;

import java.io.*;

/**
 * Administrates communication to and from server.
 *
 * @author Daniel Sing
 *
 */
public class Client extends Thread{
    private Socket socket;
    private Game game;
    private LinkedList pMouse;
    final private int rows = 9;
    final private int columns = 9;
    private final int initialValue = 0;
    public int i = 0;

    /**
     * Receives as parameters the address and port to locate server.
     * and creates new socket
     *
     * @param serverAddress
     * @param serverPort
     * @throws Exception
     */
    private Client(String serverAddress, int serverPort)throws Exception{
        this.socket = new Socket(serverAddress, serverPort);
        this.game = new Game(this.rows, this.columns, this.initialValue);
    }

    public void start() {

    	ClientThread clthr = new ClientThread(socket);
        String name = null;
		try {
			name = game.startMenu();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		listen(clthr);
        Parse parser = new Parse();
        JSONObject nameDoc = parser.namaAsJson(name);
        Conversion conv = new Conversion();
        conv.saveJsonFile(nameDoc, "name.json");
        try {
        	i = 1;
        	clthr.sendFirstJson(nameDoc);
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    

    private void listen(ClientThread clthr) {
		while(true) {
			if(i == 1) {
				i=0;
				JSONObject obj = null;
				try {
					obj = clthr.receiveFirstJson();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(obj.toString());
			}
			
			
		}
		
	}

	public static void main(String[] args) throws Exception {
        Client client = new Client("192.168.100.24", 4444);
        client.start();
     }

}
