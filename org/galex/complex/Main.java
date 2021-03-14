package org.galex.complex;

public class Main {
    public static void main(String[] args){
        ComplexNumber a = new ComplexNumber(0, 0);
        ComplexNumber b = new ComplexNumber(0, 0);
        System.out.println(b.toString());
        a = a.multiply(b);
        System.out.println(a.toString());
        a = a.divide(b);
        System.out.println(a.toString());
        System.out.println(b.toTrigonometric());
    }
}
