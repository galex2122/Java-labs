package org.galex.fileWorker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        FileWorker fw = new FileWorker();
        fw.setFileReader();
        fw.countFile();
        fw.printResult();
    }
}
