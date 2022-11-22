package org.example.model;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.example.exception.NodeAlreadyExistsException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Matrix {

    private LinkedList[] rows;
    private LinkedList[] cols;

    public Matrix() {
    }

    public Matrix(int r, int c) {
        rows = new LinkedList[r];
        cols = new LinkedList[c];

        for (int i = 0; i < r; i++)
            rows[i] = new LinkedList();

        for (int i = 0; i < c; i++)
            cols[i] = new LinkedList();
    }

    // methods -----------------------------------------------------------------

    public static Matrix readCSV(String fileName, int rows, int cols) throws IOException {

        CSVReader reader = new CSVReader(new FileReader("data/" + fileName));
        reader.getLinesRead();
        String[] nextLine;

        Matrix matrix = new Matrix(rows, cols);

        int i = 0;
        while ((nextLine = reader.readNext()) != null) {
            int j = 0;
            for (String token : nextLine) {
                int x = Integer.parseInt(token);
                if (x != 0)
                    matrix.addNode(x, i, j);
                j++;
            }
            i++;
        }

        return matrix;
    }

    public static void SaveCSV(Matrix matrix, String file) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter("data/" + file, false), ',', '\0');
        for (String[] s : matrix.get2DStringArray())
            writer.writeNext(s);
        writer.flush();
        writer.close();
    }

    public void addNode(int value, int i, int j) {

        if (value == 0) return;

        if (searchByIndex(i, j))
            throw new NodeAlreadyExistsException();

        rows[i].addNode(j, value);
        cols[j].addNode(i, value);
    }

    public void remove(int i, int j, int value) {
        if (getNodeValueInRowList(i, j) == value) {
            if (rows[i].removeNode(j))
                System.out.println("node removed");
            else
                System.out.println("node does not exist");
        } else
            System.out.println("the node i:" + i + " j:" + j + " does not have value:" + value);
    }

    public boolean searchByValue(int value) {
        for (LinkedList list : rows)
            if (list.search(value))
                return true;
        return false;
    }

    public boolean searchByIndex(int i, int j) {
        for (int k = 0; k < rows.length; k++)
            if (rows[i].searchByIndex(j))
                return true;

        return false;
    }

    public void update(int value, int i, int j) {
        if (value == 0) remove(i, j, value);
        rows[i].updateNode(j, value);
    }

    public int[][] get2DArray() {

        int[][] mat = new int[rows.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (rows[i].searchByIndex(j)) mat[i][j] = getNodeValueInRowList(i, j);
                else mat[i][j] = 0;
            }
        }

        return mat;
    }

    public String[][] get2DStringArray() {
        String[][] mat = new String[rows.length][cols.length];

        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (rows[i].searchByIndex(j)) mat[i][j] = String.valueOf(getNodeValueInRowList(i, j));
                else mat[i][j] = "0";
            }
        }

        return mat;
    }

    public void printSparseMatrix() {
        int i = 0;
        for (LinkedList list : rows)
            list.printList(i++);
    }

    public void print2DArray() {
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (rows[i].searchByIndex(j)) System.out.print(getNodeValueInRowList(i, j) + " ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public int getNodeValueInRowList(int i, int j) {
        return rows[i].getNodeValue(j);
    }

    public int getNodeValueInColList(int i, int j) {
        return cols[j].getNodeValue(i);
    }

    // setter / getter -----------------------------------------------------------------

    public LinkedList[] getRows() {
        return rows;
    }

    public void setRows(LinkedList[] rows) {
        this.rows = rows;
    }

    public LinkedList[] getCols() {
        return cols;
    }

    public void setCols(LinkedList[] cols) {
        this.cols = cols;
    }
}
