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
import java.util.Date;
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
        TableColumn<Book, String> column1 = new TableColumn<Book, String>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
       TableColumn<Book, String> column2 = new TableColumn<Book, String>("Author");
        column2.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Integer> column3 = new TableColumn<Book, Integer>("Price");
       column3.setCellValueFactory(new PropertyValueFactory<Book, Integer>("sellingPrice"));
       BooksTable.getColumns().addAll(column1,column2,column3);
        for(Book book : Books) {
            BooksTable.getItems().add(book);
        }

        TableColumn<Book, String> billTitle = new TableColumn<Book, String>("Title");
        billTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        TableColumn<Book, String> billAuthor = new TableColumn<Book, String>("Author");
        billAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        TableColumn<Book, Integer> billPrice = new TableColumn<Book, Integer>("Selling Price");
        billPrice.setCellValueFactory(new PropertyValueFactory<Book,Integer>("sellingPrice"));
        BillsTable.getColumns().addAll(billTitle,billAuthor,billPrice);

        AddToBill.setOnAction(e->{
            ArrayList<Book> books = new ArrayList<>();
            books.add((Book)BooksTable.getSelectionModel().getSelectedItem());
            BillsTable.getItems().clear();
            for(Book book : books)
                BillsTable.getItems().add(book);
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
            Books.add(new Book("Laert Byca","bIg Gay", "ok",5,"23409587","s", new Date(), 3245,345,345));
            launch();
    }


    }









