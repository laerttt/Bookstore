package com.example.bookstoreapplication.Views;
import com.example.bookstoreapplication.Controls.BillControls;
import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Controls.LogInControls;
import com.example.bookstoreapplication.Models.*;
import com.example.bookstoreapplication.NoHeader.NoHeader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import static com.example.bookstoreapplication.Controls.LibrarianControlls.*;


public class LibrarianStage extends Application {

    static ArrayList<Book> SearchBooks = new ArrayList<Book>();

    public static ArrayList<Book> BillBooks = new ArrayList<>();

    static Bill MainBill;
    private int total = 0;

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        //panes
        GridPane mainGrid = new GridPane();
        mainGrid.setStyle("-fx-font-size: 15px;");
        mainGrid.setPadding(new Insets(5, 5, 5, 5));

        GridPane tableGrid = new GridPane();
        GridPane topGrid = new GridPane();
        GridPane botGrid = new GridPane();

        //scene
        Scene scene = new Scene(mainGrid);

        // Table creation
        TableView BooksTable = new TableView<>();
        TableColumn<Book, String> column1 = new TableColumn<Book, String>("Title");
        column1.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        TableColumn<Book, String> column2 = new TableColumn<Book, String>("Author");
        column2.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Integer> column3 = new TableColumn<Book, Integer>("Selling Price");
        column3.setCellValueFactory(new PropertyValueFactory<Book, Integer>("sellingPrice"));
        TableColumn<Book, Integer> colstock = new TableColumn<>("Stock");
        colstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Book, String> Column4 = new TableColumn<Book, String>("ISBN");
        Column4.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        BooksTable.getColumns().addAll(column1, column2, column3,colstock, Column4);

