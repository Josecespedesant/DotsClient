package json_conversion;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Conversion {

    /**
     * Reads .json file and turns it into an JSONObject instance.
     *
     * @param docName, .json file name
     * @return
     */
    public JSONObject fetchJsonFile(String docName) {
        JSONObject json = null;
        try {
            FileReader doc = new FileReader(docName);
            JSONParser parser = new JSONParser();
            try {
                json = (JSONObject) parser.parse(doc);
            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * Turns a JSONObject instance into a file and saves to disk.
     *
     * @param jsonDoc
     */
    public void saveJsonFile(JSONObject jsonDoc) {
        List<String> lines = Arrays.asList(jsonDoc.toString());
        Path file = Paths.get("matrixAsJson.json");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Coudn't save fila in specified path");
        }
    }
}
