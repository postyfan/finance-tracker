package com.postyfan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CombinedTest {
    public static void main(String[] args) {
        String filename = "test-transactions.json";
        
        // Initialize Gson with pretty printing
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        
        // PART 1: Create test transactions
        System.out.println("=== Creating test data ===");
        
        // Create an ArrayList<Transaction>
        TransactionManager transactions = new TransactionManager();
        // Add 2-3 test transactions with different data
        transactions.addTransaction(new Transaction(LocalDateTime.now(), 100, "Electronics","Apple Charger", "Expense"));
        transactions.addTransaction(new Transaction(LocalDateTime.now(), 9.82, "Food","Bubble Tea", "Expense"));
        transactions.addTransaction(new Transaction(LocalDateTime.now(), 432, "Paycheck","Cineplex Paycheck", "Income"));
        // Print how many transactions you created
        List<Transaction> t = transactions.getAllTransactions();
        System.out.println("Number of transactions: " + t.size());

        // PART 2: Save to file
        System.out.println("\n=== Saving to JSON file ===");
        
        // Use try-with-resources with FileWriter
        // Inside try block:
        //   1. Convert transactions list to JSON string using gson.toJson()
        //   2. Write the JSON string to file using writer.write()
        //   3. Print success message
        // Add catch block for IOException
        String json = gson.toJson(t);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
            System.out.println("Success!");
        } catch (IOException e) {
            System.out.println("Error Occured!");
            e.printStackTrace();
        }
        
        
        
        // PART 3: Load from file
        System.out.println("\n=== Loading from JSON file ===");
        
        // Use try-with-resources with BufferedReader
        // Inside try block:
        //   1. Create a StringBuilder to build the full JSON string
        //   2. Loop through file line by line (like in Exercise 2)
        //   3. Append each line to the StringBuilder
        //   4. Convert StringBuilder to String
        //   5. Create TypeToken for ArrayList<Transaction>
        //   6. Use gson.fromJson() to convert JSON string back to List
        //   7. Loop through loaded list and print each transaction
        // Add catch block for IOException
        try (BufferedReader reader = new BufferedReader((new FileReader(filename)))) {
            StringBuilder stb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stb.append(line);
            }
            String json2 = stb.toString();
            Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
            ArrayList<Transaction> arr = gson.fromJson(json2, listType);
            for (int i = 0; i < arr.size(); i++)
                System.out.println(arr.get(i));
        } catch (IOException e) {
            System.out.println("Error Occured! " + e);
        }
        
        System.out.println("\n=== Check your project folder ===");
        System.out.println("Open " + filename + " to see the JSON!");
    }
}