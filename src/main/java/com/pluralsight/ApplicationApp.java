package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ApplicationApp {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);


        displayMainMenu();


        displayLedgerMenu();

        displayReportMenu();


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

    private static void displayReportMenu() {
        System.out.println("===== Report Menu =====");
        System.out.println("(1) Month to Date");
        System.out.println("(2) Previous Month");
        System.out.println("(3) Year to Date");
        System.out.println("(4) Previous Year");
        System.out.println("(5) Search by Vendor");//prompt the user for the vendor name
        // and display all entries for that vendor
        System.out.println("(0) Back"); //goes back to the Ledger page
        System.out.println("(H) Back to home page"); //goes back to the home page

        //need to make switch statement for all menus
    }

    private static void displayLedgerMenu() {
        System.out.println("===== Ledger Display =====");
        System.out.println("(A)All"); //displays all entries
        System.out.println("(D)Deposits");
        System.out.println("(P) Payments");
        System.out.println("(R) Reports"); //goes to next menu to run a more custom search
    }

    private static void displayMainMenu() {
        System.out.println("===== Home Screen =====");
        System.out.println("(D) Add Deposit");
        System.out.println("(P) Make a payment");
        System.out.println("(L)Ledger display ");// needs to go to next menu if L
        System.out.println("(X)Exit the application ");
    }
}

