package com.postyfan;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final TransactionManager manager = new TransactionManager();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("Welcome to Finance Tracker!\n");
        
        boolean running = true;
        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.println();
            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    deleteTransaction();
                    break;
                case 3:
                    editTransaction();
                    break;
                case 4:
                    viewTransactions();
                    break;
                case 5:
                    showSummary();
                    break;
                case 6:
                    viewTransactionsByCategory();
                    break;
                case 7:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private static void showMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. Add Transaction");
        System.out.println("2. Delete Transaction");
        System.out.println("3. Edit Transaction");
        System.out.println("4. View All Transactions");
        System.out.println("5. View Summary");
        System.out.println("6. View Transactions by Category");
        System.out.println("7. Exit");
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
    }
    
    private static void viewTransactions() {
        // TODO: Display all transactions nicely
        List<Transaction> t = manager.getAllTransactions();
        if (t.isEmpty()) {
            System.out.println("No Transactions");
            return;
        }
        for (Transaction e : t) {
            System.out.println(e + "\n");
        }
    }
    
    private static void showSummary() {
        // TODO: Show summary by category
        manager.displaySummary();
    }

    private static void viewTransactionsByCategory() {
        // Get List<Transaction> of all transactions
        List<Transaction> all = manager.getAllTransactions();
        if (all.isEmpty()) {
            System.out.println("No Transactions");
            return;
        }
        // Make HashMap to store list of transactions per category
        // HashMap @param String -> Category
        //         @param ArrayList<Transaction> -> List of Transactions under category
        HashMap<String,ArrayList<Transaction>> categories = new HashMap<>();
        // Loop though List of Transactions and add to HashMap
        for (Transaction t : all) {
            // Gets category of transaction object and assigns to String ctg
            String ctg = t.getCategory();
            // Create list if missing then add
            categories.computeIfAbsent(ctg, k -> new ArrayList<>()).add(t);
        }
        // Print Transactions by each category
        // Iterate over HashMap categories
        for (Map.Entry<String,ArrayList<Transaction>> entry : categories.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            // Iterate over the ArrayList and print out values
            for (Transaction t : entry.getValue())
                System.out.println(t);
            System.out.println("");
        }
        System.out.println();
    }

    private static void deleteTransaction() {
        // TODO: Check if there are any transactions
        // If empty, print message and return
        if (manager.size() == 0) {
            System.out.println("There are no transactions");
            return;
        }
        // TODO: Show all transactions with their IDs
        // Make it easy for user to see which ID to delete
        // Hint: Call viewTransactions() or make a simpler version
        viewTransactions();
        
        System.out.print("\nEnter transaction ID to delete: ");
        // TODO: Get ID from user input
        String choice = scanner.next();
        scanner.nextLine();

        // Make sure user wants to delete this
        System.out.println("\n");
        manager.printByID(choice);
        System.out.print("Are you sure you want to delete this? (yes/no): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("\nDeletion aborted.");
            return;
        }
        // TODO: Call manager.deleteTransaction(id)
        // Store the result (true/false)
        boolean deleted = manager.deleteTransaction(choice);
        
        // TODO: If result is true, print success message
        // If false, print "Transaction not found" message
        if (deleted)
            System.out.println("Successfully Deleted.");
        else   
            System.out.println("Transaction not found.");
        
    }

    private static void editTransaction() {
        // TODO: Check if there are any transactions
        // If empty, print message and return
        if (manager.size() == 0){
            System.out.println("No Transactions.");
            return;
        }
        // TODO: Show all transactions with their IDs
        viewTransactions();

        System.out.print("Enter transaction ID to edit: ");
        // TODO: Get ID from user
        String id = scanner.nextLine();
        
        // TODO: Find the transaction using manager.getTransactionByID(id)
        // TODO: If null, print "Transaction not found" and return
        Transaction currentTransaction = manager.getTransactionByID(id);
        if (currentTransaction == null) {
            System.out.println("Transaction not found.");
            return;
        }
        
        // TODO: Display current transaction details
        System.out.println("\nCurrent transaction:");
        // Show the transaction
        System.out.println(currentTransaction);

        System.out.println("\nEnter new values (press Enter to keep current value):");

        // TODO: Get new type (or keep current)
        System.out.print("Type [" + currentTransaction.getType() + "]: ");
        String newType = scanner.nextLine().trim();
        // If empty, use current value
        if (newType.isEmpty()) {
            System.out.println("Keeping current Type.");
            newType = currentTransaction.getType();
        } else {
            // Validate and format like in addTransaction()
            newType = newType.substring(0,1).toUpperCase() + newType.substring(1).toLowerCase();
            while (!newType.equals("Income") && !newType.equals("Expense")) {
                System.out.print("Please enter only Income or Expense for type: ");
                newType = scanner.nextLine();
                newType = newType.substring(0,1).toUpperCase() + newType.substring(1).toLowerCase();
            }
        }
        
        // TODO: Get new amount (or keep current)
        System.out.print("Amount [" + currentTransaction.getAmount() + "]: ");
        double newAmount;

        String amtString = scanner.nextLine().trim();
        if (amtString.isEmpty()) {
            System.out.println("Keeping current amount.");
            newAmount = currentTransaction.getAmount();
        } else {
            try {   
                newAmount = Double.parseDouble(amtString);
                // Validate Positive amount
                while (newAmount <= 0) {
                    System.out.print("Please enter a valid amount: ");
                    amtString = scanner.nextLine().trim();
                    if (amtString.isEmpty()) {
                        System.out.println("Keeping current amount.");
                        newAmount = currentTransaction.getAmount();
                        break;
                    }
                    newAmount = Double.parseDouble(amtString);
                }
            } catch (NumberFormatException n) {
                System.out.println("Invalid input. Keeping current amount.");
                newAmount = currentTransaction.getAmount();
            }
        }

        // TODO: Get new category (or keep current)
        System.out.print("Category [" + currentTransaction.getCategory() + "]: ");
        String newCategory = scanner.nextLine().trim();
        if (newCategory.isEmpty()){
            newCategory = currentTransaction.getCategory();
            System.out.println("Keeping current Category.");
        } else {
            newCategory = newCategory.substring(0,1).toUpperCase() + newCategory.substring(1).toLowerCase();
        }

        // TODO: Get new description (or keep current)
        System.out.print("Description [" + currentTransaction.getDescription() + "]: ");
        String newDescription = scanner.nextLine().trim();
        if (newDescription.isEmpty()) {
            newDescription = currentTransaction.getDescription();
            System.out.println("Keeping current Description.");
        }

        // TODO: Create new Transaction with updated values
        Transaction updatedTransaction = new Transaction(currentTransaction.getDate(), newAmount, newCategory, newDescription, newType);

        // TODO: Call manager.updateTransaction(id, newTransaction)
        manager.updateTransaction(id, updatedTransaction);

        // TODO: Print success/failure message
        System.out.println("Successfully edited transaction.\n");
    }
}
