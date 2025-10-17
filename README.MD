# Account Ledger Application

This is a Java console application that allows users to record and view financial transactions such as deposits and payments. All transactions are saved to a CSV file and can be filtered or viewed through different menu options.

## Features

- Add deposits
- Make payments
- View all transactions in the ledger
- Filter transactions by deposits or payments
- Generate basic reports:
    - Month to Date
    - Previous Month
    - Year to Date
    - Previous Year
    - Search by Vendor
- Data is stored in a file named `transactions.csv`

## How It Works

1. When the program starts, the main menu appears.
2. Users can select options to add deposits, make payments, or open the ledger menu.
3. The ledger menu allows users to view all entries, deposits only, payments only, or access reports.
4. Reports can be filtered by date or vendor.
5. Pressing `H` returns to the home menu and `X` exits the program.

## File Format

Each transaction is saved in the following format inside `transactions.csv`:

