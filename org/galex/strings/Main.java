package org.galex.strings;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Person person = new Person();
        System.out.println("Введите имя: ");
        person.setName(in.nextLine());
        System.out.println("Введите фамилию: ");
        person.setSurname(in.nextLine());
        System.out.println("Введите отчество: ");
        person.setPatronymic(in.nextLine());
        while (person.getBirthDate() == null) {
            System.out.println("Введите дату рождения в формате дд.мм.гггг: ");
            person.setBirthDate(in.nextLine());
        }
        System.out.println(person.toString());
    }
}
