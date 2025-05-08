package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.Scanner;

public class AuthService {
    private final UserDAO userDAO;

    public AuthService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User login(Scanner scanner) {
        System.out.println("---- Login ----");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.findByUsernameAndPassword(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            return user;
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }

    public void register(Scanner scanner) {
        System.out.println("---- Register ----");
        System.out.print("Choose username: ");
        String username = scanner.nextLine();

        System.out.print("Choose password: ");
        String password = scanner.nextLine();

        if (userDAO.usernameExists(username)) {
            System.out.println("Username already exists.");
            return;
        }

        userDAO.registerNewUser(username, password);
        System.out.println("Registration successful! You can now log in.");
    }
}
