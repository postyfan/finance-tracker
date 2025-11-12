package com.postyfan;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
    private final List<Transaction> transactions;
    private final FileManager fileManager;
    
    public TransactionManager() {
        fileManager = new FileManager();
        this.transactions = fileManager.loadTransactions();
    }
    
    public void addTransaction(Transaction t) {
        // TODO: Add transaction to list
        transactions.add(t);
        // Saves afer Transaction is added
        fileManager.saveTransactions(transactions);
    }

    /**
     * Deletes a transaction by its ID
     * @param id The transaction ID to delete
     * @return true if deleted, false if not found
     */
    public boolean deleteTransaction(String id) {
        // TODO: Loop through transactions to find matching ID
        // Remove the transaction if found
        // Hint: You can use transactions.removeIf() with a lambda
        // Or use a traditional for loop with iterator
        boolean removed = transactions.removeIf(t -> t.getID().substring(0, 8).equals(id));

        // TODO: If transaction was removed, save to file   
        if (removed)
            fileManager.saveTransactions(transactions);

        // TODO: Return true if found and deleted, false if not found
        return removed;
    
}
    
    public List<Transaction> getAllTransactions() {
        // TODO: Return all transactions    
        return transactions;
    }
    
    /**
     * @return HashMap with totals of each category
     */

    public Map<String, Double> getTotalsByCategory() {
        // Use HashMap to calculate totals per category
        // Loop through transactions, use category as key
        Map<String, Double> categoryTotals = new HashMap<>();

        // Loop through all transactions
        for(Transaction t : transactions) {
            String category = t.getCategory();
            double amount = t.getAmount();

            // Check if category already exists in HashMap
            if (categoryTotals.containsKey(category)) {
                // Add to existing total
                double currentTotal = categoryTotals.get(category);
                categoryTotals.put(category, currentTotal + amount);
            } else {
                categoryTotals.put(category, amount);
            }
        }
        // Return HashMap
        return categoryTotals;
    }

    /**
     * Gathers the total by type - income or expense
     * @return HashMap with totals of each type
     */
    public Map<String, Double> getTotalsByType() {
        // Use HashMap to calculate totals per type
        Map<String, Double> typeTotals = new HashMap<>();

        // Loop through all transactions
        for (Transaction t : transactions) {
            String type = t.getType();
            double amount = t.getAmount();

            // Check if category already exists in HashMap
            if (typeTotals.containsKey(type)) { 
                // Add to existing total
                typeTotals.put(type, typeTotals.get(type) + amount);
            } else {
                // If category is not in HashMap then it will be added
                typeTotals.put(type,amount);
            }
        }
        // Return HashMap
        return typeTotals;
    }
    

    /**
     * Displays a Summary of all Transactions
     * Shows Total for each Categories
     * Shows Total for each Type
     * Shows Overall Net Total
     */
    public void displaySummary() {
        // Check if there Transactions
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
            return;
        }
        // Get Map of categories
        Map<String, Double> categoryTotals = getTotalsByCategory();
        double incomeTotal = 0, expenseTotal = 0;
        // Displays Category Totals
        System.out.println("Category: $ Amount");
        for (Map.Entry<String, Double> t : categoryTotals.entrySet()) {
            System.out.println(t.getKey() + ": $ " + t.getValue());
        }

        // Get Map of types
        Map<String,Double> typeTotals = getTotalsByType();
        System.out.println("\nType: $ Amount");
        for (Map.Entry<String, Double> t : typeTotals.entrySet()){
            System.out.println(t.getKey() + ": $ " + String.format("%.2f", t.getValue()));
            incomeTotal += t.getKey().equalsIgnoreCase("Income") ? t.getValue() : 0.0;
            expenseTotal += t.getKey().equalsIgnoreCase("Expense") ? t.getValue() : 0.0;
        }
        System.out.println("Net Total: $ " + String.format("%.2f",(incomeTotal-expenseTotal)) + "\n");
    }

    /**
     * Retrieves all transactions that belong to a specific category.
     * @param category The category name to filter by (case-insensitive)
     * @return A list of transactions matching the specified category
     */
    public List<Transaction> getTransactionsByCategory(String category) {
        // Create empty list to store matching transactions
        List<Transaction> ret = new ArrayList<>();

        // Iterate through all transactions
        for (Transaction t : transactions) {
            // Compare categories case-insensitively and add if match
            if (t.getCategory().equalsIgnoreCase(category))
                ret.add(t);
        }
        // Return the list
        return ret;
    }

    /**
     * @return size of transactions list
     */
    public int size() {
        return transactions.size();
    }

    /**
     * displays transaction by ID
     * @param String id
     */
    public void printByID(String id) {
        for (Transaction t : transactions) {
            if (t.getID().substring(0,8).equals(id))
                System.out.println(t + "\n");
        }
    }

    /**
     * Updates a transaction by ID
     * @param id The transaction ID to update
     * @param updatedTransaction The new transaction data
     * @return true if updated, false if not found
     */
    public boolean updateTransaction(String id, Transaction updatedTransaction) {
        // TODO: Find the transaction by ID (compare first 8 chars)
        Transaction matchedTransaction = getTransactionByID(id);
        if (matchedTransaction != null) {
            // TODO: If found, replace the old transaction with updated one
            // Replace updatedTransaction param to match id and date of corrected transaction
            updatedTransaction.setID(matchedTransaction.getID());
            updatedTransaction.setDate(matchedTransaction.getDate());
            transactions.set(getIndexByID(id), updatedTransaction);

            // Save to file after updating
            fileManager.saveTransactions(transactions);
            return true;
        }
        return false;
    }

    /**
     * Finds a transaction by ID
     * @param id The shortened transaction ID (8 chars)
     * @return The transaction if found, null if not found
     */
    public Transaction getTransactionByID(String id) {
        // TODO: Loop through transactions
        // TODO: If ID matches (first 8 chars), return that transaction
        // TODO: If loop completes without finding, return null
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getID().substring(0,8).equals(id)) {
                return transactions.get(i);
            }
        }
        return null;
    }

    /**
     * Finds index of transaction by ID
     * @param id shortened transaction ID
     * @return The index if found, -1 if not found
     */
    public int getIndexByID(String id) {
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getID().substring(0,8).equals(id))
                return i;
        }
        return -1;
    }
}