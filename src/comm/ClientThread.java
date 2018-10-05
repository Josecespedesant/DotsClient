package comm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import json_parse.Parse;
import linkedlist.LinkedList;

public class ClientThread extends Thread{
	private Socket socket;
	
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	
	public void sendFirstJson(JSONObject json) throws IOException {
        try(OutputStreamWriter out = new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8)){
            out.write(json.toJSONString());
        }
    }
	
    public JSONObject receiveFirstJson() throws IOException, ParseException {
        BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        JSONObject obj = new JSONObject();
        while(brs.readLine() != null) {
        	try{
                String nombres = brs.readLine();
                JSONParser parser1 = new JSONParser();
                JSONObject jsonConNombres = (JSONObject) parser1.parse(nombres);
                obj = jsonConNombres;
            }catch(IOException ex){
                System.out.println("I/O error: "+ ex.getMessage());
            }
        }
        return obj;
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Reads incoming file and attempts to read it and form a JSONObject instance.
     *
     * @throws IOException
     * @throws ParseException
    
    private void receiveJson() throws IOException, ParseException{
        BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        try{
            String JsonString = brs.readLine();
            System.out.println(JsonString);
            JSONParser parserS = new JSONParser();
            JSONObject json = (JSONObject) parserS.parse(new InputStreamReader(new FileInputStream("gameState.json"))); //de String a Json
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
    **/

    /**
     * Sends .json file to the server.
     * @throws ParseException
     * @throws IOException
     
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
	
    */
}
