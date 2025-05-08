package org.example.app;

import org.example.dao.CartDAO;
import org.example.dao.ProductDAO;
import org.example.dao.UserDAO;
import org.example.model.Product;
import org.example.model.User;
import org.example.service.AuthService;
import org.example.service.CRUD;
import org.example.ui.ProductListFrame;
import org.example.util.FileStorage;
import org.example.service.Report;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductListFrame().setVisible(true));

        Scanner scan = new Scanner(System.in);
        Report report = new Report();

        UserDAO userDAO = new UserDAO();
        AuthService authService = new AuthService(userDAO);

        // === Вход или регистрация ===
        User currentUser = null;
        while (true) {
            System.out.println("\nWelcome!");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose option: ");
            String option = scan.nextLine();

            if (option.equals("1")) {
                currentUser = authService.login(scan);
                if (currentUser != null) break;
            } else if (option.equals("2")) {
                authService.register(scan);
            } else {
                System.out.println("Invalid option.");
            }
        }

        // === ROLE-BASED LOGIC ===
        if (currentUser.getRole().equals("admin")) {
            System.out.println("Choose data storage method:");
            System.out.println("1. File (JSON/CSV)");
            System.out.println("2. PostgreSQL Database");
            System.out.print("Enter option: ");
            int storageChoice = scan.nextInt();
            scan.nextLine();

            if (storageChoice == 1) {
                ArrayList<Product> productList = FileStorage.loadFromJSON();
                CRUD fileOperations = new CRUD(productList);
                displayFileMenu(scan, fileOperations, report);
                report.generateReport(productList);
                System.out.println("Report saved to report.txt");
            } else if (storageChoice == 2) {
                ProductDAO dao = new ProductDAO();
                displayAdminDatabaseMenu(scan, dao, userDAO);
            } else {
                System.out.println("Invalid choice.");
            }

        } else {
            // user role: only read and add to cart
            ProductDAO dao = new ProductDAO();
            displayUserMenu(scan, dao, currentUser);
        }
    }


    static void displayFileMenu(Scanner scan, CRUD operations, Report report) {
        while (true) {
            System.out.println("\nFILE MENU:");
            System.out.println("1. Create product");
            System.out.println("2. Read products");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Review report");
            System.out.println("6. Exit");

            System.out.print("Enter option: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1 -> {
                    operations.create(scan);
                    report.logOperation("create");
                }
                case 2 -> {
                    operations.read();
                    report.logOperation("read");
                }
                case 3 -> {
                    operations.update(scan);
                    report.logOperation("update");
                }
                case 4 -> {
                    operations.delete(scan);
                    report.logOperation("delete");
                }
                case 5 -> report.printReportToConsole(operations.getProducts());
                case 6 -> {
                    System.out.println("Exiting file mode...");
                    break;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

    }

    static void displayAdminDatabaseMenu(Scanner scan, ProductDAO dao, UserDAO userDAO) {
        while (true) {
            System.out.println("\nADMIN DATABASE MENU:");
            System.out.println("1. Create product");
            System.out.println("2. Read products");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Show users");
            System.out.println("6. Exit");

            System.out.print("Enter option: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1 -> {
                    Product p = readProductFromUser(scan);
                    dao.saveProduct(p);
                }
                case 2 -> {
                    List<Product> products = dao.loadAllProducts();
                    for (Product p : products) {
                        System.out.println(p);
                    }
                }
                case 3 -> {
                    Product p = readProductFromUser(scan);
                    dao.updateProduct(p);
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    dao.deleteProductById(id);
                }
                case 5 -> userDAO.printAllUsers();
                case 6 -> {
                    System.out.println("Exiting admin menu...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void displayUserMenu(Scanner scan, ProductDAO dao, User currentUser) {
        CartDAO cartDAO = new CartDAO();

        while (true) {
            System.out.println("\nUSER MENU:");
            System.out.println("1. View all products");
            System.out.println("2. Add product to cart");
            System.out.println("3. View cart");
            System.out.println("4. Remove product from cart");
            System.out.println("5. Exit");

            System.out.print("Enter option: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1 -> {
                    List<Product> products = dao.loadAllProducts();
                    for (Product p : products) {
                        System.out.println(p);
                    }
                }
                case 2 -> {
                    System.out.print("Enter product ID to add to cart: ");
                    int prodId = scan.nextInt();
                    scan.nextLine();
                    cartDAO.addToCart(currentUser.getId(), prodId);
                }
                case 3 -> {
                    List<Product> cart = cartDAO.viewCart(currentUser.getId());
                    if (cart.isEmpty()) {
                        System.out.println("Your cart is empty.");
                    } else {
                        System.out.println("Your cart:");
                        for (Product p : cart) {
                            System.out.println(p);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter product ID to remove from cart: ");
                    int prodId = scan.nextInt();
                    scan.nextLine();
                    cartDAO.removeFromCart(currentUser.getId(), prodId);
                }
                case 5 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }


    static Product readProductFromUser(Scanner scan) {
        System.out.print("Enter ID: ");
        int id = scan.nextInt();
        scan.nextLine();

        System.out.print("Enter name: ");
        String name = scan.nextLine();

        System.out.print("Enter category: ");
        String category = scan.nextLine();

        System.out.print("Enter price: ");
        double price = Double.parseDouble(scan.nextLine().replace(",", "."));
        scan.nextLine();

        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setCategory(category);
        p.setPrice(price);
        return p;

    }
}
