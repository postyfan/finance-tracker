package com.postyfan;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String id;
    private LocalDateTime date;
    private double amount;
    private String category;
    private String description;
    private String type; // "income" or "expense"
    
    // TODO: Add constructor
    public Transaction (String id, LocalDateTime date, double amount, String category, String description, String type) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.type = type;
    }
    // TODO: Add getters and setters
    public void setID(String id) {
        this.id = id;
    }
    public String getID() {
        return this.id;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public LocalDateTime getDate() {
        return this.date;
    }
    public void setAmount(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Amount must be greater than 0");
        this.amount = amount;
    }
    public double getAmount() {
        return this.amount;
    }
    public void setCategory(String c) {
        this.category = c;
    }
    public String getCategory() {
        return this.category;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    // TODO: Add toString() method
    @Override
    public String toString() {
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = this.getDate().format(myFormatDate);
        return "ID: " + this.getID() +
                "\nDate: " + formattedDate + 
                "\nAmount: " + this.getAmount() +
                "\nCategory: " + this.getCategory() + 
                "\nDescription: " + this.getDescription() +
                "\nType: " + this.getType();
    }
    public static void main(String[] args) {
        // Test your class here
        LocalDateTime date = LocalDateTime.now();
        Transaction t = new Transaction("001",date,9.99,"Coffee","Coffee from Blendz SFU","Want");
        System.out.println(t);
    }
}