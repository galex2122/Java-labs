package org.galex.matrix;

import org.galex.complex.ComplexNumber;

public class Matrix {
    private ComplexNumber[][] array;
    private int r;      // rows
    private int c;      // columns

    public Matrix(int r, int c) {
        this.r = r;
        this.c = c;
        this.array = new ComplexNumber[r][c];
    }

    public int getRows() {
        return r;
    }

    public int getColumns() {
        return c;
    }

    public void putElement(ComplexNumber complexNumber, int r, int c) {
        this.array[r][c] = complexNumber;
    }

    public Matrix plus(Matrix secondMatrix) {
        if (this.c == secondMatrix.c && this.r == secondMatrix.r) {
            Matrix newMatrix = new Matrix(r, c);
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newMatrix.array[i][j] = this.array[i][j].add(secondMatrix.array[i][j]);
                }
            }
            return newMatrix;
        } else {
            System.out.println("Can't do this");
            return this;
        }
    }

    public Matrix subtract(Matrix secondMatrix) {
        if (this.c == secondMatrix.c && this.r == secondMatrix.r) {
            Matrix newMatrix = new Matrix(r, c);
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    newMatrix.array[i][j] = this.array[i][j].subtract(secondMatrix.array[i][j]);
                }
            }
            return newMatrix;
        } else {
            System.out.println("Can't do this");
            return this;
        }
    }

    public Matrix multiply(Matrix secondMatrix) {
        if (this.c == secondMatrix.r) {
            Matrix newMatrix = new Matrix(this.r, secondMatrix.c);
            for (int i = 0; i < newMatrix.getRows(); i++) {
                for (int j = 0; j < newMatrix.getColumns(); j++) {
                    ComplexNumber newComplex = new ComplexNumber(0, 0);
                    for (int k = 0; k < this.c; k++) {
                        newComplex = newComplex.add(this.array[i][k].multiply(secondMatrix.array[k][j]));
                    }
                    newMatrix.array[i][j] = newComplex;
                }
            }
            return newMatrix;
        } else {
            System.out.println("Can't do this");
            return this;
        }
    }

    public Matrix transpose() {
        Matrix newMatrix = new Matrix(this.c, this.r);
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                newMatrix.array[j][i] = this.array[i][j];
            }
        }
        return newMatrix;
    }

    public ComplexNumber getDeterminant() {
        if (this.r == this.c) {
            return determinant(this.array);
        } else {
            System.out.println("Can't do this");
            return new ComplexNumber(0, 0);
        }
    }

    private ComplexNumber determinant(ComplexNumber[][] arr) {
        int n = arr.length;
        if (n == 1) {
            return arr[0][0];
        }
        ComplexNumber res = new ComplexNumber(0, 0);
        int l = 1;
        for (int i = 0; i < n; i++) {
            ComplexNumber[][] arrB = new ComplexNumber[n - 1][n - 1];
            int x = 0, y = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                for (int k = 1; k < n; k++) {
                    arrB[x][y] = arr[k][j];
                    ++x;
                }
                x = 0;
                ++y;
            }
            res = res.add(arr[0][i].multiply(l).multiply(determinant(arrB)));
            l *= -1;
        }
        return res;
    }

    public void printMatrix() {
        for (int i = 0; i < this.r; i++) {
            for (int j = 0; j < this.c; j++) {
                System.out.print(this.array[i][j] + "  ");
            }
            System.out.println();
        }
    }
}
