package com.example.bookstoreapplication.Views;

import com.example.bookstoreapplication.Controls.BillControls;
import com.example.bookstoreapplication.Controls.LibrarianControlls;
import com.example.bookstoreapplication.Models.Bill;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class LibrarianStatistics extends Application {
    ArrayList<Bill> librarianBills = new ArrayList<>();
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

        //libTableCol
        TableColumn<Librarian, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<Librarian, String>("name"));
        TableColumn<Librarian, String> colSurname = new TableColumn<>("Surname");
        colSurname.setCellValueFactory(new PropertyValueFactory<Librarian, String>("surname"));
        TableColumn<Librarian, Integer> colID = new TableColumn<>("Librarian ID");
        colID.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("librarianID"));
        TableColumn<Librarian, String> colMail = new TableColumn<>("E-Mail");
        colMail.setCellValueFactory(new PropertyValueFactory<Librarian, String>("email"));
        TableColumn<Librarian, String> colPhn = new TableColumn<>("Phone No.");
        colPhn.setCellValueFactory(new PropertyValueFactory<Librarian, String>("phoneNumber"));
        libTable.getColumns().addAll(colName,colSurname,colID,colMail,colPhn);
        libTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        libTable.getItems().addAll(LibrarianControlls.getAllLibrarians());

        //BillTableVIew
        TableView billTable = new TableView();
        billTable.setPrefWidth(500);
        billTable.setPrefHeight(280);
        TableColumn<Bill, Integer> colBillNo = new TableColumn<>("Bill No.");
        colBillNo.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billNumber"));
        TableColumn<Bill, Integer> colLibID = new TableColumn<>("Librarian ID");
        colLibID.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("librarianID"));
        TableColumn<Bill, Integer> colBookNo = new TableColumn<>("Boook No.");
        colBookNo.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("numberOfBooks"));
        TableColumn<Bill, Integer> colTotal = new TableColumn<>("Price");
        colTotal.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("totalPrice"));
        billTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billTable.getColumns().addAll(colBillNo,colLibID,colBookNo,colTotal );
        billTable.getItems().addAll(BillControls.getAllBills());

        //TextFields
        TextField tfSearch = new TextField();
        tfSearch.setPromptText("Search employee by Name / Surname / ID");
        tfSearch.setPrefWidth(590);

        //Label
        Label lbEmployee = new Label("Employee Table");
        Label lbBills = new Label("Bills Table");
        Text tTotal = new Text("Total:");
        tTotal.setStyle("-fx-font-size: 20px;");
        Label lbDateFrom = new Label("After:");
        Label lbDateTo = new Label("Before:");
        Text tShowTot = new Text(String.valueOf(tablePrice(billTable)));
        tShowTot.setStyle("-fx-font-size: 40px;");

        //Buttons
        Button btClose = new Button("Close");
        btClose.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button btSearch = new Button("Search");
        Button btClear = new Button("Clear");
        Button btSave = new Button("Print Bill");
        btSave.setStyle("-fx-background-color: #a36917;-fx-text-fill: white;");

        //datePicker
        DatePicker dpFrom = new DatePicker();
        DatePicker dpTo = new DatePicker();

        //actions
        tfSearch.setOnKeyPressed(e->{
            if(e.getCode()== KeyCode.ENTER){
                try(FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                    ObjectInputStream input = new ObjectInputStream(fInput)){
                    ArrayList<Person> empFound = new ArrayList<>();
                    libTable.getItems().clear();
                    while(fInput.available()>0){
                        Person A = (Person) input.readObject();
                        if(A instanceof Librarian){
                            if((Pattern.compile(tfSearch.getText(), Pattern.CASE_INSENSITIVE).matcher(((Librarian) A).getLibrarianSearchProperties()).find())){
                                empFound.add(A);
                            }
                        }
                    }
                    libTable.getItems().clear();
                    for(Person emp : empFound)
                        libTable.getItems().add(emp);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                tfSearch.clear();
            }
        });
        btSearch.setOnAction(e -> {
            try(FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)){
                ArrayList<Person> empFound = new ArrayList<>();
                libTable.getItems().clear();
                while(fInput.available()>0){
                    Person A = (Person) input.readObject();
                    if(A instanceof Librarian){
                        if((Pattern.compile(tfSearch.getText(), Pattern.CASE_INSENSITIVE).matcher(((Librarian) A).getLibrarianSearchProperties()).find())){
                            empFound.add(A);
                        }
                    }
                }
                libTable.getItems().clear();
                for(Person emp : empFound)
                    libTable.getItems().add(emp);
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            tfSearch.clear();
        });
        btClear.setOnAction(e->{
            libTable.getItems().clear();
            billTable.getItems().clear();
            librarianBills.clear();
            try {
                libTable.getItems().addAll(LibrarianControlls.getAllLibrarians());
                billTable.getItems().addAll(BillControls.getAllBills());
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            tShowTot.setText(String.valueOf(tablePrice(billTable)));
        });
        libTable.setOnMouseClicked(e->{
            if(e.getClickCount() == 2){
                try {
                    Stage x = libInfo((Librarian) libTable.getSelectionModel().getSelectedItem());
                    x.show();
                }catch(Exception ex){
                }
            }
            if(e.getClickCount()==1){
                billTable.getItems().clear();
                librarianBills.clear();
                dpFrom.getEditor().clear();
                dpTo.getEditor().clear();
                ArrayList<Bill> bills;
                try {
                    bills = new ArrayList<>(BillControls.getAllBills());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                for(Bill bill : bills){
                    if (bill.getLibrarianID() == ((Librarian)libTable.getSelectionModel().getSelectedItem()).getLibrarianID()){
                        billTable.getItems().add(bill);
                        librarianBills.add(bill);
                    }
                }
                tShowTot.setText(String.valueOf(tablePrice(billTable)));
            }
        });
        billTable.setOnMouseClicked(e->{
            if (e.getClickCount() == 2){
                try {
                    Stage x = billInfo((Bill) billTable.getSelectionModel().getSelectedItem());
                    x.show();
                }catch(Exception ex){

                }
            }
        });
        btClose.setOnAction(e -> stage.close());
        dpFrom.setOnAction(e -> {
            billTable.getItems().clear();
            if(!librarianBills.isEmpty()) {
                for (Bill bill : librarianBills) {
                    try {
                        if ((bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))) &&
                                (bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    } catch (NullPointerException ex) {
                        if ((bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    }
                }
            }
            else{
                ArrayList<Bill> bills = new ArrayList<>();
                try {
                    bills.addAll(BillControls.getAllBills());
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (Bill bill : bills) {
                    try {
                        if ((bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))) &&
                                (bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    } catch (NullPointerException ex) {
                        if ((bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    }
                }
            }
            tShowTot.setText(String.valueOf(tablePrice(billTable)));
        });
        dpTo.setOnAction(e -> {
            billTable.getItems().clear();
            if(!librarianBills.isEmpty()) {
                for (Bill bill : librarianBills) {
                    try {
                        if ((bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))) &&
                                (bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    } catch (NullPointerException ex) {
                        if ((bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    }
                }
            }
            else{
                ArrayList<Bill> bills = new ArrayList<>();
                try {
                    bills.addAll(BillControls.getAllBills());
                } catch (IOException | ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                for (Bill bill : bills) {
                    try {
                        if ((bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))) &&
                                (bill.getDate()).after(new Date(dpFrom.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    } catch (NullPointerException ex) {
                        if ((bill.getDate()).before(new Date(dpTo.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))) {
                            billTable.getItems().add(bill);
                        }
                    }
                }
            }
            tShowTot.setText(String.valueOf(tablePrice(billTable)));
        });
        btSave.setOnAction(e -> {
            try {
                BillControls.saveTxt((Bill) billTable.getSelectionModel().getSelectedItem(),stage);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        //arrangements
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

        lowRightTop.getChildren().addAll(lbDateFrom,dpFrom,lbDateTo,dpTo,btSave,tTotal,tShowTot);
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
//        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();

    }
    public Stage libInfo(Librarian lib){
        //stage
        Stage stage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label info = new Label(lib.getLibrarianProperties());

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

        stage.setTitle("Librarian Info");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        return  stage;
    }
    public Stage billInfo(Bill lib){
        //stage
        Stage stage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label info = new Label(lib.getBillProperties());

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
    public int tablePrice(TableView table){
        ArrayList<Bill> b = new ArrayList<>();
        int y=0;
        b.addAll(table.getItems());
        for(Bill x : b)
            y+=x.getTotalPrice();
        return y;
    }
}
