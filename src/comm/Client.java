package comm;

import java.net.*;

import org.json.simple.JSONObject;
import json_parse.Parse;
import linkedlist.LinkedList;
import matrix.Matrix;
import json_conversion.Conversion;

import java.io.*;

/**
 * Declaración de la clase Cliente, grafica el estado actual del juego
 * @author Daniel Sing
 *
 */

public class Client {
	//Atributo de la clase cliente
	private Socket socket;
	
	/**
	 * Constructor de la clase Client que recibe como parámetros la dirección y el puerto en el que esta hosteado el servidor
	 * y crea un nuevo socket con esos parámetros
	 * @param serverAddress
	 * @param serverPort
	 * @throws Exception
	 */
    private Client(String serverAddress, int serverPort)throws Exception{
    	this.socket = new Socket(serverAddress, serverPort);
    }
    
    /**
     * Permite interactuar con el servidor
     * @throws IOException
     */
    private void start() throws IOException{
    	
    	Conversion conv = new Conversion();
    	Parse parser = new Parse();

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
     * Método main que inicia el cliente
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      	Client cliente = new Client("172.18.78.21", 4444);
      	cliente.start();
      }
}