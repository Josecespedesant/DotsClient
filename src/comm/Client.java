package comm;

import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json_parse.Parse;
import linkedlist.LinkedList;
import matrix.Matrix;
import json_conversion.Conversion;

import java.io.*;

/**
 * Administrates communication to and from server.
 *
 * @author Daniel Sing
 *
 */

public class Client {

	private Socket socket;

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
    }

    public void start() throws IOException {
    	
    	Conversion conv = new Conversion();
    	JSONObject obj = conv.fetchJsonFile("C:\\Users\\Jose Antonio\\git\\DotClient\\matrixAsJson.json");
    	DataOutputStream wr = new DataOutputStream(socket.getOutputStream());
    	wr.write(obj.toString().getBytes());
    	
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintStream ps = new PrintStream(socket.getOutputStream());
        BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text;
        try{
        	 do {
        		 text = br.readLine();
        		 ps.println(text);
        		 String time = brs.readLine();
        		 System.out.println(time);
        		 } while (true);
        	 } 
         catch (UnknownHostException ex) {
        		 System.out.println("Server not found: " + ex.getMessage());
        		 } 
         catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
            }
    }
    /**
     * Allows interaction with server
     * @throws IOException
     * @throws ParseException
     */
    private void sendOrReceive() throws IOException, ParseException {
    	BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	String time = brs.readLine();
    	int time1 = Integer.parseInt(time);
    	if(time1 == 1) {
    		System.out.println("uno");
    		this.receiveJson();
    	}
    	else {
    		System.out.println("dos");
    		this.sendJson();
    	}
    }
    

    /**
     * Reads incoming file and attempts to read it and form a JSONObject instance.
     *
     * @throws IOException
     * @throws ParseException
     */
    private void receiveJson() throws IOException, ParseException{
    	 BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	  try{
            String JsonString = brs.readLine();
            // System.out.println(JsonString);
            JSONParser parserS = new JSONParser();
            JSONObject json = (JSONObject) parserS.parse(new InputStreamReader(new FileInputStream("matrixAsJson.json"))); //de String a Json
            System.out.println(JsonString);
            Parse parserM = new Parse();
            LinkedList list = parserM.JsonToGameState(json);
            Matrix matrix = (Matrix) list.getHead().getData();
            matrix.printMatrix();
    	  } 
    	  catch (UnknownHostException ex) {
    	   System.out.println("Server not found: " + ex.getMessage());
    	  } 
    	  catch (IOException ex) {
    	   System.out.println("I/O error: " + ex.getMessage());
    	  }
    }

    /**
     * Sends .json file to the server.
     */
    private void sendJson() {
    	Conversion conv = new Conversion();
        JSONObject obj = conv.fetchJsonFile("matrixAsJson.json");

          try {
           PrintStream ps = new PrintStream(socket.getOutputStream());
           ps.println("Client: " + obj);
           // socket.close();
          } catch (IOException ex) {
           System.out.println("Server exception: " + ex.getMessage());
           ex.printStackTrace();
          }
    	}
    
    /**
     * Initializes client
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      	Client cliente = new Client("127.0.0.1", 4444);
      	cliente.start();
      }
}