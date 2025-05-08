package org.example.service;

import org.example.model.Product;
import org.example.util.FileStorage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CRUD {
    private ArrayList<Product> productList;

    public CRUD(ArrayList<Product> productList){
        this.productList = productList; // Используем переданный список
    }

    public void create(Scanner scan){
        System.out.println("Enter ID:");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter name of the product:");
        String name = scan.nextLine();

        categoryDisplay();
        System.out.println("Enter category of the product (choose from above): ");
        String category = scan.nextLine();

        System.out.println("Enter price of the product:");
        double price = scan.nextDouble();
        scan.nextLine();

        try{
            //попытка создать объект с валидацией
            Product nProduct = new Product();
            nProduct.setId(id);
            nProduct.setName(name);
            nProduct.setCategory(category);
            nProduct.setPrice(price);

            productList.add(nProduct);

            FileStorage.saveToCSV(productList);
            FileStorage.saveToJSON(productList);
            System.out.println("Product added: " + nProduct);
        } catch (IllegalArgumentException e){
            // Обработка ошибок если данные некорректны
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void read(){
        if(productList.isEmpty()){
            System.out.println("The Product List is empty.");
        }

        for(String c : Product.getValidCategories()){
            System.out.println("Category: " + c);
            boolean hasProduct = false;

            for(Product p : productList){
                if(p.getCategory().equalsIgnoreCase(c)){
                    System.out.println("  - " + p);
                    hasProduct = true;
                }
            }

            if (!hasProduct) {
                System.out.println("  (No products in this category)");
            }

            System.out.println();
        }
    }

    public void update(Scanner scan){
        System.out.println("Enter ID to update:");
        int id = scan.nextInt();
        scan.nextLine();


        for(Product p : productList){
            if(p.getId() == id){
                System.out.println("Enter new name of the product:");
                String newName = scan.nextLine();

                System.out.println("Enter new category of the product:");
                String newCategory = scan.nextLine();

                System.out.println("Enter new price of the product:");
                double newPrice = scan.nextDouble();
                scan.nextLine();

                try{
                    // Попытка обновить данные через сеттеры с валидацией
                    p.setName(newName);
                    p.setCategory(newCategory);
                    p.setPrice(newPrice);

                    FileStorage.saveToCSV(productList);
                    FileStorage.saveToJSON(productList);
                    System.out.println("Product updated: " + p);
                } catch(IllegalArgumentException e) {
                    // Обработка ошибок
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }

        System.out.println("Product with ID " + id + " not found.");
    }

    public void delete(Scanner scan){
        System.out.println("Enter ID of product to delete:");
        int id = scan.nextInt();
        scan.nextLine();

        // Используем итератор для безопасного удаления
        Iterator<Product> iterator = productList.iterator();
        while(iterator.hasNext()){
            Product p = iterator.next();
            if(p.getId()==id){
                iterator.remove();

                FileStorage.saveToCSV(productList);
                FileStorage.saveToJSON(productList);
                System.out.println("Product deleted: " + p);
                return;
            }

            System.out.println("Product with ID " + id + " not found");
        }

//        for(Product p : productList){
//            if(p.getId()==id){
//                productList.remove(p);
//                System.out.println("Product deleted: " + p);
//                return;
//            }
//
//            System.out.println("Product with ID " + id + " not found");
//        }
    }

    private void categoryDisplay(){
        System.out.println("Available categories:");
        for(String c : Product.getValidCategories()){
            System.out.println("- " + c);
        }
    }

    public ArrayList<Product> getProducts() {
        return productList;
    }

}

