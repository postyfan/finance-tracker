package com.postyfan;

// src/main/java/com/financetracker/TransactionManager.java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionManager {
    private List<Transaction> transactions;
    
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
        // TODO: Use HashMap to calculate totals per category
        // Hint: Loop through transactions, use category as key
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
        // TODO: Print nice summary of spending by category
    }
}