package com.postyfan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class Transaction {
    private String id;
    private LocalDateTime date;
    private final double amount;
    private final String category;
    private final String description;
    private final String type; // "income" or "expense"
    
    // TODO: Add constructor
    public Transaction (LocalDateTime date, double amount, String category, String description, String type) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.type = type;
    }
    // TODO: Add getters and setters
    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }
 
    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public double getAmount() {
        return this.amount;
    }
  
    public String getCategory() {
        return this.category;
    }
  
    public String getDescription() {
        return this.description;
    }
    
    public String getType() {
        return this.type;
    }

    // TODO: Add toString() method
    @Override
    public String toString() {
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = this.getDate().format(myFormatDate);
        return "Type: " + this.getType() +
                "\nTransaction ID: " + this.getID().substring(0,8) +
                "\nDate: " + formattedDate + 
                "\nAmount: " + this.getAmount() +
                "\nCategory: " + this.getCategory() + 
                "\nDescription: " + this.getDescription();
    }
    public static void main(String[] args) {
        // Test your class here
        LocalDateTime date = LocalDateTime.now();
        Transaction t = new Transaction(date,9.99,"Coffee","Coffee from Blendz SFU","Want");
        System.out.println(t);
    }
}