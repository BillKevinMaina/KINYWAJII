package com.drinksales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdminUI extends Application {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private BufferedReader in;
    private PrintWriter out;

    @Override
    public void start(Stage primaryStage) {
        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server at " + SERVER_ADDRESS + ":" + SERVER_PORT);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to connect to server: " + e.getMessage());
            alert.showAndWait();
            return;
        }

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(15);
        grid.setHgap(15);
        grid.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 20px;");

        Label banner = new Label("Kinywaji Enterprises");
        banner.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff; -fx-background-color: linear-gradient(to right, #1E40AF, #E5E7EB); -fx-padding: 10px 20px; -fx-alignment: center;");
        grid.add(banner, 0, 0, 2, 1);

        Button ordersReportButton = new Button("View Orders Report");
        ordersReportButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        ordersReportButton.setOnMouseEntered(e -> ordersReportButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        ordersReportButton.setOnMouseExited(e -> ordersReportButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Button salesReportButton = new Button("View Sales Report");
        salesReportButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        salesReportButton.setOnMouseEntered(e -> salesReportButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        salesReportButton.setOnMouseExited(e -> salesReportButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Button allOrdersButton = new Button("View All Orders");
        allOrdersButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        allOrdersButton.setOnMouseEntered(e -> allOrdersButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        allOrdersButton.setOnMouseExited(e -> allOrdersButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Label branchLabel = new Label("Branch:");
        branchLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        ComboBox<String> branchCombo = new ComboBox<>();
        branchCombo.setStyle("-fx-background-color: #E5E7EB; -fx-border-color: #D1D5DB; -fx-border-radius: 6px; -fx-padding: 8px; -fx-pref-width: 300px; -fx-font-size: 12px; -fx-alignment: center; -fx-text-fill: #1F2937; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 1);");

        Label drinkLabel = new Label("Drink:");
        drinkLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        ComboBox<String> drinkCombo = new ComboBox<>();
        drinkCombo.setStyle("-fx-background-color: #E5E7EB; -fx-border-color: #D1D5DB; -fx-border-radius: 6px; -fx-padding: 8px; -fx-pref-width: 300px; -fx-font-size: 12px; -fx-alignment: center; -fx-text-fill: #1F2937; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 1);");

        Label quantityLabel = new Label("Quantity:");
        quantityLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        TextField quantityField = new TextField();
        quantityField.setStyle("-fx-background-color: #E5E7EB; -fx-border-color: #D1D5DB; -fx-border-radius: 6px; -fx-padding: 8px; -fx-pref-width: 300px; -fx-text-fill: #1F2937; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 1);");

        Button addStockButton = new Button("Add Stock");
        addStockButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        addStockButton.setOnMouseEntered(e -> addStockButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        addStockButton.setOnMouseExited(e -> addStockButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Button checkStockButton = new Button("Check Stock Levels");
        checkStockButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        checkStockButton.setOnMouseEntered(e -> checkStockButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        checkStockButton.setOnMouseExited(e -> checkStockButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #1F2937; -fx-padding: 8px;");

        grid.add(ordersReportButton, 0, 1, 2, 1);
        grid.add(salesReportButton, 0, 2, 2, 1);
        grid.add(allOrdersButton, 0, 3, 2, 1);
        grid.add(branchLabel, 0, 4);
        grid.add(branchCombo, 1, 4);
        grid.add(drinkLabel, 0, 5);
        grid.add(drinkCombo, 1, 5);
        grid.add(quantityLabel, 0, 6);
        grid.add(quantityField, 1, 6);
        grid.add(addStockButton, 1, 7);
        grid.add(checkStockButton, 0, 8, 2, 1);
        grid.add(statusLabel, 0, 9, 2, 1);

        try {
            System.out.println("Sending GET_BRANCHES");
            out.println("GET_BRANCHES");
            String branchResponse = in.readLine();
            System.out.println("Received branches: " + branchResponse);
            if (branchResponse != null && !branchResponse.trim().isEmpty() && branchResponse.startsWith("[")) {
                branchResponse = branchResponse.substring(1, branchResponse.length() - 1);
                String[] branches = branchResponse.split("\",\"");
                for (String branch : branches) {
                    branchCombo.getItems().add(branch.replace("\"", "").trim());
                }
            } else {
                System.out.println("No valid branch data received");
            }

            System.out.println("Sending GET_DRINKS");
            out.println("GET_DRINKS");
            String drinkResponse = in.readLine();
            System.out.println("Received drinks: " + drinkResponse);
            if (drinkResponse != null && !drinkResponse.trim().isEmpty() && drinkResponse.startsWith("[")) {
                drinkResponse = drinkResponse.substring(1, drinkResponse.length() - 1);
                String[] drinks = drinkResponse.split("\",\"");
                for (String drink : drinks) {
                    drinkCombo.getItems().add(drink.replace("\"", "").trim());
                }
            } else {
                System.out.println("No valid drink data received");
            }
        } catch (IOException e) {
            e.printStackTrace();
            statusLabel.setText("Error fetching branches or drinks: " + e.getMessage());
        }

ordersReportButton.setOnAction(e -> {
    try {
        out.println("GET_ORDERS_REPORT");
        String report = in.readLine();
        System.out.println("Received orders report: " + report); // For debugging
        if (report != null && !report.trim().isEmpty() && report.startsWith("[")) {
            // Remove the square brackets
            String content = report.substring(1, report.length() - 1);
            // Split by comma to separate orders
            String[] orders = content.split(",");
            // Build the formatted string with each order on a new line
            StringBuilder formattedOrders = new StringBuilder();
            for (String order : orders) {
                formattedOrders.append(order.trim()).append("\n");
            }
            // Create and display the TextArea
            TextArea textArea = new TextArea(formattedOrders.toString());
            textArea.setEditable(false);
            Stage reportStage = new Stage();
            reportStage.setScene(new Scene(textArea, 400, 300));
            reportStage.setTitle("Orders Report");
            reportStage.show();
        } else {
            statusLabel.setText("No orders found.");
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        statusLabel.setText("Error fetching orders report: " + ex.getMessage());
    }
});
        salesReportButton.setOnAction(e -> {
            try {
                out.println("GET_SALES_REPORT");
                String report = in.readLine();
                TextArea textArea = new TextArea(report.substring(1, report.length() - 1).replace("\"", ""));
                textArea.setEditable(false);
                Stage reportStage = new Stage();
                reportStage.setScene(new Scene(textArea, 400, 300));
                reportStage.setTitle("Sales Report");
                reportStage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                statusLabel.setText("Error fetching sales report: " + ex.getMessage());
            }
        });

        allOrdersButton.setOnAction(e -> {
            try {
                out.println("GET_ALL_ORDERS");
                String allOrdersResponse = in.readLine();
                System.out.println("Received all orders: " + allOrdersResponse);
                if (allOrdersResponse != null && !allOrdersResponse.trim().isEmpty() && allOrdersResponse.startsWith("[")) {
                    // Remove the square brackets
                    allOrdersResponse = allOrdersResponse.substring(1, allOrdersResponse.length() - 1);
                    // Split the response into individual orders
                    String[] orders = allOrdersResponse.split("\",\"");
                    // Format each order with a newline
                    StringBuilder formattedOrders = new StringBuilder();
                    for (String order : orders) {
                        formattedOrders.append(order.replace("\"", "").trim()).append("\n");
                    }
                    // Display in TextArea
                    TextArea textArea = new TextArea(formattedOrders.toString());
                    textArea.setEditable(false);
                    Stage ordersStage = new Stage();
                    ordersStage.setScene(new Scene(textArea, 400, 400));
                    ordersStage.setTitle("All Orders");
                    ordersStage.show();
                } else {
                    statusLabel.setText("No orders found.");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                statusLabel.setText("Error fetching all orders: " + ex.getMessage());
            }
        });

        addStockButton.setOnAction(e -> {
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (branchCombo.getValue() == null || drinkCombo.getValue() == null || quantity <= 0) {
                    statusLabel.setText("Please select branch, drink, and valid quantity.");
                    return;
                }
                out.println("ADD_STOCK");
                out.println(branchCombo.getItems().indexOf(branchCombo.getValue()) + 1);
                out.println(drinkCombo.getItems().indexOf(drinkCombo.getValue()) + 1);
                out.println(quantity);
                statusLabel.setText(in.readLine());
            } catch (NumberFormatException ex) {
                statusLabel.setText("Quantity must be a number.");
            } catch (IOException ex) {
                ex.printStackTrace();
                statusLabel.setText("Error adding stock: " + ex.getMessage());
            }
        });

        checkStockButton.setOnAction(e -> {
            try {
                out.println("CHECK_STOCK");
                String alerts = in.readLine();
                if (alerts.equals("[]")) {
                    statusLabel.setText("No stock alerts.");
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Stock Alerts");
                    alert.setContentText(alerts.substring(1, alerts.length() - 1).replace("\"", ""));
                    alert.showAndWait();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                statusLabel.setText("Error checking stock: " + ex.getMessage());
            }
        });

        Scene scene = new Scene(grid, 550, 500);
        primaryStage.setTitle("Admin Control Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}