        TableView BillsTable = new TableView<>();
        TableColumn<Book, String> billTitle = new TableColumn<Book, String>("Title");
        billTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        TableColumn<Book, String> billAuthor = new TableColumn<Book, String>("Author");
        billAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Integer> billPrice = new TableColumn<Book, Integer>("Selling Price");
        billPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("sellingPrice"));
        TableColumn<Book, String> billISBN = new TableColumn<Book, String>("ISBN");
        billISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        BillsTable.getColumns().addAll(billTitle, billAuthor, billPrice, billISBN);
        ArrayList<Book> books = BookControls.getBooks();
        for (Book book : books) {
            BooksTable.getItems().add(book);
        }
        //buttons
        Button btAdd = new Button("Add");
        btAdd.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
        Button btSearch = new Button("Search");
        Button btPrint = new Button("Print");
        Button ClearBill = new Button("Clear Bill");
        Button ClearBooks = new Button("Clear Book Search");
        Button btRemove = new Button("Remove");
        btRemove.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btLogOut = new Button("Log Out");
        btLogOut.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btMangerMode = new Button("Manager Mode");
        btMangerMode.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        //textFields
        TextField tfSearchBar = new TextField();
        tfSearchBar.setPromptText("\t\t\tSearch by Title / Author / Category / Supplier / ISBN");
        tfSearchBar.setPrefWidth(500);

        //labels
        Label lbBooks = new Label("Book search result");
        Label lbBills = new Label("Bill total");
        Label lbTotal = new Label("Total: 0");
        lbTotal.setAlignment(Pos.CENTER);

        //actions
        btPrint.setOnAction(O -> {
            try {
                printBill(BillsTable);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            BooksTable.getItems().clear();
            try {
                BooksTable.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            lbTotal.setText("Total: 0");
        });
        btAdd.setOnAction(e -> {
            if ((Book) BooksTable.getSelectionModel().getSelectedItem() != null && ((Book) BooksTable.getSelectionModel().getSelectedItem()).getStock()>0) {
                BillBooks.add((Book) BooksTable.getSelectionModel().getSelectedItem());
                BillsTable.getItems().clear();
                BillsTable.getItems().addAll(BillBooks);
            }
            else{
                Stage w = warning();
                w.show();
            }
            try {
                lbTotal.setText("Total: " + String.valueOf(bookTotal()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btSearch.setOnAction(e -> {

            BooksTable.getItems().clear();
            try {
                SearchBooks = searchBt(tfSearchBar);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for (Book book : SearchBooks) {
                BooksTable.getItems().add(book);
            }
            SearchBooks.clear();
        });
        ClearBill.setOnAction(e -> {
            BillsTable.getItems().clear();
            BillBooks.clear();
            try {
                lbTotal.setText("Total: " + String.valueOf(bookTotal()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        ClearBooks.setOnAction(e -> {
            BooksTable.getItems().clear();
            SearchBooks.clear();
            for (Book book : books) {
                BooksTable.getItems().add(book);
            }
        });
        tfSearchBar.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                BooksTable.getItems().clear();
                try {
                    SearchBooks = searchBt(tfSearchBar);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (Book book : SearchBooks) {
                    BooksTable.getItems().add(book);
                }
                SearchBooks.clear();
            }
        });
        BooksTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Stage x = bookoInfo((Book) BooksTable.getSelectionModel().getSelectedItem());
                x.show();
            }
        });
        BillsTable.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Stage x = bookoInfo((Book) BooksTable.getSelectionModel().getSelectedItem());
                x.show();
            }
        });
        btRemove.setOnAction(e -> {
            if ((BillsTable.getSelectionModel().getSelectedItem()) != null) {
                for (Book book : BillBooks) {
                    if (book.toString().contentEquals((BillsTable.getSelectionModel().getSelectedItem()).toString())) {
                        BillBooks.remove(book);
                        break;
                    }
                }
                if (BillBooks.size() != 0) {
                    BillsTable.getItems().clear();
                    for (Book book : BillBooks)
                        BillsTable.getItems().add(book);
                    try {
                        lbTotal.setText("Total: " + String.valueOf(bookTotal()));
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    BillsTable.getItems().clear();
                    try {
                        lbTotal.setText("Total: " + String.valueOf(bookTotal()));
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        btLogOut.setOnAction(e -> {
            LogInWindow L = new LogInWindow();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.close();
        });
        btMangerMode.setOnAction(e->{
            ManagerView L = new ManagerView();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            primaryStage.close();
        });


        //style
        BooksTable.setPrefWidth(500);
        BooksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        BooksTable.setPlaceholder(new Label("No search has been made yet"));

        BillsTable.setPrefWidth(500);
        BillsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        BillsTable.setPlaceholder(new Label("No bill to display"));

        mainGrid.add(tfSearchBar, 0, 0);
        mainGrid.add(btSearch, 1, 0);
        if(LogInControls.checkAccess())
            mainGrid.add(btMangerMode, 1, 0);
        mainGrid.add(lbBooks, 0, 1);
        mainGrid.add(btAdd, 0, 1);
        mainGrid.add(btRemove, 1, 1);
        mainGrid.add(lbBills, 1, 1);
        mainGrid.add(btPrint, 1, 1);
        mainGrid.add(BooksTable, 0, 2);
        mainGrid.add(BillsTable, 1, 2);
        mainGrid.add(ClearBooks, 0, 3);
        mainGrid.add(ClearBill, 1, 3);
        mainGrid.add(btLogOut, 1, 3);
        mainGrid.add(lbTotal, 1, 3);
        GridPane.setHalignment(btMangerMode, HPos.RIGHT);
        GridPane.setHalignment(btLogOut, HPos.RIGHT);
        GridPane.setHalignment(lbTotal, HPos.LEFT);
        GridPane.setHalignment(lbBooks, HPos.CENTER);
        GridPane.setHalignment(btAdd, HPos.RIGHT);
        GridPane.setHalignment(lbBills, HPos.CENTER);
        GridPane.setHalignment(btPrint, HPos.RIGHT);
        GridPane.setHalignment(ClearBooks, HPos.CENTER);
        GridPane.setHalignment(ClearBill, HPos.CENTER);
        mainGrid.setVgap(5);
        mainGrid.setHgap(5);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static ArrayList<Book> searchBt(TextField tfSearchBar) throws IOException, ClassNotFoundException {
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Book A = (Book) input.readObject();
                if ((Pattern.compile(tfSearchBar.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())) {
                    SearchBooks.add(A);

                }
            }
            return SearchBooks;
        }
    }

    public void printBill(TableView BillsTable) throws IOException, ClassNotFoundException {
        if (((new File("src/main/resources/Bills.dat")).exists())) {
            try (NoHeader Output = new NoHeader(new FileOutputStream("src/main/resources/Bills.dat", true));
            ) {
                Bill A = new Bill(BillBooks,new Date(),getCurrentLib() , bookTotal());
                A.setBillNumber(BillControls.billCounter());
                Output.writeObject(A);
                BillsTable.getItems().clear();
                for (Book i : BillBooks) {
                    i.addSold(1);
                    BookControls.removeBook(i);
                }
                BillBooks.clear();
            }
        }
        else {
            try (FileOutputStream fOutput = new FileOutputStream("src/main/resources/Bills.dat");
                 ObjectOutputStream Output = new ObjectOutputStream(fOutput);
            ) {
                Bill A = new Bill(BillBooks,new Date(),getCurrentLib(), bookTotal());
                A.setBillNumber(BillControls.billCounter());
                Output.writeObject(A);
                BillsTable.getItems().clear();
                BillBooks.clear();

            }
        }
    }

    static int bookTotal() throws IOException, ClassNotFoundException {
        int totalPrice = 0;
        for (int i = 0; i < BillBooks.size(); i++) {
            Book A = BillBooks.get(i);
            totalPrice = totalPrice + A.getSellingPrice();
        }
        return totalPrice;
    }

    public Stage bookoInfo(Book book) {
        //stage
        Stage stage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label info = new Label(book.getBookProperties());

        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 15px;");
        bPane.setCenter(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(e -> stage.close());

        stage.setTitle("Book Info");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        return stage;
    }
    public Stage warning(){
        //stage
        Stage stage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane,200,150);

        //messageLabel
        Text info = new Text("No book selected!\nor\nBook out of stock!");
        info.setStyle("-fx-font-size: 16px;");
        info.setTextAlignment(TextAlignment.CENTER);
        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 15px;");
        bPane.setCenter(info);
        BorderPane.setAlignment(info,Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close,Pos.BOTTOM_RIGHT);

        //actions
        close.setOnAction(e -> stage.close());

        stage.setTitle("Warning!");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        return  stage;
    }
}









