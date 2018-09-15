
package encode;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;
import org.json.simple.JSONArray;
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

/**
 * Translates a Matrix instance to/from .json format.
 *
 * @author David Azofeifa H.
 */
public class MatrixToJson {

    /**
     * Encodes a Matrix instance into a JSONObject instance.
     *
     * @param matrix, Matrix instance
     * @param score, game score for both players.
     * @return JSONObject with corresponding matrix values.
     */
    public JSONObject encondeMatrix(Matrix matrix, LinkedList score) {
        JSONObject jsonDoc = new JSONObject();

        JSONArray points = new JSONArray();
        points.add(score.getHead().getData());
        points.add(score.getHead().getNext().getData());
        jsonDoc.put("points", points);

        JSONArray _matrix = new JSONArray();

        Node tempNode1 = matrix.getMatrix().getHead();
        for (int i=0; i < matrix.getRows(); i++) {
            JSONArray temp = new JSONArray();
            Node tempNode2 = ((LinkedList) tempNode1.getData()).getHead();
            for (int j=0; j < matrix.getColumns(); j++) {
               temp.add(tempNode2.getData());
                tempNode2 = tempNode2.getNext();
            }
            _matrix.add(temp);
            tempNode1 = tempNode1.getNext();
        }
        jsonDoc.put("matrix", _matrix);
        return jsonDoc;
    }

    /**
     * Decodes a JSONObject instance into a Matrix Instance.
     *
     * @param jsonDoc, JSONObject instance
    * @return Matrix instance
    */
    public Matrix decodeMatrix(JSONObject jsonDoc) {
        //TODO: improve
        int scorePlayer1 = 0;
        int scorePlayer2 = 0;

        JSONArray score = (JSONArray) jsonDoc.get("points");

        //TODO: usar
        scorePlayer1 = (int) score.get(0);
        scorePlayer2 = (int) score.get(1);

        JSONArray _matrix = (JSONArray) jsonDoc.get("matrix");

        int matrixColumns = _matrix.size();
        int matrixRows = ((JSONArray) _matrix.get(0)).size();

        // TODO: parametrizar inicial value
        Matrix matrix = new Matrix(matrixRows, matrixColumns, 0);

        for (int j = 0; j < matrixColumns; j++) {
            JSONArray tempColumn = (JSONArray) _matrix.get(j);
            for (int i = 0; i < matrixRows; i++) {
                int tempValue = (int) tempColumn.get(i);
                matrix.changeValue(i, j, tempValue);
            }
        }

        return matrix;
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

    public static void main(String[] args) {
        MatrixToJson conv = new MatrixToJson();
        Matrix matrix = new Matrix(4,6,1);
        LinkedList score = new LinkedList();
        score.append(300);
        score.append(900);
        JSONObject tempMatrixJson = conv.encondeMatrix(matrix, score);
        conv.saveJsonFile(tempMatrixJson);
    }

}
