package matrix;

import linkedlist.LinkedList;
import linkedlist.Node;

public class Matrix {

    private int rows;
    private int columns;
    private LinkedList matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new LinkedList();

        for (int i=0; i < columns; i++) {
            LinkedList temp = new LinkedList();
            for (int j=0; j<rows; j++) {
                temp.append(false);
            }
            this.matrix.append(temp);
        }
    }

    public int viewValue(int i, int j) {
        int value = -1;

        if (this.matrix.getLength() > j || ((LinkedList) this.matrix.getHead().getData()).indexExists(i))
            value = -1;
        else {
            Node temp = this.matrix.getHead();
            for (int k = 0; k <= j; k++) {
                temp = temp.getNext();
            }

            Node temp2 = ((LinkedList) temp.getData()).getHead();
            for (int k = 0; k <=i; k++) {
                temp2 = temp2.getNext();
            }

            switch ((int) temp2.getData()) {
                case 0:
                    value = 0;
                    break;

                case 1:
                    value = 1;
                    break;

                default:
                    value = -1;
                    break;
            }
        }

        return value;
    }

    public boolean changeValue(int i, int j, int newData) {
        boolean sucessfulChange = true;
        if (this.matrix.getLength() < j || !((LinkedList) this.matrix.getHead().getData()).indexExists(i))
            sucessfulChange = false;
        else {
            Node temp = this.matrix.getHead();
            for (int k = 0; k <= j; k++) {
                temp = temp.getNext();
            }

            Node temp2 = ((LinkedList) temp.getData()).getHead();
            for (int k = 0; k <= i; k++) {
                temp2 = temp2.getNext();
            }

            System.out.println(temp2.getData());
            printMatrix();
        }

        return sucessfulChange;

    }

    public void printMatrix() {
        for (int j=0; j<this.rows; j++) {
            for (int i=0; i<this.rows; i++) {
                System.out.print(this.viewValue(i, j));
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Matrix test = new Matrix(10, 10);
        test.changeValue(0, 0, 0);
    }

}
