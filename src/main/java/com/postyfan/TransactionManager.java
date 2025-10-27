package com.postyfan;

// src/main/java/com/financetracker/TransactionManager.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
    private final List<Transaction> transactions;
    
    public TransactionManager() {
        this.transactions = new ArrayList<>();
    }
    
    public void addTransaction(Transaction t) {
        // TODO: Add transaction to list
        transactions.add(t);
    }
    
    public List<Transaction> getAllTransactions() {
        // TODO: Return all transactions    
        return transactions;
    }
    
    /**
     * 
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
    
    public void displaySummary() {
        // Print nice summary of spending by category
        // Get Map of categories
        if (transactions.isEmpty()) {
            System.out.println("No transactions");
            return;
        }
        Map<String, Double> hash = getTotalsByCategory();
        double total = 0;
        System.out.println("Category: $ Totals");
        for (Map.Entry<String, Double> t : hash.entrySet()) {
            System.out.println(t.getKey() + ": $ " + t.getValue());
            total += t.getValue();
        }
        System.out.println("Overall Total: & " + total);
    }
}