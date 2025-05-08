package org.example.service;

import org.example.model.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report {
    private int createCount = 0;
    private int readCount = 0;
    private int updateCount = 0;
    private int deleteCount = 0;

    private List<String> activityLog = new ArrayList<>();

    public void logOperation(String operation){
        switch(operation.toLowerCase()){
            case "create" -> createCount++;
            case "read" -> readCount++;
            case "update" -> updateCount++;
            case "delete" -> deleteCount++;
        }
        activityLog.add(LocalDateTime.now() + " - " + operation.toUpperCase());
    }

    public void generateReport(List<Product> products){
        Map<String, Integer> categoryCounts = new HashMap<>();

        for(Product p : products){
            String category = p.getCategory();
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }

        try(FileWriter writer = new FileWriter("report.txt")){
            writer.write("=== PRODUCT REPORT ===\n");
            writer.write("Total Products: " + products.size() + "\n");
            writer.write("Create Operations: " + createCount + "\n");
            writer.write("Read Operations: " + readCount + "\n");
            writer.write("Update Operations: " + updateCount + "\n");
            writer.write("Delete Operations: " + deleteCount + "\n\n");

            writer.write("Products by Category:\n");
            for(Map.Entry<String, Integer> entry : categoryCounts.entrySet()){
                writer.write(" - " + entry.getKey() + ": " + entry.getValue() + "\n");
            }

            writer.write("\nActivity Log:\n");
            for(String logEntry : activityLog){
                writer.write(logEntry + "\n");
            }

            writer.write("\n=== END OF REPORT ===\n");
        } catch(IOException e){
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    public void printReportToConsole(List<Product> products) {
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Product p : products) {
            String category = p.getCategory();
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }

        System.out.println("\n=== PRODUCT REPORT ===");
        System.out.println("Generated at: " + LocalDateTime.now());
        System.out.println("Total Products: " + products.size());
        System.out.println("Create Operations: " + createCount);
        System.out.println("Read Operations: " + readCount);
        System.out.println("Update Operations: " + updateCount);
        System.out.println("Delete Operations: " + deleteCount);

        System.out.println("\nProducts by Category:");
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("\nActivity Log:");
        for (String log : activityLog) {
            System.out.println(log);
        }
        System.out.println("=== END OF REPORT ===\n");
    }
}
