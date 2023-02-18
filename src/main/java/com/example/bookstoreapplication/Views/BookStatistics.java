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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class BookStatistics extends Application {

    @Override
    public void start(Stage stage) {
        //pane
        VBox mainPane = new VBox();
            mainPane.setStyle("-fx-font-size: 15px");
            mainPane.setPadding(new Insets(5));
        VBox bot = new VBox();
        HBox bott = new HBox();
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
        TableColumn<Book, String> colBought = new TableColumn<>("Bought");
        colBought.setCellValueFactory(new PropertyValueFactory<>("bought"));
        TableColumn<Book, String> colPPrice = new TableColumn<>("Purchase Price");
        colPPrice.setCellValueFactory(new PropertyValueFactory<>("purchasedPrice"));
        TableColumn<Book, String> colSold = new TableColumn<>("Sold");
        colSold.setCellValueFactory(new PropertyValueFactory<>("sold"));
        TableColumn<Book, String> colSPrice = new TableColumn<>("Selling Price");
        colSPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));
        table.getColumns().addAll(colTitle,colAuthor,colISBN,colBought,colPPrice,colSold,colSPrice);
        try {
            table.getItems().addAll(BookControls.getBooks());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPrefWidth(1050);

        Text profit = new Text("Total Profit: ");
        profit.setStyle("-fx-font-size: 20px;");
        Text p = new Text("");
        p.setStyle("-fx-font-size: 30px;");
        TextField search = new TextField();

        Button btClose = new Button("Close");
        btClose.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btSearch = new Button("Search");
        Button btClear = new Button("Clear");

        search.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER){
                try(FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
                    ObjectInputStream input = new ObjectInputStream(fInput)){
                    ArrayList<Book> booksFound = new ArrayList<>();
                    table.getItems().clear();
                    while(fInput.available()>0){
                        Book A = (Book) input.readObject();
                        if((Pattern.compile(search.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())){
                            booksFound.add(A);
                        }
                    }
                    for(Book book : booksFound)
                        table.getItems().add(book);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btClear.setOnAction(e->{
            search.clear();
            table.getItems().clear();
            try {
                table.getItems().addAll(BookControls.getBooks());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        btSearch.setOnAction(e-> {
            try(FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)){
                ArrayList<Book> booksFound = new ArrayList<>();
                table.getItems().clear();
                while(fInput.available()>0){
                    Book A = (Book) input.readObject();
                    if((Pattern.compile(search.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getBookSearchProperties()).find())){
                        booksFound.add(A);
                    }
                }
                for(Book book : booksFound)
                    table.getItems().add(book);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }

        });
        btClose.setOnAction(e-> stage.close());
        table.setOnMouseClicked(e->{
            p.setText(String.valueOf(table.getSelectionModel().getSelectedItem().getSold()*
                            table.getSelectionModel().getSelectedItem().getSellingPrice()-
                            table.getSelectionModel().getSelectedItem().getBought()*
                            table.getSelectionModel().getSelectedItem().getPurchasedPrice()));
        });
        //arr
        mainPane.getChildren().addAll(table,bot);
        bot.getChildren().addAll(bott, btClose);
        bott.getChildren().addAll(search,btSearch,btClear,profit,p);
        bot.setAlignment(Pos.CENTER_RIGHT);
        bott.setAlignment(Pos.CENTER);
        bott.setPadding(new Insets(5,0,0,0));
        bott.setSpacing(5);
        stage.setTitle("Book Stats");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }
}
