package encode;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;
import org.json.JSONObject;
import org.json.JSONArray;

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
        points.put(score.getHead().getData());
        points.put(score.getHead().getNext().getData());
        jsonDoc.put("points", points);

        JSONArray _matrix = new JSONArray();

        Node tempNode1 = matrix.getMatrix().getHead();
        for (int i=0; i < matrix.getRows(); i++) {
            JSONArray temp = new JSONArray();
            Node tempNode2 = ((LinkedList) tempNode1.getData()).getHead();
            for (int j=0; j < matrix.getColumns(); j++) {
               temp.put(tempNode2.getData());
                tempNode2 = tempNode2.getNext();
            }
            _matrix.put(temp);
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

        scorePlayer1 = score.getInt(0);
        scorePlayer2 = score.getInt(1);

        JSONArray _matrix = (JSONArray) jsonDoc.get("matrix");

        int matrixColumns = _matrix.length();
        int matrixRows = ((JSONArray) _matrix.get(0)).length();

        // TODO: parametrizar inicial value
        Matrix matrix = new Matrix(matrixRows, matrixColumns, 0);

        for (int j = 0; j < matrixColumns; j++) {
            JSONArray tempColumn = (JSONArray) _matrix.get(j);
            for (int i = 0; i < matrixRows; i++) {
                int tempValue = tempColumn.getInt(i);
                matrix.changeValue(i, j, tempValue);
            }
        }

        return matrix;
    }

    public void saveJsonFile(JSONObject jsonDoc) {
        List<String> lines = Arrays.asList(jsonDoc.toString(4));
        Path file = Paths.get("matrixAsJson.json");
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Coudn't save fila in specified path");
        }
    }

    public void fetchJsonFile() {

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
