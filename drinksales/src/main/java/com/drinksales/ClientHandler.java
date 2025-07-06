package com.drinksales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final DatabaseManager db;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, DatabaseManager db) {
        this.clientSocket = socket;
        this.db = db;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true); // Auto-flush
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("Connected to client: " + clientSocket.getInetAddress());

            String command;
            while ((command = in.readLine()) != null) {
                System.out.println("Received raw command: '" + command + "'");
                if (command == null || command.trim().isEmpty()) {
                    System.err.println("Empty or null command received, skipping");
                    continue;
                }
                System.out.println("Processing command: " + command);
                switch (command) {
                    case "GET_BRANCHES":
                        List<Branch> branches = db.getBranches();
                        StringBuilder branchJson = new StringBuilder("[");
                        for (int i = 0; i < branches.size(); i++) {
                            branchJson.append("\"").append(branches.get(i).getName()).append("\"");
                            if (i < branches.size() - 1) branchJson.append(",");
                        }
                        branchJson.append("]");
                        out.println(branchJson.toString());
                        break;

                    case "GET_DRINKS":
                        List<Drink> drinks = db.getDrinks();
                        StringBuilder drinkJson = new StringBuilder("[");
                        for (int i = 0; i < drinks.size(); i++) {
                            drinkJson.append("\"").append(drinks.get(i).getName()).append("\"");
                            if (i < drinks.size() - 1) drinkJson.append(",");
                        }
                        drinkJson.append("]");
                        out.println(drinkJson.toString());
                        break;

                    case "PLACE_ORDER":
                        try {
                            String customerName = in.readLine();
                            int branchId = Integer.parseInt(in.readLine());
                            int drinkId = Integer.parseInt(in.readLine());
                            int quantity = Integer.parseInt(in.readLine());

                            int customerId = db.addCustomer(customerName);
                            db.placeOrder(customerId, branchId, drinkId, quantity);

                            out.println("Order placed successfully!");
                            // 🔴 Stock alerts removed here to avoid sending to CustomerUI
                        } catch (SQLException e) {
                            out.println("Error placing order: " + e.getMessage());
                            e.printStackTrace();
                        } catch (NumberFormatException e) {
                            out.println("Invalid data received");
                        } catch (Exception e) {
                            out.println("Unexpected error: " + e.getMessage());
                            e.printStackTrace();
                        }
                        break;

                    case "GET_ORDERS_REPORT":
                        List<String> ordersReport = db.getOrdersReport();
                        out.println("[" + String.join(",", ordersReport) + "]");
                        break;

                    case "GET_SALES_REPORT":
                        List<String> salesReport = db.getSalesReport();
                        out.println("[" + String.join(",", salesReport) + "]");
                        break;

                    case "ADD_STOCK":
                        int stockBranchId = Integer.parseInt(in.readLine());
                        int stockDrinkId = Integer.parseInt(in.readLine());
                        int stockQuantity = Integer.parseInt(in.readLine());
                        db.addStock(stockBranchId, stockDrinkId, stockQuantity);
                        out.println("Stock updated successfully!");
                        break;

                    case "CHECK_STOCK":
                        List<String> stockNotifications = db.checkStockLevels();
                        out.println("[" + String.join(",", stockNotifications) + "]");
                        break;

                    case "GET_ALL_ORDERS":
                        List<String> allOrders = db.getAllOrders();
                        out.println("[" + String.join(",", allOrders) + "]");
                        break;

                    default:
                        out.println("Invalid command");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) out.close();
                if (in != null) in.close();
                clientSocket.close();
                System.out.println("Closed connection for client: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
