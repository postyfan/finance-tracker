package com.postyfan;

import java.io.*;

public class FileIOTest {
    public static void main(String[] args) {
        String filename = "test.txt";
        
        // PART 1: Write to file
        System.out.println("=== Writing to file ===");
        
        // TODO: Use try-with-resources to create a FileWriter
        // Syntax: try (FileWriter writer = new FileWriter(filename)) { }
        // Inside the try block:
        //   - Write 3 lines to the file using writer.write()
        //   - Each line should be different text
        //   - Don't forget to add \n for new lines
        //   - Print success message
        // Add catch block for IOException
        //   - Print error message if exception occurs
        try (FileWriter writer = new FileWriter("file.txt")) {
            for (int i = 0; i < 5; i++) 
                writer.write("Bubb " + (i+1) + '\n');
        } catch (IOException e) {
            System.err.println("An Error Occured!");
            e.printStackTrace();
        }
        
        
        // PART 2: Read from file
        System.out.println("\n=== Reading from file ===");
        
        // TODO: Use try-with-resources to create a BufferedReader
        // Syntax: try (BufferedReader reader = new BufferedReader(new FileReader(filename))) { }
        // Inside the try block:
        //   - Create a loop that reads lines until null
        //   - Hint: while ((line = reader.readLine()) != null)
        //   - Print each line
        // Add catch block for IOException
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error Occured!");
            e.printStackTrace();
        }
        
        
        
        // PART 3: Check file properties
        System.out.println("\n=== File Info ===");
        
        // TODO: Create a File object with the filename
        File file = new File("file.txt");
        // TODO: Check if file exists using .exists()
        boolean fileExists = file.exists();
        // TODO: Get file size using .length()
        long size = file.length();
        // TODO: Print both pieces of information
        System.err.println("File Exists: " + fileExists + "Size: " + size);
        
    }
}