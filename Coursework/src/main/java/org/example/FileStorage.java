package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class FileStorage {

    private static final String CSV_FILE_NAME = "products.csv";
    private static final String JSON_FILE_NAME = "products.json";

    public static void saveToCSV(ArrayList<Product> productList){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_NAME))){
            writer.write("id,name,category,price");
            writer.newLine();
            for(Product p : productList){
                writer.write(p.toCSV());
                writer.newLine();
            }
        } catch(IOException e){
            System.out.println("Writing to CSV file error: " + e.getMessage());
        }
    }

    public static ArrayList<Product> loadFromCSV(){
        ArrayList<Product> products = new ArrayList<>();
        File file = new File(CSV_FILE_NAME);

        if(!file.exists()){
            return products;
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_NAME))){
            String line;
            boolean isFirstLine = true;

            while((line=reader.readLine())!=null){
                if(isFirstLine){
                    isFirstLine=false;
                    continue;
                }
                products.add(Product.fromCSV(line));
            }
        } catch (IOException | IllegalArgumentException e){
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return products;
    }

    public static void saveToJSON(ArrayList<Product> productList){
        ObjectMapper mapper = new ObjectMapper();

        try{
            mapper.writeValue(new File(JSON_FILE_NAME), productList);
        } catch (IOException e){
            System.out.println("Error writing in JSON file: " + e.getMessage());
        }
    }

    public static ArrayList<Product> loadFromJSON(){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Product> products = new ArrayList<>();

        try{
            products = mapper.readValue(new File(JSON_FILE_NAME), mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class));
        } catch(IOException e){
            System.out.println("Error reading JSON file: " + e.getMessage());
        }

        return products;
    }
}
