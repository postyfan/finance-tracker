package com.postyfan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileQuickTest {
    public static void main(String[] args) {
        // Test writing
        try (FileWriter writer = new FileWriter("hello.txt")) {
            for (int i = 0; i < 10; i++)
                writer.write("Bubb number " + (i+1) + '\n');
            System.out.println("✓ File written");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        // Test reading
        try (BufferedReader reader = new BufferedReader(new FileReader("hello.txt"))) {
            String content;
            while ((content = reader.readLine()) != null) {
                System.out.println("✓ File read: " + content);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}