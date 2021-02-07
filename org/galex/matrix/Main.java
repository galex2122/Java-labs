package org.galex.matrix;
import org.galex.complex.ComplexNumber;

public class Main {
    public static void main(String[] args){
        Matrix matrix = new Matrix(3, 3);
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                matrix.putElement(new ComplexNumber(i, j), i, j);
            }
        }
        Matrix matrix1 = new Matrix(3, 4);
        for (int i = 0; i < matrix1.getRows(); i++) {
            for (int j = 0; j < matrix1.getColumns(); j++) {
                matrix1.putElement(new ComplexNumber(i, j), i, j);
            }
        }
        matrix.printMatrix();
        System.out.println();
        System.out.println(matrix.getDeterminant());
        System.out.println();
        matrix = matrix.transpose();
        matrix.printMatrix();
        System.out.println();
        matrix = matrix.multiply(matrix1);
        matrix.printMatrix();
        System.out.println();
    }
}
