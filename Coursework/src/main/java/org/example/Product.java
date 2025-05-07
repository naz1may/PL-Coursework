package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

//класс модель, описывает, и за действия(крад) не должен отвечать / хранит информацию о сущностях
public class Product {
    private int id;
    private String name;
    private String category;
    private double price;

    private static Set<Integer> usedIds = new HashSet<>(); //для обеспечения уникальности айди
    private static final Set<String> validCategories = new HashSet<>();

    static {
        validCategories.add("Headwear");
        validCategories.add("Tops");
        validCategories.add("Bottoms");
        validCategories.add("Footwear");
        validCategories.add("Accessories");
    }

    //конструктор
    public Product(int id, String name, String category, double price){
        this.id=id; //без проверки
        this.name=name;
        this.category=category;
        setPrice(price); // проверкой
    }

    public Product(){
        this.id = 0;
        this.name = "";
        this.category = "";
    }

    //геттеры и сеттеры (для изменения полей после создания продукта)

    //геттеры
    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public Double getPrice(){
        return price;
    }

    public static Set<String> getValidCategories() {
        return validCategories;
    }

    //сеттеры с проверками

    public void setId(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if(usedIds.contains(id)){
            throw new IllegalArgumentException("ID must be unique.");
        }
        usedIds.add(id);
        this.id=id;
    }

    public void setName(String name){
        if(name==null || name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name=name;
    }

    public void setCategory(String category){
        if(category==null || category.trim().isEmpty()){
            throw new IllegalArgumentException(("Category cannot be null or empty."));
        }
        if(!validCategories.contains(category)){
            throw new IllegalArgumentException("Invalid category.");
        }
        this.category=category;
    }

    public void setPrice(double newPrice){
        if(newPrice<0){
            throw new IllegalArgumentException("Price can't be negative");
        }
        if(newPrice>1000000){
            throw new IllegalArgumentException("Price can't be more than 1000000");
        }
        this.price=newPrice;
    }

    @Override //переопределение, потому что есть в родительском классе
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', category='" + category + "', price=" + price + "}";
    }

    //файл хэндинг

    public String toCSV(){
        return id + "," + name + "," + category + "," + price;
    }

    public static Product fromCSV(String csvLine){
        String[] parts = csvLine.split(",");
        if(parts.length!=4){
            throw new IllegalArgumentException("Incorrect CSV line: " + csvLine);
        }

        return new Product(
                Integer.parseInt(parts[0]),
                parts[1],
                parts[2],
                Double.parseDouble(parts[3])
        );
    }
}
