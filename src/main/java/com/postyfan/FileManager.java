package com.postyfan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_PATH = "transactions.json";

    private Gson gson;
    
    public FileManager() {
        //  Initialize Gson with pretty printing and LocalDateTime adapter
        // Register LocalDateTimeAdapter like you did in exercises
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).setPrettyPrinting().create();
        
    }
    
    /**
     * Saves the list of transactions to a JSON file
     * @param transactions The list to save
     */
    public void saveTransactions(List<Transaction> transactions) {
        // TODO: Convert transactions list to JSON string
        String json = gson.toJson(transactions);
        
        // TODO: Write JSON string to file using try-with-resources
        // Use FILE_PATH as the filename
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(json);
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
             // TODO: Add catch block for IOException and print error
            System.out.println("Error Occured! " + e);
        }
    }
    
    /**
     * Loads transactions from JSON file
     * @return List of transactions, or empty list if file doesn't exist/error
     */
    public List<Transaction> loadTransactions() {
        // TODO: Check if file exists using File class
        // If file doesn't exist, return new empty ArrayList
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            // TODO: Read file content as String using BufferedReader

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                // Use StringBuilder to build the full JSON string (like in Exercise 3)
                builder.append(line);
            }
            // TODO: Convert JSON string to List<Transaction> using TypeToken
            // TODO: Return the loaded list
            String json = builder.toString();
            Type listType = new TypeToken<ArrayList<Transaction>>(){}.getType();
            ArrayList<Transaction> ret = gson.fromJson(json, listType);
            System.out.println("Transactions saved successfully.");
            return ret;
        } catch (IOException e) {
            // TODO: Add catch block for IOException
            // If error occurs, return empty ArrayList
            System.out.println("Error Occured! " + e);
            return new ArrayList<>();
        }
        
    }
}
