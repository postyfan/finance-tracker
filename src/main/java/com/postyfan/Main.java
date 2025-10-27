package com.postyfan;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static TransactionManager manager = new TransactionManager();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to Finance Tracker!");
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    showSummary();
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add Transaction");
        System.out.println("2. View All Transactions");
        System.out.println("3. View Summary");
        System.out.println("4. Exit");
        System.out.print("Choose option: ");
    }
    
    private static void addTransaction() {
        // TODO: Ask user for input and create transaction
        System.out.print("Type (Income/Expense): ");
        String type = scanner.nextLine();
        type = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
        while (!type.equals("Income") && !type.equals("Expense")) {
            System.out.print("Please enter only Income or Expense for type: ");
            type = scanner.nextLine();
            type = type.substring(0,1).toUpperCase() + type.substring(1).toLowerCase();
        }

        // Date is added when this transaction is made
        LocalDateTime date = LocalDateTime.now();

        // Amount Input
        System.out.print("Amount (CAD): ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid Input! Please enter Valid Input");
            scanner.next();
            System.out.print("Amount (CAD): ");
        }
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Need to read '\n' character since nextDouble() doesnt

        // Validate positive amount
        while (amount <= 0) {
            System.out.print("Please enter a valid amount: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid Input! Please enter Valid Input");
                scanner.next();
                System.out.print("Please enter a valid amount: ");
            }
            amount = scanner.nextDouble();
            scanner.nextLine();
        }

        // Category Input
        System.out.print("Category: ");
        String category = scanner.nextLine();
        // Formats Input
        category = category.substring(0,1).toUpperCase() + category.substring(1).toLowerCase();

        // Description Input
        System.out.print("Description: ");
        String description = scanner.nextLine();

        // Create Transaction object    
        // Add to manager
        Transaction t = new Transaction(date,amount,category,description,type);
        manager.addTransaction(t);
        System.out.println("Transaction added!");
    }
    
    private static void viewTransactions() {
        // TODO: Display all transactions nicely
        List<Transaction> t = manager.getAllTransactions();
        if (t.isEmpty()) {
            System.out.println("No Transactinons");
            return;
        }
        int count = 1;
        for (Transaction e : t) {
            System.out.println("Transaction " + count + ": ");
            System.out.println(e);
            count++;
        }
    }
    
    private static void showSummary() {
        // TODO: Show summary by category
        manager.displaySummary();
    }
}
