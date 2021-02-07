package org.galex.complex;

public class Main {
    public static void main(String[] args){
        ComplexNumber a = new ComplexNumber(3, 4.3);
        ComplexNumber b = new ComplexNumber(5, 3.3);
        System.out.println(b.toString());
        a = a.multiply(b);
        System.out.println(a.toString());
        System.out.println(b.toTrigonometric());
    }
}
