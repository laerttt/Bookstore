package com.example.bookstoreapplication;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.converter.FloatStringConverter;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.bookstoreapplication.LibrarianControlls.*;

public class LibrarianStage extends Application {
    public static   TableView BooksTable = new TableView<>();
    public static   TableView BillsTable = new TableView<>();
    public static ArrayList ExtraBooks = new ArrayList<>();

    Bill MainBill;
    Button AddToBill= new Button("Add");
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
  //
        TableColumn<Book, String> column1 =
               new TableColumn<>("Books");
        column1.setCellValueFactory(
                new PropertyValueFactory<Book, String>("title"));
       TableColumn<Book, String> column2 =
               new TableColumn<>("Author");
        column2.setCellValueFactory(
                new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Double> column3 =
               new TableColumn<>("Price");
       column3.setCellValueFactory(
               new PropertyValueFactory<Book, Double>("sellingPrice"));
       BooksTable.getColumns().add(column1);
       BooksTable.getColumns().add(column2);
       BooksTable.getColumns().add(column3);
Books.add(new Book("Laert Byca","bIG gay", 10));
       for(int i=0;i<Books.size();i++) {
     BooksTable.getItems().add(new Book(getBookTitle(i),getBookAuthor(i),getBookPrice(i)));
     }


        TableColumn<Book, String> Column1 =
                new TableColumn<>("Books");
        column1.setCellValueFactory(
                new PropertyValueFactory<Book, String>("title"));
        TableColumn<Book, String> Column2 =
                new TableColumn<>("Author");
        column2.setCellValueFactory(
                new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Double> Column3 =
                new TableColumn<>("Price");
        column3.setCellValueFactory(
                new PropertyValueFactory<Book, Double>("sellingPrice"));
        BillsTable.getColumns().add(Column1);
        BillsTable.getColumns().add(Column2);
        BillsTable.getColumns().add(Column3);



        BooksTable.setOnMouseClicked(e ->{

                ExtraBooks.add( BooksTable.getSelectionModel().getSelectedItem());
            System.out.print(BooksTable.getSelectionModel().getSelectedItem());
        });




        AddToBill.setOnAction(e -> {
            try {
                BillsTable.getItems().clear();
               Book A = (Book)  BooksTable.getSelectionModel().getSelectedItem();
                MainBill = getBill(ExtraBooks);
                BillsTable.getItems().addAll(A);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        GridPane Pane1= new GridPane();
        Pane1.setVgap(10);
        Pane1.setHgap(10);
        Pane1.setPadding(new Insets(10,10,10,10));
        Pane1.add(BillsTable,0,1);
        Pane1.add(BooksTable,0,0);
        Pane1.add(AddToBill,1,0);
        GridPane.setHalignment(AddToBill, HPos.CENTER);
        Scene scene = new Scene(Pane1);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

        public static void main(String[] args) {
            launch();
    }


    }









