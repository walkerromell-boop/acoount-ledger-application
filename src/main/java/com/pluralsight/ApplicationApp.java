package com.pluralsight;

import java.time.LocalTime;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    private static boolean displayReportMenu() {
        List<Transactions> transactions = readTransactions("transactions.csv");
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
                    showMonthToDate(transactions);
                    break;

                case "2":
                    showPreviousMonth(transactions);
                    break;

                case "3":
                    showYearToDate(transactions);
                    break;

                case "4":
                    showPreviousYear(transactions);
                    break;

                case "5":
                    searchByVendor(transactions);
                    break;

                case "0":
                    report = false; // go back to Ledger
                    break;

                case "H":
                    //System.out.println("returning to main menu");
                    return true;

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;

            }

        }

        return false;
    }

    private static void displayLedgerMenu() {
        List<Transactions> transactions = readTransactions("transactions.csv");

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
                    displayResults(transactions);
                    break;
                case "D":
                    System.out.println("Showing Deposits only...");
                    List<Transactions> deposits = transactions.stream()
                            .filter(t -> t.getAmount() > 0)
                            .toList();
                    displayResults(deposits);
                    break;
                case "P":
                    System.out.println("Showing Payments only...");
                    List<Transactions> payments = transactions.stream()
                            .filter(t -> t.getAmount() < 0)
                            .toList();
                    displayResults(payments);
                    break;
                case "R":
                    boolean backToMain = displayReportMenu();
                    if (backToMain) return;
                    break;
                case "H":
                    System.out.println("Returning to home menu...");
                    return;
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
//            System.out.print("Enter deposit date (YYYY-MM-DD): ");
//            String date = scanner.nextLine();

            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine();

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); //consumes the next line

            // Auto date & time
            String date = LocalDate.now().toString();
            String time = LocalTime.now().withNano(0).toString(); // removes nanoseconds

            // Write deposit to transactions.csv
            writeTransactionToFile(date, time, "Deposit to " + accountNumber, name, amount);

//            try {
//                String time = "00:00"; // You can add a real time if you want
//                writeTransactionToFile(date, time, "Deposit", name, amount);
//                System.out.println("Deposit successfully added!");
////            try (FileWriter fileWriter = new FileWriter("deposits.csv", true);
////                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
////
////                bufferedWriter.write(date + "|" + accountNumber + "|" + name + "|" + amount + "\n");
////                System.out.println("Deposit successfully made!");
//
//            } catch (Exception e) {
//                System.out.println("ERROR: Cannot make the deposit ");
////            e.getStackTrace();
//            }
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
        while (addMore) {
            System.out.println("Enter your card number: ");
            String cardNumber = scanner.nextLine();
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Enter the expiration date of card: ");
            String expiredDate = scanner.nextLine();
            System.out.println("Enter Cvv of card: ");
            Integer cvv = scanner.nextInt();
            System.out.println("How much do you want to pay: ");
            Double debit = scanner.nextDouble();
            scanner.nextLine();

            // Auto date & time
            String date = LocalDate.now().toString();
            String time = LocalTime.now().withNano(0).toString();
            double negativeAmount = -Math.abs(debit);

            // Write payment to transactions.csv
            writeTransactionToFile(date, time, "Payment via card " + cardNumber, name, negativeAmount);

//            try {
//                double debitAmount = -Math.abs(debit); // ensures it's negative
//                String time = "00:00"; // placeholder, or use LocalTime.now().toString()
//                writeTransactionToFile(date, time, "Payment", name, debitAmount);
//                System.out.println("Payment successfully added!");
//
////            try (FileWriter fileWriter = new FileWriter("debit.csv", true);
////                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
////
////                bufferedWriter.write(date + "|" + cardNumber + "|" + name + "|" + cvv + "|" + debit + "\n");
////                System.out.println("Debit successfully made!");
//
//            } catch (Exception e) {
//                System.out.println("ERROR: Cannot make the debit ");
////            e.getStackTrace();
//            }
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

    }

    private static void displayDeposits() {
        System.out.println("\n ---All Deposits---");
        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(Pattern.quote("|"));

                // Skip the header or incomplete rows
                if (parts.length < 5 || parts[4].equalsIgnoreCase("amount")) {
                    continue;
                }

                try {
                    double amount = Double.parseDouble(parts[4].trim());
                    if (amount > 0) { // Deposit only
                        System.out.printf("Date: %s | Time: %s | Description: %s | Vendor: %s | Amount: %.2f%n",
                                parts[0], parts[1], parts[2], parts[3], amount);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing amount in line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No transactions found yet (file not created).");
        } catch (IOException e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        System.out.println("--------------------\n");
    }

    private static List<Transactions> readTransactions(String filename) {
        List<Transactions> transactions = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            bufferedReader.readLine(); // skip header line
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue; // skip incomplete lines

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transactions transaction = new Transactions(date, time, description, vendor, amount);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return transactions;
    }

    private static void writeTransactionToFile(String date, String time, String description, String vendor, double amount) {
        String fileName = "transactions.csv";
        String newEntry = date + "|" + time + "|" + description + "|" + vendor + "|" + amount + "\n";

        try {
            File file = new File(fileName);
            List<String> lines = new ArrayList<>();

            // If file exists, it read its content first
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines.add(line);
                    }
                }
            }

            // Now it writes new entry FIRST, then the old lines
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                for (String line : lines) {
                    writer.write(line + "\n");
                }
                writer.write(newEntry); // new entry at bottom
            }

            System.out.println("Transaction saved successfully!");
        } catch (IOException e) {
            System.out.println("Error writing transaction: " + e.getMessage());
        }
    }


    private static void showMonthToDate(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        List<Transactions> filtered = transactions.stream()
                .filter(t -> t.getDate().getMonth() == now.getMonth() &&
                        t.getDate().getYear() == now.getYear())
                .toList();

        System.out.println("\n=== Month to Date Report ===");
        displayResults(filtered);
    }

    private static void showPreviousMonth(List<Transactions> transactions) {
        LocalDate now = LocalDate.now().minusMonths(1);
        List<Transactions> filtered = transactions.stream()
                .filter(t -> t.getDate().getMonth() == now.getMonth() &&
                        t.getDate().getYear() == now.getYear())
                .toList();

        System.out.println("\n=== Previous Month Report ===");
        displayResults(filtered);
    }

    private static void showYearToDate(List<Transactions> transactions) {
        LocalDate now = LocalDate.now();
        List<Transactions> filtered = transactions.stream()
                .filter(t -> t.getDate().getYear() == now.getYear())
                .toList();

        System.out.println("\n=== Year to Date Report ===");
        displayResults(filtered);
    }

    private static void showPreviousYear(List<Transactions> transactions) {
        LocalDate now = LocalDate.now().minusYears(1);
        List<Transactions> filtered = transactions.stream()
                .filter(t -> t.getDate().getYear() == now.getYear())
                .toList();

        System.out.println("\n=== Previous Year Report ===");
        displayResults(filtered);
    }

    private static void searchByVendor(List<Transactions> transactions) {
        System.out.print("Enter vendor name: ");
        String vendorName = scanner.nextLine().trim().toLowerCase();

        List<Transactions> filtered = transactions.stream()
                .filter(t -> t.getVendor().toLowerCase().contains(vendorName))
                .toList();

        System.out.println("\n=== Transactions for Vendor: " + vendorName + " ===");
        displayResults(filtered);
    }

    private static void displayResults(List<Transactions> list) {
        if (list.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            list.forEach(System.out::println);
        }
        System.out.println(); // blank line for spacing
    }


}





