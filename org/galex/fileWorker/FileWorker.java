package org.galex.fileWorker;

import java.io.*;
import java.util.Scanner;

public class FileWorker {
    private int[] lettersCounter;
    FileReader fileReader;
    FileWriter fileWriter;
    Scanner scanner;

    public FileWorker() {
        scanner = new Scanner(System.in);
        lettersCounter = new int[26];
    }

    public void setFileReader() {
        System.out.println("Please enter name or path to file");
        String path = scanner.next();
        try {
            fileReader = new FileReader(path);
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
            setFileReader();
        }
    }

    public void countFile() {
        try {
            while (fileReader.ready()) {
                int letter = fileReader.read();
                if (letter >= 65 && letter <= 90) {  // char from A to Z
                    lettersCounter[letter - 65]++;
                } else if (letter >= 97 && letter <= 122) {  // char from a to z
                    lettersCounter[letter - 97]++;
                }
            }
            fileReader.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void printResult() {
        System.out.println("Please enter path for result file");
        String path = scanner.next();
        try {
            fileWriter = new FileWriter(path);
            for (int i = 0; i < lettersCounter.length; i++) {
                fileWriter.write((char) (i + 65) + " " + (char) (i + 97) + " - " + lettersCounter[i] + "\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public int[] getLettersCounter(){
        return lettersCounter;
    }
}
