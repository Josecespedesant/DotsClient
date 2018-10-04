package json_parse;

import json_conversion.Conversion;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import linkedlist.LinkedList;
import linkedlist.Node;
import matrix.Matrix;
import people.Player;

import javax.sound.sampled.Line;

    /**
    * Translates a Matrix instance to/from .json format.
    *
    * @author David Azofeifa H.
    **/
public class Parse {

    /** Encodes current game state info into a JSONObject instance.
     *
     * @param matrix
     * @param player1
     * @param player2
     * @param posMouse
     * @return
     **/
    public JSONObject gameStateToJson(Matrix matrix, Player player1, Player player2, LinkedList posMouse) {
        JSONObject jsonDoc = new JSONObject();


        // Creating mouse position as array for jsonDoc
        JSONArray _posMouse = new JSONArray();
        _posMouse.add(posMouse.getHead().getData());
        _posMouse.add(posMouse.getHead().getNext().getData());
        jsonDoc.put("mouse", _posMouse);

        // Creating players json object
        JSONObject playersJsonDoc = new JSONObject();

        // Creating player 1 array for playersJsonDoc
        JSONArray _player1 = new JSONArray();
        _player1.add(player1.getName());
        _player1.add(player1.getScore());
        _player1.add(player1.getActiveTurn());
        playersJsonDoc.put("1", _player1);

        // Creating player 2 array for playersJsonDoc
        JSONArray _player2 = new JSONArray();
        _player2.add(player2.getName());
        _player2.add(player2.getScore());
        _player2.add(player2.getActiveTurn());
        playersJsonDoc.put("2", _player2);

        // putting playersJsonDoc in jsonDoc
        jsonDoc.put("players", playersJsonDoc);


        // Creating matrix for jsonDoc instance
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
     * // Todo modify method to accomodate new data
     *
     * Decodes a JSONObject instance into a Matrix Instance.
     *
     * @param jsonDoc, JSONObject instance
     * @return Matrix instance
     */
    public LinkedList JsonToGameState(JSONObject jsonDoc) {
        // Decoding players
        JSONObject players = (JSONObject) jsonDoc.get("players");

        // Decoding player1
        JSONArray player1 = (JSONArray) players.get("1");
        String player1Name = (String) player1.get(0);
        Player _player1 = new Player(player1Name);
        int player1Score = (int) _player1.getScore();
        _player1.setScore(player1Score);
        boolean player1ActiveTurn = (boolean) _player1.getActiveTurn();
        _player1.setActiveTurn(player1ActiveTurn);

        // Decoding player1
        JSONArray player2 = (JSONArray) players.get("2");
        String player2Name = (String) player2.get(0);
        Player _player2 = new Player(player2Name);
        int player2Score = (int) _player2.getScore();
        _player2.setScore(player2Score);
        boolean player2ActiveTurn = (boolean) _player2.getActiveTurn();
        _player2.setActiveTurn(player2ActiveTurn);

        // Decoding Mouse position
        LinkedList posMouse = new LinkedList();
        JSONArray mouse = new JSONArray();
        posMouse.append(mouse.get(0));
        posMouse.append(mouse.get(1));

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
        LinkedList gameState = new LinkedList();
        gameState.append(matrix);
        gameState.append(_player1);
        gameState.append(_player2);
        gameState.append(posMouse);
        return gameState;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix(4, 4, 5);
        LinkedList posMouse = new LinkedList();
        posMouse.append(40);
        posMouse.append(70);
        Player player1 = new Player("David");
        Player player2 = new Player("Daniel");

        Parse parser = new Parse();
        JSONObject doc = parser.gameStateToJson(matrix, player1, player2, posMouse);

        Conversion conv = new Conversion();
        conv.saveJsonFile(doc);
    }
}