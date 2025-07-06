package com.drinksales;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerUI extends Application {
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

        Label nameLabel = new Label("Customer Name:");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 14px; -fx-text-fill: #1F2937;");
        TextField nameField = new TextField();
        nameField.setStyle("-fx-background-color: #E5E7EB; -fx-border-color: #D1D5DB; -fx-border-radius: 6px; -fx-padding: 8px; -fx-pref-width: 300px; -fx-text-fill: #1F2937; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5, 0, 0, 1);");

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

        Button orderButton = new Button("Place Order");
        orderButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0; -fx-transition: -fx-background-color 0.3s;");
        orderButton.setOnMouseEntered(e -> orderButton.setStyle("-fx-background-color: #1E3A8A; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));
        orderButton.setOnMouseExited(e -> orderButton.setStyle("-fx-background-color: #1E40AF; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-padding: 8px 20px; -fx-border-radius: 6px; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(30,64,175,0.3), 5, 0, 0, 1); -fx-background-insets: 0;"));

        Label statusLabel = new Label();
        statusLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #1F2937; -fx-padding: 8px;");

        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(branchLabel, 0, 2);
        grid.add(branchCombo, 1, 2);
        grid.add(drinkLabel, 0, 3);
        grid.add(drinkCombo, 1, 3);
        grid.add(quantityLabel, 0, 4);
        grid.add(quantityField, 1, 4);
        grid.add(orderButton, 1, 5);
        grid.add(statusLabel, 1, 6);

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

        orderButton.setOnAction(e -> {
            System.out.println("Order button clicked");
            if (nameField.getText().isEmpty() || branchCombo.getValue() == null || drinkCombo.getValue() == null || quantityField.getText().isEmpty()) {
                statusLabel.setText("Please fill all fields.");
                System.out.println("Validation failed: Missing fields");
                return;
            }
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                if (quantity <= 0) {
                    statusLabel.setText("Quantity must be greater than 0.");
                    System.out.println("Validation failed: Invalid quantity");
                    return;
                }
                System.out.println("Sending PLACE_ORDER command");
                out.println("PLACE_ORDER");
                out.println(nameField.getText());
                out.println(branchCombo.getItems().indexOf(branchCombo.getValue()) + 1); // 1-based index
                out.println(drinkCombo.getItems().indexOf(drinkCombo.getValue()) + 1);  // 1-based index
                out.println(quantity); // Send quantity
                System.out.println("Waiting for server response");
                String response = in.readLine();
                System.out.println("Received server response: " + response);
                statusLabel.setText(response);
                String alerts = in.readLine();
                System.out.println("Received stock alerts: " + alerts);
                if (alerts != null && !alerts.equals("[]")) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Stock Alert");
                    alert.setContentText(alerts.substring(1, alerts.length() - 1).replace("\"", ""));
                    alert.showAndWait();
                }
            } catch (NumberFormatException ex) {
                statusLabel.setText("Quantity must be a number.");
                System.out.println("Exception: Invalid number format - " + ex.getMessage());
            } catch (IOException ex) {
                statusLabel.setText("Error placing order: " + ex.getMessage());
                System.out.println("Exception: IO error - " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(grid, 500, 400);
        primaryStage.setTitle("Customer Order Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}