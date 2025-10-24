package com.anton;

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
        System.out.print("Type (income/expense): ");
        // ... get other inputs
        // Create Transaction object
        // Add to manager
        System.out.println("Transaction added!");
    }
    
    private static void viewTransactions() {
        // TODO: Display all transactions nicely
    }
    
    private static void showSummary() {
        // TODO: Show summary by category
    }
}
