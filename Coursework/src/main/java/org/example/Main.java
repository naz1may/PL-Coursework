package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> productList = FileStorage.loadFromJSON();
        Scanner scan = new Scanner(System.in);

        CRUD operations = new CRUD(productList);
        Report report = new Report();

        displaymenu(scan, operations, report);

        // После выхода из меню — генерируем отчёт
        report.generateReport(productList);
        System.out.println("Report generated in 'report.txt'");
    }

    static void displaymenu(Scanner scan, CRUD operations, Report report) {
        while (true) {
            System.out.println("MENU:");
            System.out.println("1. CRUD operations");
            System.out.println("2. Review report");
            System.out.println("3. Exit");

            System.out.print("Enter command number: ");
            int command = scan.nextInt();
            scan.nextLine();

            switch (command) {
                case 1:
                    CRUDmenu(scan, operations, report);
                    break;

                case 2:
                    report.printReportToConsole(operations.getProducts());

                case 3:
                    return;

                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    static void CRUDmenu(Scanner scan, CRUD operations, Report report) {
        while (true) {
            System.out.println("1. Create product");
            System.out.println("2. Read product list");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("5. Exit");

            System.out.print("Enter operation's number: ");
            int op = scan.nextInt();
            scan.nextLine();

            switch (op) {
                case 1:
                    operations.create(scan);
                    report.logOperation("create");
                    break;
                case 2:
                    operations.read();
                    report.logOperation("read");
                    break;
                case 3:
                    operations.update(scan);
                    report.logOperation("update");
                    break;
                case 4:
                    operations.delete(scan);
                    report.logOperation("delete");
                    break;
                case 5:
                    System.out.println("Exiting to main menu...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
