package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.ArrayList;

import static com.example.bookstoreapplication.LibrarianControlls.*;

public class LibrarianStage extends Application {
    @Override
    public void start(Stage primaryStage) {
        TableView BillsTable = new TableView<>();
        TableColumn<Book, String> column1 =
                new TableColumn<>("Books");
        column1.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        TableColumn<Bill, String> column2 =
                new TableColumn<>("Author");
        column2.setCellValueFactory(
                new PropertyValueFactory<>("author"));
        TableColumn<Bill, String> column3 =
                new TableColumn<>("Price");
        column3.setCellValueFactory(
                new PropertyValueFactory<>("sellingPrice"));
        BillsTable.getColumns().add(column1);
        BillsTable.getColumns().add(column2);
        BillsTable.getColumns().add(column3);
        Books.add(new Book("Homo","Homo",69));
        for(int i=0;i<Books.size();i++) {
        BillsTable.getItems().add(Books.get(0));
        }

        TableView.TableViewSelectionModel selectionModel =
                BillsTable.getSelectionModel();




        Button AddToBill= new Button("Add");
        Button RemoveFromBill= new Button("Button");
        Button Print= new Button("Print");



        


            VBox vbox = new VBox(BillsTable);

            Scene scene = new Scene(vbox);

            primaryStage.setScene(scene);

            primaryStage.show();

    }

        public static void main(String[] args) {
            launch();

        }




        }







