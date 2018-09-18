
package json_parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;

/**
 * Translates a Matrix instance to/from .json format.
 *
 * @author David Azofeifa H.
 */
public class Parse {

    /**
     * Encodes a Matrix instance into a JSONObject instance.
     *
     * @param matrix, Matrix instance
     * @param score, game score for both players.
     * @return JSONObject with corresponding matrix values.
     */
    public JSONObject MatrixToJson(Matrix matrix, LinkedList score) {
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
    public Matrix JsonToMatrix(JSONObject jsonDoc) {
        //TODO: improve
        int scorePlayer1 = 0;
        int scorePlayer2 = 0;

        JSONArray score = (JSONArray) jsonDoc.get("points");

        //TODO: usar
        //scorePlayer1 = (int) score.get(0);
        //scorePlayer2 = (int) score.get(1);

        JSONArray _matrix = (JSONArray) jsonDoc.get("matrix");

        int matrixColumns = _matrix.size();
        int matrixRows = ((JSONArray) _matrix.get(0)).size();

        // TODO: parametrizar inicial value
        Matrix matrix = new Matrix(matrixRows, matrixColumns, 0);

        for (int j = 0; j < matrixColumns; j++) {
            JSONArray tempColumn = (JSONArray) _matrix.get(j);
            for (int i = 0; i < matrixRows; i++) {
                int tempValue = ((Long) tempColumn.get(i)).intValue();
                matrix.changeValue(i, j, tempValue);
            }
        }
        return matrix;
    }
}
