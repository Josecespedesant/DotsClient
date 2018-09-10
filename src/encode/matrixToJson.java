package encode;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Translates a Matrix instance to/from .json format.
 *
 * @author David Azofeifa H.
 */
public class matrixToJson {

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

        JSONArray

        Matrix tempMatrix = new Matrix(_matrix.length(), _);

        return matrix;
    }

    public void fetchJsonFile() {

    }


    public static void main(String[] args) {

        JSONObject general = new JSONObject();
        JSONObject temp = new JSONObject();

        temp.put("test", 0);

        general.put("rows", 5);
        general.put("columnas", 5);
        general.put("myTestie", temp);

        JSONArray array = new JSONArray();

        array.put(3);
        array.put(4);


        general.put("puntos",array);

        System.out.println(general.keySet());

    }

}
