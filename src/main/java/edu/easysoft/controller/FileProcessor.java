package edu.easysoft.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileProcessor {
    public static void WritingStringToFile(String str,String fileName) {
        byte[] strToBytes = str.getBytes();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(fileName);
            outputStream.write(strToBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static String readFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scaner = new Scanner(file);
        StringBuilder sb = new StringBuilder();
        while(scaner.hasNextLine()){
            System.out.println("------------------------------------------------");
            System.out.println(scaner.nextLine());
            sb.append(scaner.nextLine());

        }
        return sb.toString();
    }
}
