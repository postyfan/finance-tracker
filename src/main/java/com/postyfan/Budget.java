package com.postyfan;

public class Budget {
    private String category;
    private double limit;
    private double spent;
    
    public Budget(String category, double limit) {
        // TODO: Initialize category and limit
        // TODO: Initialize spent to 0.0
        this.category = category;
        this.limit = limit;
        this.spent = 0.0;
    }
    
    // Getters for category, limit, spent

    public String getCategory() {
        return category;
    }

    public double getLimit() {
        return limit;
    }

    public double getSpent() {
        return spent;
    }
    
    public void setLimit(double limit) {
        // TODO: Update the budget limit
        if (limit <= 0) {
            System.out.println("Invalid input.");
            return;
        }
        this.limit = limit;
    }
    
    public void setSpent(double spent) {
        // TODO: Update the spent amount
        if (spent < 0) {
            System.out.println("Invalid input");
            return;
        }
        this.spent = spent;
    }
    
    public double getRemaining() {
        // TODO: Calculate and return: limit - spent
        return limit - spent;
    }
    
    public double getPercentageUsed() {
        // TODO: Calculate percentage: (spent / limit) * 100
        // Return as double (e.g., 75.5 for 75.5%)
        return (spent/limit) * 100.0;
    }
    
    public boolean isOverBudget() {
        // TODO: Return true if spent > limit, false otherwise
        return spent > limit;
    }
    
    public boolean isNearLimit() {
        // TODO: Return true if spent >= 80% of limit
        // Hint: Use getPercentageUsed() >= 80
        return getPercentageUsed() >= 80;
    }
    
    @Override
    public String toString() {
        // TODO: Return a formatted string showing budget info
        // Example: "Food: $450.00 / $500.00 (90.0%)"
        return getCategory() + ": $" + String.format("%.2f", spent) + " / $" + String.format("%.2f", limit);
    }
}