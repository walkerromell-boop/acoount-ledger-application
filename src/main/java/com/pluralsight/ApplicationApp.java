package com.pluralsight;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ApplicationApp {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


//        displayMainMenu();
        runMainMenu();

//
//        displayLedgerMenu();
//
//        displayReportMenu();
//
//
//        runFileReader();


    }

    public static void runMainMenu() {
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.println("Choose an option: ");

            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "D":
                    addDepositMenu();
                    displayDeposits();
                    break;
                case "P":
                    addPayment();
                    break;
                case "L":
                    displayLedgerMenu();
                    break;
//                case "R":
//                    displayReportMenu();
//                    break;
                case "X":
                    System.out.println("Exiting app... Have a blessed day");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Read the choices again bro!!!");


            }
        }
    }

    private static void runFileReader() {
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
        boolean report = true;
        while (report) {
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

            System.out.println("Choose an option: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "1":
                    System.out.println("Showing Month to Date report...");
                    break;
                case "2":
                    System.out.println("Showing Previous Month report...");
                    break;
                case "3":
                    System.out.println("Showing Year to Date report...");
                    break;
                case "4":
                    System.out.println("Showing Previous Year report...");
                    break;
                case "5":
                    System.out.println("Search by Vendor selected...");
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    report = false; // go back to Ledger
                    break;
                case "H":
                    System.out.println("Exiting... Goodbye!");
                    return; // returns to main menu
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        }


    }

    private static void displayLedgerMenu() {
        boolean ledger = true;
        while (ledger) {
            System.out.println("===== Ledger Menu =====");
            System.out.println("(A)All"); //displays all entries
            System.out.println("(D)Deposits");
            System.out.println("(P) Payments");
            System.out.println("(R) Reports"); //goes to next menu to run a more custom search
            System.out.println("(H) Returning to home menu");
            System.out.println("Choose a option: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A":
                    System.out.println("Showing Whole ledger report...");
                    break;
                case "D":
                    System.out.println("Showing Deposits only...");
                    break;
                case "P":
                    System.out.println("Showing Payments only...");
                    break;
                case "R":
                    displayReportMenu();
                    break;
                case "H":
                    System.out.println("Returning to home menu...");
                    ledger = false;
                default:
                    System.out.println("Invalid choice. Please try again.");

            }
        }

    }

    private static void displayMainMenu() {
        boolean running = true;
        System.out.println("===== Home Screen =====");
        System.out.println("(D) Add Deposit");
        System.out.println("(P) Make a payment");
        System.out.println("(L)Ledger display ");// needs to go to next menu if L
        System.out.println("(X)Exit the application ");
        if (running == !true) {
        }
    }

    private static void addDepositMenu() {
        boolean addMore = true;
//        All deposits should be positive.
        //  Ask the user for their deposit info
        while (addMore) {
            System.out.print("Enter deposit date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            try (FileWriter fileWriter = new FileWriter("deposits.csv", true);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

                bufferedWriter.write(date + "|" + accountNumber + "|" + name + "|" + amount + "\n");
                System.out.println("Deposit successfully made!");

            } catch (IOException e) {
                System.out.println("ERROR: Cannot make the deposit ");
//            e.getStackTrace();
            }
            // Ask if user wants to add another
            System.out.print("Would you like to make another deposit? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();

            if (!response.equals("yes") && !response.equals("y")) {
                addMore = false;
                System.out.println("Returning to main menu...");
            } else {
                System.out.println("\n---Add another deposit---\n");

            }
        }
    }

    private static void addPayment() {
//        Adding payment in this section and all debits should be negative
        boolean addMore = true;
        System.out.println("Enter your card number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the expiration date of card: ");
        String date = scanner.nextLine();
        System.out.println("Enter Cvv of card: ");
        Integer cvv = scanner.nextInt();
        System.out.println("How much do you want to pay: ");
        Double debit = scanner.nextDouble();

        try (FileWriter fileWriter = new FileWriter("debit.csv", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(date + "|" + cardNumber + "|" + name + "|" + cvv + "|" + debit + "\n");
            System.out.println("Debit successfully made!");

        } catch (IOException e) {
            System.out.println("ERROR: Cannot make the debit ");
//            e.getStackTrace();
        }
        // Ask if user wants to add another
        System.out.print("Would you like to make another deposit? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();

        if (!response.equals("yes") && !response.equals("y")) {
            addMore = false;
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("\n---Add another debit---\n");

        }

    }

    private static void displayDeposits() {
        System.out.println("\n ---All Deposits---");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("deposits.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(Pattern.quote("|"));
                if (parts.length == 4) {
                    System.out.printf("Date: %s | Account: %s | Name: %s | Amount: %s%n",
                            parts[0], parts[1], parts[2], parts[3]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No deposits found yet (file not created).");
        } catch (IOException e) {
            System.out.println(" Error reading deposits: " + e.getMessage());
        }
        System.out.println("--------------------\n");
    }
}





