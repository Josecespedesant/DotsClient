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
    
    private void sendOrRecieved() throws IOException { //metodo miedo para probar el envio y recibimiento de datos (es momentaneo)
    	BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	String time = brs.readLine();
    	int time1 = Integer.parseInt(time);
    	if(time1 == 1) {
    		System.out.println("uno");
    		this.received();
    		}
    	else {
    		System.out.println("dos");
    		this.send();
    		}
    	}
    
    private void received() throws IOException{
    	  BufferedReader brs = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	  try{
    		  do {
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
    
    private void send() {
    	Conversion conv = new Conversion();
        Parse parser = new Parse();

        JSONObject obj = conv.fetchJsonFile("matrixAsJson.json");

        /* try (FileWriter file = new FileWriter("matrixAsJson.json")) {

             file.write(obj.toJSONString());
             file.flush();

       } catch (IOException e) {
             e.printStackTrace();
         }
*/

          try {
           PrintStream ps = new PrintStream(socket.getOutputStream());
           ps.println("Cliente: " + obj); 
           // socket.close();
          } catch (IOException ex) {
           System.out.println("Server exception: " + ex.getMessage());
           ex.printStackTrace();
          }
    	}

    
    /**
     * Método main que inicia el cliente
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      	Client cliente = new Client("127.0.0.1", 4444);
      	cliente.sendOrRecieved();;
      }
}