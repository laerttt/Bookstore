package com.example.bookstoreapplication.Views;

import com.example.bookstoreapplication.Models.Book;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookStatistics extends Application {

    @Override
    public void start(Stage stage) {
        //pane
        VBox mainPane = new VBox();
            mainPane.setStyle("-fx-font-size: 15px");
            mainPane.setPadding(new Insets(5));
        GridPane bottomPane = new GridPane();
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(10,0,0,0));
        bottomPane.setVgap(5);

        //scene
        Scene scene = new Scene(mainPane);

        //table columns
        TableView<Book> table = new TableView<>();
        TableColumn<Book, String> colTitle = new TableColumn<>("Title");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> colAuthor = new TableColumn<>("Author");
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<Book, String> colISBN = new TableColumn<>("ISBN");
        colISBN.setCellValueFactory(new PropertyValueFactory<>("iSBN"));
//        TableColumn<Book, String> colBought = new TableColumn<>("Bought");
//        colBought.setCellValueFactory(new PropertyValueFactory<>("bought"));
        TableColumn<Book, String> colPPrice = new TableColumn<>("Purchase Price");
        colPPrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
//        TableColumn<Book, String> colSold = new TableColumn<>("Sold");
//        colSold.setCellValueFactory(new PropertyValueFactory<>("sold"));
        TableColumn<Book, String> colSPrice = new TableColumn<>("Selling Price");
        colSPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        table.getColumns().addAll(colTitle,colAuthor,colISBN,colPPrice,colSPrice);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(1050);
        DatePicker after = new DatePicker();
        DatePicker before = new DatePicker();

        Text profit = new Text("Total Profit: ");
        Text p = new Text("");
        Label lbAfter = new Label("After:");
        Label lbBefore = new Label("Before:");
        TextField search = new TextField();

        Button btClose = new Button("Close");
        btClose.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btSearch = new Button("Search");
        Button btClear = new Button("Clear");

        //
        mainPane.getChildren().addAll(table,bottomPane);
        bottomPane.getColumnConstraints().add(new ColumnConstraints(250));
        bottomPane.getColumnConstraints().add(new ColumnConstraints(250));
        bottomPane.getColumnConstraints().add(new ColumnConstraints(250));
        bottomPane.getColumnConstraints().add(new ColumnConstraints(250));
        bottomPane.add(search,0,0);
        GridPane.setHalignment(search, HPos.LEFT);
        bottomPane.add(btSearch,0,0);
        GridPane.setHalignment(btSearch, HPos.CENTER);
        bottomPane.add(btClear,0,0);
        GridPane.setHalignment(btClear, HPos.RIGHT);

        bottomPane.add(btClose,0,1);

        bottomPane.add(lbAfter,1,0);
        bottomPane.add(lbBefore,2,0);
        bottomPane.add(profit,3,0);
        bottomPane.add(after,1,1);
        bottomPane.add(before,2,1);
        bottomPane.add(p,3,1);

        stage.setTitle("Book Stats");
        stage.setScene(scene);
        stage.show();

    }
}
