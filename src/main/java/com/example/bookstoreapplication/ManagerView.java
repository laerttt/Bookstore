package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.beans.binding.StringExpression;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.Stack;

public class ManagerView extends Application {
    @Override
    public void start(Stage managerStage) throws Exception {

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-font-size: 15px");
        TextField tfTitle = new TextField();

        TextField tfAuthor = new TextField();
        TextField tfCategory = new TextField();
        TextField tfQuantity = new TextField();
        TextField tfISBN = new TextField();
        TextField tfPurchasePrice = new TextField();
        TextField tfSupplier = new TextField();
        TextField tfSellPrice = new TextField();
        TextField tfOrgPrice = new TextField();
        Label lbTitle = new Label("Title:");
        Label lbAuthor = new Label("Author:");
        Label lbCategory = new Label("Category:");
        Label lbQuantity = new Label("Quantity:");
        Label lbISBN = new Label("ISBN:");
        Label lbSupplier = new Label("Supplier:");
        Label lbPurchasePrice = new Label("Purchased Price:");
        Label lbSellPrice = new Label("Selling Price:");
        Label lbOrgPrice = new Label("Original Price:");
        Button btCheck = new Button("Check Stock");
        Button btAdd = new Button("Add");
        Button btLibrarians = new Button("Librarians");
        Button btStat = new Button("Statistics");
        Button btLogOut = new Button("Log Out");
        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.add(btCheck,0,0);
        gridPane.add(lbTitle,0,1);
        gridPane.add(tfTitle,1,1);
        gridPane.add(lbAuthor,0,2);
        gridPane.add(tfAuthor,1,2);
        gridPane.add(lbCategory,0,3);
        gridPane.add(tfCategory,1,3);
        gridPane.add(lbQuantity,0,4);
        gridPane.add(tfQuantity,1,4);
        gridPane.add(lbISBN,0,5);
        gridPane.add(tfISBN,1,5);
        gridPane.add(lbSupplier, 0, 6);
        gridPane.add(tfSupplier, 1, 6);
        gridPane.add(lbPurchasePrice,0,7);
        gridPane.add(tfPurchasePrice,1,7);
        gridPane.add(lbSellPrice,0,8);
        gridPane.add(tfSellPrice,1,8);
        gridPane.add(lbOrgPrice,0,9);
        gridPane.add(tfOrgPrice,1,9);

        gridPane.add(btAdd,1,10);
        gridPane.add(btLibrarians,0,11);
        gridPane.add(btStat,1,11);
        gridPane.add(btLogOut,2,11);

        //add books to dat file
        btAdd.setOnAction(e -> {
            try {
                ManagerControls.addBooks(tfTitle.getText(),tfAuthor.getText(),
                        tfCategory.getText(),Integer.parseInt(tfQuantity.getText()),tfISBN.getText(),
                        tfSupplier.getText(),new Date(),Integer.parseInt(tfPurchasePrice.getText()),Integer.parseInt(tfOrgPrice.getText()),
                                Integer.parseInt(tfSellPrice.getText()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
//        btLogOut.setOnAction(e -> LogInWindow.m       ain());
//        btLogOut.setOnAction(e -> managerStage.close());
        Scene scene = new Scene(gridPane);
        managerStage.setTitle("Bookstore(Manager)");
        managerStage.setScene(scene);
        managerStage.show();
    }

}
