package comm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        try{
            String nombres = brs.readLine();
            JSONParser parser1 = new JSONParser();
            JSONObject jsonConNombres = (JSONObject) parser1.parse(nombres);
            obj = jsonConNombres;
        }catch(IOException ex){
            System.out.println("I/O error: "+ ex.getMessage());
        }
		return obj;
    }
	
	
}
