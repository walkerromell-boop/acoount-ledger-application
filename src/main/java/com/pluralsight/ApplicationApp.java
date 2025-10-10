package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ApplicationApp {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);









        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean hasContent = false;

            while ((line = bufferedReader.readLine()) != null) {
                hasContent = true;
                System.out.println(line); // shows what was read
            }

            if (hasContent) {
                System.out.println(" File read successfully!");
            } else {
                System.out.println(" File is empty.");
            }
            bufferedReader.close();
        } catch (
                FileNotFoundException e) {
            System.out.println(" File not found: " + e.getMessage());
        } catch (
                IOException e) {
            System.out.println(" Error reading file: " + e.getMessage());
        }
    }
}

