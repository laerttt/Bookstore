package com.example.bookstoreapplication.Views;
import com.example.bookstoreapplication.Models.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.example.bookstoreapplication.Controls.LibrarianControlls.*;


public class LibrarianStage extends Application {
    public static   TableView BooksTable = new TableView<>();
    public static   TableView BillsTable = new TableView<>();
    static ArrayList<Book> SearchBooks = new ArrayList<Book>();

    public static TextField SearchBar = new TextField();
    
    public static ArrayList<Book> BillBooks = new ArrayList<>();
    
    static Bill MainBill;
    Button btAdd = new Button("Add");
    Button btSearch= new Button("Search");
    Button btPrint = new Button("Print");
    Button ClearBill = new Button("Clear Bill");
    Button ClearBooks = new Button("Clear Book Search");
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
  // Table creation


        TableColumn<Book, String> column1 = new TableColumn<Book, String>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
       TableColumn<Book, String> column2 = new TableColumn<Book, String>("Author");
        column2.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Integer> column3 = new TableColumn<Book, Integer>("Selling Price");
       column3.setCellValueFactory(new PropertyValueFactory<Book, Integer>("sellingPrice"));
        TableColumn<Book, String> Column4 = new TableColumn<Book, String>("ISBN");
        Column4.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
       BooksTable.getColumns().addAll(column1,column2,column3,Column4);
        btSearch.setOnAction(e->{
            BooksTable.getItems().clear();
            try {
                SearchBooks= searchBt();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for(Book book : SearchBooks) {
                BooksTable.getItems().add(book);
            }

                SearchBooks.clear();


        });

        TableColumn<Book, String> billTitle = new TableColumn<Book, String>("Title");
        billTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        TableColumn<Book, String> billAuthor = new TableColumn<Book, String>("Author");
        billAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        TableColumn<Book, Integer> billPrice = new TableColumn<Book, Integer>("Selling Price");
        billPrice.setCellValueFactory(new PropertyValueFactory<Book,Integer>("sellingPrice"));
        TableColumn<Book, String> billISBN = new TableColumn<Book, String>("ISBN");
        billISBN.setCellValueFactory(new PropertyValueFactory<Book,String>("ISBN"));
        BillsTable.getColumns().addAll(billTitle,billAuthor,billPrice,billISBN);

        btPrint.setOnAction(O->{
            try {
                printBill();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        btAdd.setOnAction(e->{

            BillBooks.add((Book)BooksTable.getSelectionModel().getSelectedItem());
            BillsTable.getItems().clear();
            for(Book book : BillBooks)
                BillsTable.getItems().add(book);
        });

        ClearBill.setOnAction(e->{  BillsTable.getItems().clear(); BillBooks.clear(); });
        ClearBooks.setOnAction(e->{  BooksTable.getItems().clear();
        SearchBooks.clear(); });

      // Button Design
        BillsTable.setPlaceholder(
                new Label("No bill to display"));
        BooksTable.setPlaceholder(
                new Label("No search has been made yet"));

        btAdd. setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");

        SearchBar.setPromptText("Search by book name:");

        // Label Creation and design


        Label BooksLabel= new Label("Book search result");
        Label BillsLabel= new Label("Bill total");
        BillsLabel.setStyle("-fx-text-size:15px;");
        BooksLabel.setStyle("-fx-text-size:15px;");
        // Putting nodes into Pane
        GridPane Pane1= new GridPane();
        Pane1.setVgap(10);
        Pane1.setHgap(10);
        Pane1.setPadding(new Insets(10,10,10,10));
        Pane1.add(ClearBill,1,3);
        Pane1.add(ClearBooks,0,3);
        Pane1.add(BooksLabel,0,1);
        Pane1.add(BillsLabel,1,1);
        Pane1.add(btPrint,1,1);
        Pane1.add(btSearch,1,0);
        Pane1.add(BillsTable,1,2);
        Pane1.add(BooksTable,0,2);
        Pane1.add(btAdd,1,1);
        Pane1.add(SearchBar,0,0);
        Pane1.setStyle("-fx-font-size:15px;");
        GridPane.setHalignment(btSearch, HPos.LEFT);
        GridPane.setHalignment(btAdd, HPos.LEFT);
        GridPane.setHalignment(btPrint, HPos.RIGHT);
        GridPane.setHalignment(BooksLabel, HPos.CENTER);
        GridPane.setHalignment(BillsLabel, HPos.CENTER);
        GridPane.setHalignment(ClearBooks, HPos.CENTER);
        GridPane.setHalignment(ClearBill, HPos.CENTER);
       Pane1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(Pane1,1000,500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static ArrayList<Book> searchBt() throws IOException, ClassNotFoundException {
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ){
            while (fInput.available() > 0) {
                Book A = (Book) input.readObject();
                if ((Pattern.compile(SearchBar.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())){
                    SearchBooks.add(A);
                    System.out.print(A.getTitle());
                }
            }
            return SearchBooks;
        }
    }

    public void printBill() throws IOException, ClassNotFoundException {
        try (FileOutputStream fOutput = new FileOutputStream("Bills.dat");
             ObjectOutputStream Output = new ObjectOutputStream(fOutput);
        ){
            Bill A = getBill(BillBooks,bookTotal());
            Output.writeObject(A);
            BillsTable.getItems().clear();
            BillBooks.clear();
            MainBill= A;
        }
    }
    static int bookTotal() throws IOException, ClassNotFoundException {
        int totalPrice=0;
        for (int i = 0; i <BillBooks.size() ; i++) {
            Book A= BillBooks.get(i);
            totalPrice=totalPrice+A.getSellingPrice();
        }
        return totalPrice;
    }
}








