package com.example.bookstoreapplication.Views;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Controls.LogInControls;
import com.example.bookstoreapplication.Models.Book;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class ManagerView extends Application {
    TableView<Book> bookTableView = new TableView<>();
    @Override
    public void start(Stage managerStage) throws Exception {
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

        //scenes
        Scene scene = new Scene(mainGridPane );

        //tableview
        bookTableView.setPlaceholder(new Label("No books found."));

        //book tableView
        TableColumn<Book, String> bcolTitle = new TableColumn<>("Title");
        bcolTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> bcolAuthor = new TableColumn<>("Author");
        bcolAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book, Integer> bcolStock = new TableColumn<>("Stock");
        bcolStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        bcolStock.setMaxWidth(50);
        bcolStock.setMinWidth(50);
        TableColumn<Book, String> bcolISBN = new TableColumn<>("ISBN");
        bcolISBN.setCellValueFactory(new PropertyValueFactory<>("iSBN"));
        bcolISBN.setMinWidth(100);
        bcolISBN.setMaxWidth(100);
        TableColumn<Book, Integer> bcolPrice = new TableColumn<>("Price");
        bcolPrice.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        bcolPrice.setMinWidth(50);
        bcolPrice.setMaxWidth(50);
        TableColumn<Book, Integer> bcolSellPrice = new TableColumn<>("Selling Price");
        bcolSellPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        bcolSellPrice.setMinWidth(80);
        bcolSellPrice.setMaxWidth(80);
        TableColumn<Book, Integer> bcolOrgPrice = new TableColumn<>("Original Price");
        bcolOrgPrice.setCellValueFactory(new PropertyValueFactory<>("originalPrice"));
        bcolOrgPrice.setMinWidth(100);
        bcolOrgPrice.setMaxWidth(100);
        TableColumn<Book, String> bcolCategory = new TableColumn<>("Category");
        bcolCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn<Book, String> bcolSupplier = new TableColumn<>("Supplier");
        bcolSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        bcolSupplier.setMaxWidth(120);
        bcolSupplier.setMinWidth(120);
        bookTableView.getColumns().addAll(bcolTitle, bcolAuthor, bcolCategory, bcolISBN,bcolSupplier, bcolStock, bcolSellPrice, bcolPrice, bcolOrgPrice);
        bookTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTableView.setMinWidth(1000);
        bookTableView.setMaxHeight(375);

        //tableViewUp
        bookTableView.getItems().addAll(BookControls.getBooks());

        //textFields
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
        TextField tfsearchBook = new TextField();
            tfsearchBook.setPrefWidth(500);
            tfsearchBook.setPromptText("\t\t\t\t\t\t\t Enter Title / Author / ISBN / Supplier");

        //Labels
        Label lbTitle = new Label("*Title:");
        Label lbAuthor = new Label("*Author:");
        Label lbCategory = new Label("*Category:");
        Label lbQuantity = new Label("*Quantity:");
        Label lbISBN = new Label("*ISBN:");
        Label lbSupplier = new Label("Supplier:");
        Label lbPurchasePrice = new Label("*Purchased Price:");
        Label lbSellPrice = new Label("*Selling Price:");
        Label lbOrgPrice = new Label("*Original Price:");

        //texts
        Text text = new Text("* -> Necessary fields");
        text.setFont(Font.font("Verdana", FontWeight.NORMAL, 13));
        text.setUnderline(true);
        text.setFill(Color.DARKRED);

        //buttons
        Button btCheck = new Button("Check Stock");
            btCheck.setStyle("-fx-background-color: #a36917;-fx-text-fill: white;");
        Button btAdd = new Button("Add");
            btAdd.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
            GridPane.setHalignment(btAdd, HPos.RIGHT);
        Button btLibrarians = new Button("Librarians");
        Button btStat = new Button("Statistics");
        Button btLogOut = new Button("Log Out");
            btLogOut.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
            GridPane.setHalignment(btLogOut, HPos.RIGHT);
        Button bttitleSearch = new Button("Search");
        Button btClearSearch = new Button("Clear");
        Button btLibStage = new Button("Librarian Mode");
            btLibStage.setStyle("-fx-background-color: blue;-fx-text-fill: white;");
        //styling
        mainGridPane.setPadding(new Insets(10,10,10,10));
        mainGridPane.add(gridPane,0,0);
        mainGridPane.add(rightSearchTView,1,0);
        rightSearchTView.getChildren().addAll(search,bookTableView);
        rightSearchTView.setSpacing(5);
        search.getChildren().addAll(tfsearchBook,bttitleSearch, btClearSearch);
        if(LogInControls.checkAccess())
            search.getChildren().add(btLibStage);
        search.setAlignment(Pos.CENTER);
        search.setSpacing(5);
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
        gridPane.add(text,0,10);
        gridPane.add(btAdd,1,10);
        gridPane.add(btLibrarians,0,11);
        gridPane.add(btStat,1,11);
        gridPane.add(btLogOut,1,11);

        //actions
        btAdd.setOnAction(e -> {
            try {
                BookControls.addBooks(tfTitle.getText(),tfAuthor.getText(),
                        tfCategory.getText(),Integer.parseInt(tfQuantity.getText()),tfISBN.getText(),
                        tfSupplier.getText(),new Date(),Integer.parseInt(tfPurchasePrice.getText()),Integer.parseInt(tfOrgPrice.getText()),
                                Integer.parseInt(tfSellPrice.getText()));
            } catch (Exception ex) {
                Stage o = new Stage();
                Text t = new Text("Fill all  *  fields!!");
                t.setStyle("-fx-font-size: 15px;");
                Button b = new Button("Close");
                b.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
                BorderPane p = new BorderPane();
                p.setPadding(new Insets(10,10,10,10));
                p.setBottom(b);
                p.setCenter(t);
                BorderPane.setAlignment(b,Pos.CENTER_RIGHT);
                Scene s = new Scene(p,220,100);
                o.setScene(s);
                o.initOwner(managerStage);
                o.setResizable(false);
                o.initModality(Modality.APPLICATION_MODAL);
                o.show();

                b.setOnAction(z -> o.close());
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
            tfsearchBook.clear();
            bookTableView.getItems().clear();

            try {
                bookTableView.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btCheck.setOnAction(e -> {
            Stage test = lowStockStage();
            test.show();
        });
        bttitleSearch.setOnAction(e-> {
            try(FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
            ObjectInputStream input = new ObjectInputStream(fInput)){
                ArrayList<Book> booksFound = new ArrayList<>();
                bookTableView.getItems().clear();
                while(fInput.available()>0){
                    Book A = (Book) input.readObject();
                    if((Pattern.compile(tfsearchBook.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())){
                        booksFound.add(A);
                    }
                }
                for(Book book : booksFound)
                    bookTableView.getItems().add(book);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        btClearSearch.setOnAction(e->{
            tfsearchBook.clear();
            bookTableView.getItems().clear();
            try {
                bookTableView.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btLogOut.setOnAction(e -> {
            LogInWindow L = new LogInWindow();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            managerStage.close();
        });
        bookTableView.setOnMouseClicked(e ->{
            if(e.getClickCount()==2){
                try {
                    Stage x = bookoInfo(bookTableView.getSelectionModel().getSelectedItem());
                    x.show();
                }catch(Exception ex){

                }
            }
        });
        tfsearchBook.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER){
                try(FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
                    ObjectInputStream input = new ObjectInputStream(fInput)){
                    ArrayList<Book> booksFound = new ArrayList<>();
                    bookTableView.getItems().clear();
                    while(fInput.available()>0){
                        Book A = (Book) input.readObject();
                        if((Pattern.compile(tfsearchBook.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())){
                            booksFound.add(A);
                        }
                    }
                    for(Book book : booksFound)
                        bookTableView.getItems().add(book);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btLibrarians.setOnAction(e->{
            LibrarianStatistics l = new LibrarianStatistics();
            try {
                l.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        btLibStage.setOnAction(e->{
            LibrarianStage L = new LibrarianStage();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            managerStage.close();
        });
        btStat.setOnAction(e->{
            BookStatistics L = new BookStatistics();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        managerStage.setResizable(false);
        managerStage.setTitle("Bookstore(Manager)");
        managerStage.setScene(scene);
        managerStage.show();
    }
    public Stage lowStockStage(){
        //getLowBooks
        ArrayList<Book> lowBooks;
        try {
            lowBooks = BookControls.getLowStock();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //stages
        Stage lowStockStage = new Stage();

        //panes
        HBox lowHBox = new HBox();
        BorderPane tableLowPane = new BorderPane();

        //scenes
        Scene lowStockScene = new Scene(tableLowPane, 1000,500);

        //tableView
        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> colAuthor = new TableColumn<>("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book, Integer> colStock = new TableColumn<>("Stock");
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<Book, String> colISBN = new TableColumn<>("ISBN");
        colISBN.setCellValueFactory(new PropertyValueFactory<>("iSBN"));
        TableColumn<Book, Integer> colPrice = new TableColumn<>("Price");
        colPrice.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        tableView.getColumns().addAll(colTitle,colAuthor,colStock,colISBN,colPrice);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //tableViewUp
        for(Book book : lowBooks){
            tableView.getItems().add(book);
        }

        Label lbLowQuan = new Label("Quantity:");

        TextField tfLowQuan = new TextField();
        tfLowQuan.setPromptText("Quantity");

        Button btAddStock = new Button("Add Stock") ;
        btAddStock.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
        Button btLowClose = new Button("Close");
        btLowClose.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");

        tableLowPane.setCenter(tableView);
        tableLowPane.setBottom(lowHBox);
        tableLowPane.setPadding(new Insets(0,0,5,0));
        lowHBox.getChildren().addAll(lbLowQuan,tfLowQuan,btAddStock,btLowClose);
        lowHBox.setSpacing(2);
        lowHBox.setAlignment(Pos.BASELINE_CENTER);
        BorderPane.setMargin(lowHBox, new Insets(5,0,0,0));

        btLowClose.setOnAction(e ->  { tableView.getItems().clear();
            try {
                bookTableView.getItems().clear();
                bookTableView.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException ez) {
                throw new RuntimeException(ez);
            }
            tfLowQuan.clear();
            lowStockStage.close();});
        btAddStock.setOnAction(e -> {
            try {
                BookControls.addStock(tableView.getSelectionModel().getSelectedItem().getISBN(), Integer.parseInt(tfLowQuan.getText()));
            } catch (IOException | ClassNotFoundException | NullPointerException ex) {
                Stage o = new Stage();
                Text t = new Text("Please select a book.");
                t.setStyle("-fx-font-size: 15px;");
                Button b = new Button("Close");
                b.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
                BorderPane p = new BorderPane();
                p.setPadding(new Insets(10,10,10,10));
                p.setBottom(b);
                p.setCenter(t);
                BorderPane.setAlignment(b,Pos.CENTER_RIGHT);
                Scene s = new Scene(p,220,100);
                o.setScene(s);
                o.initOwner(lowStockStage);
                o.setResizable(false);
                o.initModality(Modality.APPLICATION_MODAL);
                o.show();

                b.setOnAction(z -> o.close());
            }
            tfLowQuan.clear();
            tableView.getItems().clear();
            try {
                tableView.getItems().addAll(BookControls.getLowStock());
            } catch (IOException | ClassNotFoundException ez) {
                throw new RuntimeException(ez);
            }

        });
        tableView.setOnMouseClicked(e ->{
            if(e.getClickCount()==2){
                try {
                    Stage x = bookoInfo(tableView.getSelectionModel().getSelectedItem());
                    x.show();
                }catch(Exception ex){

                }
            }
        });

        tableView.setPlaceholder(new Label("All books are up on stock."));
        lowStockStage.initModality(Modality.APPLICATION_MODAL);
        lowStockStage.setTitle("Low Stock Books");
        lowStockStage.setScene(lowStockScene);
        lowStockStage.setMinWidth(400);
        return lowStockStage;
    }

    public Stage bookoInfo(Book book){
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
        BorderPane.setAlignment(info,Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close,Pos.BOTTOM_RIGHT);

        //actions
        close.setOnAction(e -> stage.close());

        stage.setTitle("Book Info");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        return  stage;
    }

}
