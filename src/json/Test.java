package json;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class Test {

	public static void main(String[] args) {
		String inputJson = "{ \"dot1PosX\": 1,  \"dot1PosY\": 2,  \"dot2PosX\": 3,  \"dot2PosY\": 4}";
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonParser mp = mapper.readValue(inputJson, JsonParser.class);
			System.out.println(mp.getDot1PosX());
		}catch(IOException mp) {
			System.out.println("Esta vara c mamo");
		}
	}

}
