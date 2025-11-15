package com.postyfan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class GsonTest {
    public static void main(String[] args) {
        // Initialize Gson with pretty printing
        // Hint: Use GsonBuilder().setPrettyPrinting().create()
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        
        
        // Create a test transaction
        // Use your Transaction constructor with:
        // - LocalDateTime.now() for date
        // - amount: 50.0
        // - category: "Food"
        // - description: "Lunch at cafeteria"
        // - type: "Expense"
        Transaction t = new Transaction(LocalDateTime.now(), 50.0, "Food", "Lunch at cafeteria", "Expense");
        
        
        // Convert single transaction to JSON string
        // Hint: Use gson.toJson(transaction)
        // Store in a String variable
        String json = gson.toJson(t);
        
        
        System.out.println("=== Single Transaction as JSON ===");
        // Print the JSON string
        System.out.println(json);    
    
        // Create a List<Transaction> and add 2 transactions to it
        // Transaction 1: The one you created above
        // Transaction 2: Create a new one with different values
        TransactionManager transactions = new TransactionManager();
        transactions.addTransaction(t);
        transactions.addTransaction(new Transaction(LocalDateTime.now(), 100.0, "Clothing", "Sweater from Hollister", "Expense"));
        
        
        // Convert the entire list to JSON string
        // Hint: gson.toJson() works on lists too!
        String json2 = gson.toJson(transactions.getAllTransactions());
        
        
        System.out.println("\n=== List of Transactions as JSON ===");
        // Print the list JSON
        System.out.println(json2);
        
        // Convert JSON string back to List<Transaction>
        // This is the tricky part! You need TypeToken:
        // Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
        // Then use: gson.fromJson(jsonString, listType)
        Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
        ArrayList<Transaction> loadedTransactions= gson.fromJson(json2, listType);
        
        System.out.println("\n=== Loaded Back from JSON ===");
        // Loop through the loaded list and print each transaction's description and amount
        for (int i = 0; i < loadedTransactions.size(); i++)
            System.out.println(loadedTransactions.get(i));
    }
}