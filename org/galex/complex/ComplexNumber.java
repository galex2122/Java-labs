package org.galex.complex;

public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber secondNumber) {
        return new ComplexNumber(this.real += secondNumber.real,
                this.imaginary += secondNumber.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber secondNumber) {
        return new ComplexNumber(this.real -= secondNumber.real,
                this.imaginary -= secondNumber.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber secondNumber) {
        return new ComplexNumber(this.real * secondNumber.real -
                this.imaginary * secondNumber.imaginary,
                this.real * secondNumber.imaginary +
                        this.imaginary * secondNumber.real);
    }

    public ComplexNumber multiply(int a) {
        return new ComplexNumber(this.real * a, this.imaginary * a);
    }

    public ComplexNumber divide(ComplexNumber secondNumber) {
        return new ComplexNumber((this.real * secondNumber.real +
                this.imaginary * secondNumber.imaginary) /
                (Math.pow(secondNumber.real, 2) + Math.pow(secondNumber.imaginary, 2)),
                (this.imaginary * secondNumber.real - this.real * secondNumber.imaginary) /
                        (Math.pow(secondNumber.real, 2) + Math.pow(secondNumber.imaginary, 2)));
    }

    @Override
    public String toString() {
        return this.real+" + "+this.imaginary+"i";
    }

    public String toTrigonometric(){
        double module = Math.sqrt(Math.pow(this.real, 2)+Math.pow(this.imaginary, 2));
        double underCos = this.real / Math.sqrt(Math.pow(this.real, 2)+Math.pow(this.imaginary, 2));
        double underSin = this.imaginary / Math.sqrt(Math.pow(this.real, 2)+Math.pow(this.imaginary, 2));
        return module + "(cos(" + underCos + ") + sin(" + underSin + ")*i)";
    }
}
