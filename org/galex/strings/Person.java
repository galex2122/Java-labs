package org.galex.strings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String dateString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate formattedBirthDate = null;
        try {
            formattedBirthDate = LocalDate.parse(dateString, format);

        } catch (DateTimeParseException e) {
            System.out.println("Неверная дата, не установлена");
        }
        this.birthDate = formattedBirthDate;
    }

    public int getAge() {
        LocalDate now = LocalDate.now();
        try {
            return (int) ChronoUnit.YEARS.between(this.birthDate, now);
        } catch (NullPointerException e) {
            System.out.println("Дата рождения не задана");
        }
        return 0;
    }

    public String getGender() {
        try {
            if (this.patronymic.charAt(this.patronymic.length() - 1) == 'а') {
                return "Ж";
            } else {
                return "М";
            }
        } catch (NullPointerException e) {
            return "Отчество не задано";
        }
    }

    @Override
    public String toString() {
        return this.surname + " " + this.name + " " + this.patronymic +
                " Пол - " + this.getGender() +
                " Дата рождения - " + this.birthDate.toString() +
                " Возраст - " + this.getAge();
    }
}
