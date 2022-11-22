package org.example;

import org.example.model.Matrix;
import org.example.model.Menu;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Matrix matrix = new Matrix();

        while (true) {

            try {
                Menu.showMenu();
                int n = sc.nextInt();

                if (n == 0) {
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    int value = sc.nextInt();
                    matrix.addNode(value, i, j);
                    System.out.println("node added");
                } else if (n == 1) {
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    int value = sc.nextInt();
                    matrix.remove(i, j, value);
                } else if (n == 2) {
                    int value = sc.nextInt();
                    if (matrix.searchByValue(value)) System.out.println("matrix has this value");
                    else System.out.println("matrix does not have this value");
                } else if (n == 3) {
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    int value = sc.nextInt();
                    matrix.update(value, i, j);
                    System.out.println("node updated");
                } else if (n == 4) {
                    System.out.println("1. sparse matrix");
                    System.out.println("2. 2d array");
                    int m = sc.nextInt();
                    if (m == 1) matrix.printSparseMatrix();
                    else matrix.print2DArray();
                } else if (n == 5) {
                    String fileName = sc.next();
                    Matrix.SaveCSV(matrix, fileName);
                    System.out.println("saved");
                } else if (n == 6) {
                    String path = sc.next();
                    int r = sc.nextInt();
                    int c = sc.nextInt();
                    matrix = Matrix.readCSV(path, r, c);
                    System.out.println("matrix loaded:");
                    matrix.print2DArray();
                } else if (n == 7) {
                    int r = sc.nextInt();
                    int c = sc.nextInt();
                    matrix = new Matrix(r, c);
                    System.out.println("new matrix created");
                } else if (n == 8) {
                    System.exit(0);
                }

            } catch (Exception e) {
                System.out.println("error:\n" + e.getMessage());
            }
        }
    }
}
