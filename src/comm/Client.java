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
<<<<<<< HEAD
        ClientThread clthr = new ClientThread(socket);
=======
<<<<<<< HEAD
        String name = null;
        try {
            name = game.startMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
=======
    	ClientThread clithr = new ClientThread(socket);
>>>>>>> branch 'master' of https://github.com/Josecespedesant/DotsClient.git
        String name = game.startMenu();
>>>>>>> origin/master
        Parse parser = new Parse();
        JSONObject nameDoc = parser.namaAsJson(name);
        Conversion conv = new Conversion();
        conv.saveJsonFile(nameDoc, "name.json");
        try {
			clthr.sendFirstJson(nameDoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    

    public static void main(String[] args) throws Exception {
        Client client = new Client("192.168.100.24", 4444);
        client.start();
     }

}
