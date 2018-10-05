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
        String name = game.startMenu();
        Parse parser = new Parse();
        JSONObject nameDoc = parser.namaAsJson(name);
        Conversion conv = new Conversion();
        conv.saveJsonFile(nameDoc, "name.json");
        try {
            sendFirstJson(nameDoc);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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

    public void sendFirstJson(JSONObject json) throws IOException {
        try(OutputStreamWriter out = new OutputStreamWriter(this.socket.getOutputStream(), StandardCharsets.UTF_8)){
            out.write(json.toJSONString());
        }
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

    public static void main(String[] args) throws Exception {
        Client client = new Client("192.168.100.24", 4444);
        client.start();
    }

}
