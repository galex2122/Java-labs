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

        try {
            System.out.println(matrix.getDeterminant());
            System.out.println();
        }catch (ArithmeticException arithmeticException){
            System.out.println(arithmeticException.getMessage());
        }

        matrix = matrix.transpose();
        matrix.printMatrix();
        System.out.println();

        try {
            matrix = matrix.multiply(matrix1);
            matrix.printMatrix();
            System.out.println();
        }catch (ArithmeticException arithmeticException){
            System.out.println(arithmeticException.getMessage());
        }

        try {
            System.out.println(matrix1.getDeterminant());
            System.out.println();
        }catch (ArithmeticException arithmeticException){
            System.out.println(arithmeticException.getMessage());
        }
    }
}
