package comm;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json_conversion.Conversion;
import json_parse.Parse;
import linkedlist.LinkedList;
import people.Player;
import matrix.Matrix;
 
/** 
 * This thread is responsible to handle client connection.
 * @author www.codejava.net
 */

public class ClientThread extends Thread {
 //Attribute of the class ServerThread
    private Socket socket;
    
    /**
     * Constructor of the ServerThread class that receives the socket.
     * @param socket
     */
    public ClientThread(Socket socket) {
        this.socket = socket;
    }
     
    /**
     * Keep the server running.
     */
    @SuppressWarnings("unchecked")
    public void send(JSONObject newJson) {
     
     JSONObject obj = newJson;
     	
        try {
         //Aqu� hay que modificar esto para que la condicion de imprimir el objeto json no sea escribir en la consola.
            PrintStream ps = new PrintStream(socket.getOutputStream());
            ps.println("Server: " + obj);
 
           // socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
    public void received() throws IOException, ParseException {
    	BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	try{
    		String JsonString = brs.readLine();
    		JSONParser parserS = new JSONParser();
    		JSONObject json = (JSONObject) parserS.parse(new InputStreamReader(new FileInputStream("matrixAsJson.json")));
    		System.out.println(JsonString);
   
    		Parse parserM = new Parse();
    		LinkedList list = parserM.JsonToGameState(json);
    		
    		
    		Player player1 = (Player) list.getHead().getNext().getData();
    		Player player2 = (Player) list.getHead().getNext().getNext().getData();
    		Matrix matrix = (Matrix) list.getHead().getData();
    		
    		
    		LinkedList pos = (LinkedList) list.getHead().getNext().getNext().getNext().getData();
    		
    		
    		JSONObject newJson = parserM.gameStateToJson(matrix, player1, player2, pos);
    		Conversion conv = new Conversion();
    		conv.saveJsonFile(newJson, "gameState.json");
    		
    		
    		matrix.printMatrix();
    		
    		this.send(newJson);
    	} 
    	catch (UnknownHostException ex) {
    		System.out.println("Server not found: " + ex.getMessage());
    	} 
    	catch (IOException ex) {
    		System.out.println("I/O error: " + ex.getMessage());
    	}
    	
    }

   }