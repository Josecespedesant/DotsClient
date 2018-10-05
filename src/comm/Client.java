package comm;

import java.net.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.Game;
import json_parse.Parse;
import linkedlist.LinkedList;
import matrix.Matrix;
import people.Player;
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
	private Game game;
	private LinkedList pMouse;

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

    
    
    public void start() throws IOException, ParseException {
    	Conversion conv = new Conversion();
        JSONParser parserS = new JSONParser();
        ClientThread clienTh = new ClientThread(socket);

    	JSONObject obj = (JSONObject) parserS.parse(new InputStreamReader(new FileInputStream("matrixAsJson.json")));
    	DataOutputStream wr = new DataOutputStream(socket.getOutputStream());
    	wr.write(obj.toString().getBytes());
    	
    	
    	//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //PrintStream ps = new PrintStream(socket.getOutputStream());
        BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String text;
        try{
        	 do {
        		 
        		 String time = brs.readLine();
        		 System.out.println(time);
        		// text = br.readLine();
        		 //System.out.println(text+"kk");
        		// ps.println(time);
        		 
                 Parse parserM = new Parse();

                 //LinkedList list = parserM.JsonToGameState(obj);               
                 
                 //Matrix matrix = (Matrix) list.getHead().getData();
                 //matrix.printMatrix();
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
     * Reads incoming file and attempts to read it and form a JSONObject instance.
     *
     * @throws IOException
     * @throws ParseException
     */
    private void receiveJson() throws IOException, ParseException{
    	 BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	  try{
            String JsonString = brs.readLine();
            System.out.println(JsonString);
            JSONParser parserS = new JSONParser();
            JSONObject json = (JSONObject) parserS.parse(new InputStreamReader(new FileInputStream("matrixAsJson.json"))); //de String a Json
            System.out.println(JsonString);
            Parse parserM = new Parse();
            
              JSONObject obj = game.getGameState(pMouse);
              Parse parser = new Parse();
//              LinkedList list = parser.JsonToGameState(obj);
//              Matrix matrix = (Matrix) list.getHead().getData();
//              matrix.printMatrix();
//            
      		LinkedList list = parserM.JsonToGameState(obj);
      		game.updateGameState(list);
            
    	  } 
    	  catch (UnknownHostException ex) {
    	   System.out.println("Server not found: " + ex.getMessage());
    	  } 
    	  catch (IOException ex) {
    	   System.out.println("I/O error: " + ex.getMessage());
    	  }
    	  this.sendJson();
    }

    /**
     * Sends .json file to the server.
     * @throws ParseException 
     * @throws IOException 
     */
    private void sendJson() throws IOException, ParseException {
    	
        JSONObject obj = game.getGameState(pMouse);

          try {
           PrintStream ps = new PrintStream(socket.getOutputStream());
           ps.println("Client: " + obj);
           // socket.close();
          } catch (IOException ex) {
           System.out.println("Server exception: " + ex.getMessage());
           ex.printStackTrace();
          }
          this.receiveJson();
    	}
    
    public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public LinkedList getpMouse() {
		return pMouse;
	}

	public void setpMouse(LinkedList pMouse) {
		this.pMouse = pMouse;
	}

	
    /**
     * Initializes client
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      	Client cliente = new Client("127.0.0.1", 4444);
      	
      	Player p1 = new Player("Ana");
      	Player p2 = new Player("Pedro");
      	Game game = new Game(p1, p2, 9,9,0);
      	LinkedList<Integer> pos = new LinkedList<Integer>();
      	pos.append(4);
      	pos.append(3);
      	cliente.setGame(game);
      	cliente.setpMouse(pos);
      	cliente.sendJson();
      }
}