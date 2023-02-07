package com.example.bookstoreapplication.Views;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.concurrent.Callable;

public class LibrarianStatistics extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        //pane
        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(5,5,5,5));
        mainVBox.setSpacing(5);
        mainVBox.setStyle("-fx-font-size: 15px;");
        HBox topHBox = new HBox();
        topHBox.setSpacing(5);
        GridPane topGridPane = new GridPane();
        topGridPane.setVgap(5);
        HBox lowHBox = new HBox();
        VBox lowLeft = new VBox();
        GridPane lowRight = new GridPane();
        VBox lowRightTop = new VBox();
        //scene
        Scene scene = new Scene(mainVBox);
        //LibrarianTableView
        TableView libTable = new TableView();
        libTable.setPrefWidth(700);
        libTable.setPrefHeight(300);
        //BillTableVIew
        TableView billTable = new TableView();
        billTable.setPrefWidth(500);
        billTable.setPrefHeight(280);

        //TextFields
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Search employee by Name / Surname / ID");
        tfSearch.setPrefWidth(590);

        //Label
        Label lbEmployee = new Label("Employee Table");
        Label lbBills = new Label("Bills Table");
        Text tTotal = new Text("Total:");
        tTotal.setStyle("-fx-font-size: 20px;");
        Text tShowTot = new Text("123443");
        tShowTot.setStyle("-fx-font-size: 40px;");
        Label lbDateFrom = new Label("From:");
        Label lbDateTo = new Label("To:");

        //Buttons
        Button btClose = new Button("Close");
        btClose.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btSearch = new Button("Search");
        Button btClear = new Button("Clear");
        Button btShow = new Button("Show");
        btShow.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");


        //datePicker
        DatePicker dpFrom = new DatePicker();
        DatePicker dpTo = new DatePicker();

        //arragnements
        topHBox.getChildren().addAll(tfSearch,btSearch,btClear);
        topGridPane.add(topHBox,0,0);
        topGridPane.add(lbEmployee,0,1);
        GridPane.setHalignment(lbEmployee,HPos.CENTER);
        GridPane.setHalignment(libTable, HPos.CENTER);
        topGridPane.add(libTable,0,2);
        GridPane.setHalignment(libTable, HPos.CENTER);

        lowHBox.getChildren().addAll(lowLeft,lowRight);
        lowHBox.setAlignment(Pos.CENTER);
        lowHBox.setSpacing(5);;

        lowLeft.getChildren().addAll(lbBills,billTable);
        lowLeft.setSpacing(5);
        lowLeft.setAlignment(Pos.CENTER);

        lowRightTop.getChildren().addAll(lbDateFrom,dpFrom,lbDateTo,dpTo,btShow,tTotal,tShowTot);
        VBox.setMargin(tShowTot,new Insets(0,0,0,70));
        lowRightTop.setSpacing(5);
        Label blank = new Label();
        Label blank1 = new Label();
        lowRight.add(blank,0,0);
        lowRight.add(lowRightTop,0,1);
        lowRight.add(blank1,0,2);
        lowRight.add(btClose,0,3);
        lowRight.setVgap(5);
        GridPane.setValignment(btClose,VPos.TOP);
        GridPane.setHalignment(btClose,HPos.RIGHT);
        mainVBox.getChildren().addAll(topGridPane, lowHBox);

        stage.setTitle("Librarian Statistics");
        stage.setScene(scene);
        stage.show();

    }
}
