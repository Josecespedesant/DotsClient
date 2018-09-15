package matrix;

import linkedlist.LinkedList;
import linkedlist.Node;

/**
 * Binary matrix made of lists that contain lists.
 *
 * @author David Azofeifa H.
 */

public class Matrix {

    private int rows;
    private int columns;
    private LinkedList matrix;

    public int getRows() { return rows; }

    public void setRows(int rows) { this.rows = rows; }

    public int getColumns() { return columns; }

    public void setColumns(int columns) { this.columns = columns; }

    public LinkedList getMatrix() { return matrix; }

    public void setMatrix(LinkedList matrix) {
        this.matrix = matrix;
    }

    public Matrix(int rows, int columns, int initialValue) {
        int initialValue1 = initialValue;
        this.rows = rows;
        this.columns = columns;
        this.matrix = new LinkedList();

        for (int i=0; i < this.rows; i++) {
            LinkedList temp = new LinkedList();
            for (int j=0; j < this.columns; j++) {
                temp.append(initialValue1);
            }
            this.matrix.append(temp);
        }
    }

    /**
     * Searches for value in specified index
     *
     * @param i, row index
     * @param j, column index
     * @return value matrix contains in specified index
     */
    public int viewValue(int i, int j) {
        // TODO: hacer que correctamente retorne falso con i o j mayores de lo permitido
        int value;
        if (this.matrix.getLength() < j && !((LinkedList) this.matrix.getHead().getData()).indexExists(i))
            value = -1;
        else {
            Node temp = this.matrix.getHead();
            for (int k = 0; k < j; k++) {
                temp = temp.getNext();
            }

            Node temp2 = ((LinkedList) temp.getData()).getHead();
            for (int k = 0; k < i; k++) {
                temp2 = temp2.getNext();
            }

            value = (int) temp2.getData();
        }

        return value;
    }

    /**
     * Searches for value in specified index, and changes it's value if fiybd.
     *
     * @param i, row index
     * @param j, column index
     * @param newData, data that'll replace matrix[i,j]
     * @return does matrix[i.j] exist
     */
    public boolean changeValue(int i, int j, int newData) {
        // TODO: hacer que correctamente retorne falso con i o j mayores de lo permitido
        boolean sucessfulChange = true;
        if (this.matrix.getLength() < j && !((LinkedList) this.matrix.getHead().getData()).indexExists(i))
            sucessfulChange = false;
        else {
            Node temp = this.matrix.getHead();
            for (int k = 0; k < j; k++) {
                temp = temp.getNext();
            }

            Node temp2 = ((LinkedList) temp.getData()).getHead();
            for (int k = 0; k < i; k++) {
                temp2 = temp2.getNext();
            }
            temp2.setData(newData);
        }

        return sucessfulChange;
    }

    /**
     * Prints all matrix values (in respective order) on console
     */
    public void printMatrix() {
        for (int j=0; j<this.rows; j++) {
            for (int i=0; i<this.rows; i++) {
                System.out.print(this.viewValue(i, j));
            }
            System.out.println("");
        }
    }
}
