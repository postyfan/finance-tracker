package com.anton;

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
    }
    
    public List<Transaction> getAllTransactions() {
        // TODO: Return all transactions
    }
    
    public Map<String, Double> getTotalsByCategory() {
        // TODO: Use HashMap to calculate totals per category
        // Hint: Loop through transactions, use category as key
    }
    
    public void displaySummary() {
        // TODO: Print nice summary of spending by category
    }
}