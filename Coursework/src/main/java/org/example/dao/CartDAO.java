package org.example.dao;

import org.example.model.Product;
import org.example.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public void addToCart(int userId, int productId) {
        String sql = "INSERT INTO cart_items (user_id, product_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
            System.out.println("Product added to cart.");
        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        }
    }

    public void removeFromCart(int userId, int productId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ? AND product_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Product removed from cart.");
            } else {
                System.out.println("Product not found in cart.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing from cart: " + e.getMessage());
        }
    }

    public List<Product> viewCart(int userId) {
        List<Product> cart = new ArrayList<>();
        String sql = "SELECT p.id, p.name, p.category, p.price FROM products p " +
                "JOIN cart_items c ON p.id = c.product_id WHERE c.user_id = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getDouble("price"));
                cart.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error viewing cart: " + e.getMessage());
        }
        return cart;
    }
}
