package com.example.bookstoreapplication.Views;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Models.Book;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class ManagerView extends Application {
    private Object temp;
    @Override
    public void start(Stage managerStage) throws Exception {
        //stages
        Stage lowStockStage = new Stage();

        //panes
        GridPane mainGridPane = new GridPane();
        mainGridPane.setAlignment(Pos.CENTER);
        mainGridPane.setHgap(20);

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);
        gridPane.setHgap(2);
        gridPane.setStyle("-fx-font-size: 15px;");

        VBox rightSearchTView = new VBox();
        HBox search = new HBox();
        BorderPane tableLowPane = new BorderPane();
        HBox lowHBox = new HBox();

        //scenes
        Scene scene = new Scene(mainGridPane );
        Scene lowStockScene = new Scene(tableLowPane, 1000,500);

        //tableview
        TableView<Book> tableView = new TableView<>();
        TableView<Book> bookTableView = new TableView<>();

        //lowStock tableView
        TableColumn<Book, String> colTitle = new TableColumn<Book, String>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        TableColumn<Book, String> colAuthor = new TableColumn<Book, String>("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        TableColumn<Book, Integer> colStock = new TableColumn<Book, Integer>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<Book, Integer>("stock"));
        TableColumn<Book, String> colISBN = new TableColumn<Book, String>("ISBN");
        colISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("iSBN"));
        TableColumn<Book, Integer> colPrice = new TableColumn<Book, Integer>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("purchasedPrice"));
        tableView.getColumns().addAll(colTitle,colAuthor,colStock,colISBN,colPrice);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //book tableView
        TableColumn<Book, String> bcolTitle = new TableColumn<Book, String>("Title");
        bcolTitle.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        bcolTitle.setMinWidth(150);
        TableColumn<Book, String> bcolAuthor = new TableColumn<Book, String>("Author");
        bcolAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        bcolAuthor.setMinWidth(100);
        TableColumn<Book, Integer> bcolStock = new TableColumn<Book, Integer>("Stock");
        bcolStock.setCellValueFactory(new PropertyValueFactory<Book, Integer>("stock"));
        bcolStock.setMaxWidth(50);
        bcolStock.setMinWidth(50);
        TableColumn<Book, String> bcolISBN = new TableColumn<Book, String>("ISBN");
        bcolISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("iSBN"));
        TableColumn<Book, Integer> bcolPrice = new TableColumn<Book, Integer>("Price");
        bcolPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("purchasedPrice"));
        TableColumn<Book, Integer> bcolSellPrice = new TableColumn<Book, Integer>("Selling Price");
        bcolSellPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("sellingPrice"));
        TableColumn<Book, Integer> bcolOrgPrice = new TableColumn<Book, Integer>("Original Price");
        bcolOrgPrice.setCellValueFactory(new PropertyValueFactory<Book, Integer>("originalPrice"));
        bookTableView.getColumns().addAll(bcolTitle, bcolAuthor, bcolISBN, bcolStock, bcolSellPrice, bcolPrice, bcolOrgPrice);
        bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTableView.setMinWidth(1000);
        ArrayList<Book> books = BookControls.getBooks();
        for(Book book : books){
            bookTableView.getItems().add(book);
        }

        //mainTextFields
        TextField tfTitle = new TextField();
            tfTitle.setPromptText("Book Title");
        TextField tfAuthor = new TextField();
            tfAuthor.setPromptText("Author Name");
        TextField tfCategory = new TextField();
            tfCategory.setPromptText("Book Category");
        TextField tfQuantity = new TextField();
            tfQuantity.setPromptText("Quantity");
        TextField tfISBN = new TextField();
            tfISBN.setPromptText("Book ISBN");
        TextField tfPurchasePrice = new TextField();
            tfPurchasePrice.setPromptText("Purchased Price");
        TextField tfSupplier = new TextField();
            tfSupplier.setPromptText("Supplier Name");
        TextField tfSellPrice = new TextField();
            tfSellPrice.setPromptText("Bookstore Selling Price");
        TextField tfOrgPrice = new TextField();
            tfOrgPrice.setPromptText("Original Book Price");
        TextField searchBook = new TextField("Search");
            searchBook.setPromptText("Enter Title/ISBN");

        //lowStock textFields
        TextField tfLowQuan = new TextField();
            tfLowQuan.setPromptText("Quantity");

        //mainLabels
        Label lbTitle = new Label("Title:");
        Label lbAuthor = new Label("Author:");
        Label lbCategory = new Label("Category:");
        Label lbQuantity = new Label("Quantity:");
        Label lbISBN = new Label("ISBN:");
        Label lbSupplier = new Label("Supplier:");
        Label lbPurchasePrice = new Label("Purchased Price:");
        Label lbSellPrice = new Label("Selling Price:");
        Label lbOrgPrice = new Label("Original Price:");

        //lowStock labels
        Label lbLowQuan = new Label("Quantity:");

        //mainButtons
        Button btCheck = new Button("Check Stock");
            btCheck.setStyle("-fx-background-color: orange;-fx-text-fill: white;");

        Button btAdd = new Button("Add");
            btAdd.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
            GridPane.setHalignment(btAdd, HPos.RIGHT);

        Button btLibrarians = new Button("Librarians");

        Button btStat = new Button("Statistics");

        Button btLogOut = new Button("Log Out");
            btLogOut.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
        GridPane.setHalignment(btLogOut, HPos.RIGHT);

        Button titleSearch = new Button("Search");
        Button ISBNSearch = new Button("ISBN Search");

        //lowStock buttons
        Button btAddStock = new Button("Add Stock") ;
            btAddStock.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");

        Button btLowClose = new Button("Close");
            btLowClose.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");

        //arrangements
        mainGridPane.setPadding(new Insets(10,10,10,10));
        mainGridPane.add(gridPane,0,0);
        mainGridPane.add(rightSearchTView,1,0);
        rightSearchTView.getChildren().addAll(search,bookTableView);
        search.getChildren().addAll(searchBook,titleSearch,ISBNSearch);
        search.setSpacing(5);

        //addBooks
        gridPane.setPadding(new Insets(0, 0, 0, 0));
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
        gridPane.add(btLogOut,1,11);

        //lowStock tableView arr
        tableLowPane.setCenter(tableView);
        tableLowPane.setBottom(lowHBox);
        tableLowPane.setPadding(new Insets(0,0,5,0));
        lowHBox.getChildren().addAll(lbLowQuan,tfLowQuan,btAddStock,btLowClose);
        lowHBox.setSpacing(2);
        lowHBox.setAlignment(Pos.CENTER);

        //mainActions
        btAdd.setOnAction(e -> {
            try {
                BookControls.addBooks(tfTitle.getText(),tfAuthor.getText(),
                        tfCategory.getText(),Integer.parseInt(tfQuantity.getText()),tfISBN.getText(),
                        tfSupplier.getText(),new Date(),Integer.parseInt(tfPurchasePrice.getText()),Integer.parseInt(tfOrgPrice.getText()),
                                Integer.parseInt(tfSellPrice.getText()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tfTitle.clear();
            tfAuthor.clear();
            tfCategory.clear();
            tfQuantity.clear();
            tfISBN.clear();
            tfSupplier.clear();
            tfPurchasePrice.clear();
            tfSellPrice.clear();
            tfOrgPrice.clear();
            bookTableView.getItems().clear();
//            ArrayList<Book> books = BookControls.getBooks();
            try {
                bookTableView.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btCheck.setOnAction(e -> {
            ArrayList<Book> lowBooks;
            try {
                lowBooks = BookControls.getLowStock();
                System.out.println(lowBooks);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for(Book book : lowBooks){
                tableView.getItems().add(book);
            }
            lowStockStage.show();
        });

        //lowStockAActions
        btLowClose.setOnAction(e ->  { tableView.getItems().clear();
            tfLowQuan.clear();
            lowStockStage.close();});
        btAddStock.setOnAction(e -> { System.out.println(((Book) tableView.getSelectionModel().getSelectedItem()).getISBN()+"\n"+Integer.parseInt(tfLowQuan.getText()));
            try {
                BookControls.addStock(((Book) tableView.getSelectionModel().getSelectedItem()).getISBN(), Integer.parseInt(tfLowQuan.getText()));
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tfLowQuan.clear();
            tableView.getItems().clear();
            try {
                tableView.getItems().addAll(BookControls.getLowStock());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });

        lowStockStage.initOwner(managerStage);
        lowStockStage.initModality(Modality.APPLICATION_MODAL);
        lowStockStage.setTitle("Low Stock Books");
        lowStockStage.setScene(lowStockScene);
        lowStockStage.setMinWidth(400);
        managerStage.setResizable(false);
        managerStage.setTitle("Bookstore(Manager)");
        managerStage.setScene(scene);
        managerStage.show();
    }

}
