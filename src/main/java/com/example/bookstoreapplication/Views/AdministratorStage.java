package com.example.bookstoreapplication.Views;


import com.example.bookstoreapplication.Models.Administrator;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Manager;
import com.example.bookstoreapplication.Models.Person;
import com.example.bookstoreapplication.NoHeader.NoHeader;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class AdministratorStage extends Application {
    Button btDelete = new Button("Delete Selected Employees");
    public static TableView AllEmp = new TableView<>();
    public static TableView tbChangeAccessLib = new TableView<>();
    public static TableView tbChangeAccessMan = new TableView<>();
    public static ArrayList <Administrator> Admin = new ArrayList<>();
    public static Button AccessModififer= new Button("Change Employee access");
    public static ArrayList <Manager> Managers = new ArrayList<>();
    public static ArrayList <Librarian> Librarians = new ArrayList<>();
    public static Button btManagerView= new Button("Manager View");
    public static Button btLibrarianView =  new Button("Librarian View");
    public static Button btAdd = new Button("Add Employee");
    public static Button btSearch = new Button("Search");
    public static Button btLogOut = new Button("Log Out");
    public static TextField search = new TextField();
    public static ArrayList<Librarian> searchedEmp = new ArrayList<>();

    @Override
    public void start(Stage AdminStage) throws IOException, ClassNotFoundException {

        getAllLib();
        getAdmin();
        getManager();
        //Table Creation

        TableColumn<Librarian, String> NameCol = new TableColumn<>("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Librarian, String> SurnCol = new TableColumn<>("Surname");
        SurnCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        TableColumn<Person, String> UserNameCol = new TableColumn<>("Username");
        UserNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        TableColumn<Librarian, Integer> Id = new TableColumn<>("LibrarianID");
        Id.setCellValueFactory(new PropertyValueFactory<>("librarianID"));
        TableColumn<Librarian, String> PHN = new TableColumn<>("Phone Number");
        PHN.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        AllEmp.getColumns().addAll(NameCol, SurnCol,UserNameCol,Id, PHN);
        AllEmp.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        AllEmp.setMinWidth(900);
        AllEmp.setMaxHeight(375);
        for (Librarian librarian : Librarians) {
            AllEmp.getItems().add(librarian);
        }
// search bar modify
        search.setPrefWidth(400);
        search.setPromptText("\t Enter Name/Surname/LibrarianID/Username");

        // Button Actions


        btDelete.setOnAction(e -> {

            Stage x = AreYouSureDeletion((Librarian) AllEmp.getSelectionModel().getSelectedItem());
            x.show();
        });

        btSearch.setOnAction(e -> {

            AllEmp.getItems().clear();
            try {
                searchedEmp = searchBt(search);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for (Librarian lib : searchedEmp) {
                AllEmp.getItems().add(lib);
            }
            searchedEmp.clear();
            search.clear();
        });
        btLogOut.setOnAction(e -> {
            LogInWindow L = new LogInWindow();
            try {
                L.start(new Stage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            Admin.clear();
            Managers.clear();
            Librarians.clear();
            AllEmp.getItems().clear();
            AllEmp.getColumns().clear();

            AdminStage.close();
        });
        btManagerView.setOnAction(e -> {
            ManagerView L = new ManagerView();
            try {
                L.start(new Stage());
                Admin.clear();
                Managers.clear();
                Librarians.clear();
                AllEmp.getItems().clear();
                AllEmp.getColumns().clear();

                AdminStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

        });
        AllEmp.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Stage x = EmpInfo((Librarian) AllEmp.getSelectionModel().getSelectedItem());
                x.show();
            }
        });

        btLibrarianView.setOnAction(e -> {
            LibrarianStage L = new LibrarianStage();
            try {
                L.start(new Stage());
                Admin.clear();
                Managers.clear();
                Librarians.clear();
                AllEmp.getItems().clear();
                AllEmp.getColumns().clear();

                AdminStage.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
                });

            AccessModififer.setOnAction(e -> {
            tbChangeAccessMan.getItems().clear();
            tbChangeAccessLib.getItems().clear();
            try {
                Stage X = AccessDiff();
                X.show();
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            });
        btLibrarianView.setStyle("-fx-background-color: blue;-fx-text-fill: white;");
        btManagerView.setStyle("-fx-background-color: blue;-fx-text-fill: white;");
        btLogOut.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
        btAdd.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
        btDelete.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
        // all search bar ribbon
        HBox searchBar = new HBox();
        searchBar.getChildren().add(search);
        searchBar.getChildren().add(btSearch);
        searchBar.getChildren().add(btLogOut);
        searchBar.setAlignment(Pos.CENTER);
        search.setAlignment(Pos.CENTER);
        btLogOut.setAlignment(Pos.CENTER);
        //RightSide
        VBox RightSideTable = new VBox();
        RightSideTable.getChildren().add(searchBar);
        RightSideTable.getChildren().add(AllEmp);
        RightSideTable.getChildren().add(btDelete);
        btDelete.setAlignment(Pos.BOTTOM_RIGHT);
        // LeftSide

        VBox LeftSideAdder = new VBox();
        LeftSideAdder.getChildren().add(btLibrarianView);
        LeftSideAdder.getChildren().add(btManagerView);
        LeftSideAdder.getChildren().add(addingEmpPane());
        LeftSideAdder.getChildren().add(AccessModififer);
        LeftSideAdder.setAlignment(Pos.CENTER);
        LeftSideAdder.setSpacing(5);
        // Table Pane ===> pane with table and all nodes that have the need for table

        // Main Pane ties everything into place
        GridPane MainPane = new GridPane();
        MainPane.add(LeftSideAdder, 0, 0);
        MainPane.add(RightSideTable, 1, 0);
        MainPane.setStyle("-fx-font-size: 15px;");
        MainPane.setHgap(5);
        MainPane.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(MainPane);

        AdminStage.setScene(scene);
        AdminStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public static GridPane addingEmpPane() {







        GridPane gridPane = new GridPane();
        TextField tfName = new TextField();
        tfName.setPromptText("Name");
        TextField tfSurname = new TextField();
        tfSurname.setPromptText("Surname");
        TextField tfPhoneNumber = new TextField();
        tfPhoneNumber.setPromptText("+355 6X XX XX XXX");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("example@example.com");
        TextField tfLibrarianID = new TextField();
        tfLibrarianID.setPromptText("LibrarianID");
        TextField tfSalary = new TextField();
        tfSalary.setPromptText("Salary");
        TextField tfUsername = new TextField();
        tfUsername.setPromptText("Username");
        TextField tfPassword = new TextField();
        tfPassword.setPromptText("Password");
        DatePicker datepicker = new DatePicker();
        datepicker.setPromptText("Birthdate");
        btAdd.setOnAction(k -> {
            try {
                Date Birthdate = new Date(datepicker.getValue().toEpochDay());


                for(int i=0;i<Librarians.size();i++){
                    Librarian A= (Librarian) (Librarians.get(i));
                    if (A.getLibrarianID() == Integer.parseInt(tfLibrarianID.getText())){
                        Stage x = DuplicateInfo(1);
                        x.show();
                        tfLibrarianID.clear();
                        break;
                    } else if (A.getUserName().contentEquals(tfUsername.getText())) {
                        Stage x = DuplicateInfo(2);
                        x.show();
                        tfUsername.clear();
                        break;
                    } else {   addNewEmp(tfName.getText(),
                            tfSurname.getText(),
                            Birthdate,
                            tfPhoneNumber.getText(),
                            Integer.parseInt(tfLibrarianID.getText()),
                            tfEmail.getText(),
                            tfPassword.getText(),
                            tfUsername.getText(),
                            Integer.parseInt(tfSalary.getText()));
                        tfName.clear();
                        tfSurname.clear();
                        tfPhoneNumber.clear();
                        datepicker.getEditor().clear();
                        tfEmail.clear();
                        tfSalary.clear();
                        tfLibrarianID.clear();
                        tfPhoneNumber.clear();
                        tfUsername.clear();
                        tfPassword.clear();
                        AllEmp.getItems().clear();
                        for (int j = 0; j < Librarians.size(); j++) {
                            AllEmp.getItems().add(Librarians.get(j));
                        }
                        break;
                    }

                }


            } catch (Exception ex) {

                Stage X = noAddedInfo();
                X.show();

            }


        });


        //Labels
        Label lbName = new Label("Name:");
        Label lbSurname = new Label("Surname:");
        Label lbLibrarianID = new Label("LibrarianID:");
        Label lbEmail = new Label("Email:");
        Label lbUsername = new Label("Username:");
        Label lbPassword = new Label("Password:");
        Label lbSalary = new Label("Salary");
        Label lbBirthdate = new Label("BirthDate:");
        Label lbPhoneNumber = new Label("Phone Number:");
        gridPane.add(lbName, 0, 1);
        gridPane.add(tfName, 1, 1);
        gridPane.add(lbSurname, 0, 2);
        gridPane.add(tfSurname, 1, 2);
        gridPane.add(lbLibrarianID, 0, 3);
        gridPane.add(tfLibrarianID, 1, 3);
        gridPane.add(lbEmail, 0, 4);
        gridPane.add(tfEmail, 1, 4);
        gridPane.add(lbUsername, 0, 5);
        gridPane.add(tfUsername, 1, 5);
        gridPane.add(lbPassword, 0, 6);
        gridPane.add(tfPassword, 1, 6);
        gridPane.add(lbSalary, 0, 7);
        gridPane.add(tfSalary, 1, 7);
        gridPane.add(lbBirthdate, 0, 8);
        gridPane.add(datepicker, 1, 8);
        gridPane.add(lbPhoneNumber, 0, 9);
        gridPane.add(tfPhoneNumber, 1, 9);
        gridPane.add(btAdd, 1, 10);


        return gridPane;
    }


    public static void deleteEmp(Librarian B) throws IOException {

        for (int i = 0; i < Librarians.size(); i++) {
            Librarian A = (Librarian) Librarians.get(i);
            if (A.getLibrarianID() == B.getLibrarianID()) {

                Librarians.remove(A);
            }
        }

        FileOutputStream newOutput = new FileOutputStream("src/main/resources/Employee.dat");
        ObjectOutputStream newOut = new ObjectOutputStream(newOutput);

        for (int i = 0; i < Librarians.size(); i++) {
            Librarian A =  (Librarians.get(i));

            newOut.writeObject(A);
        }
        for(int i = 0; i < Admin.size(); i++){
            Administrator A = (Administrator) Admin.get(i);
            newOut.writeObject(A);
        }
        for(int i = 0; i < Managers.size(); i++){
            Manager A = (Manager) Managers.get(i);
            newOut.writeObject(A);
        }
    }

    public static void getAllLib() throws IOException, ClassNotFoundException {
        try (
                FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)
        ) {

            while (fInput.available() > 0) {

                Person A = (Person) input.readObject();
                if (A instanceof Librarian)

                    Librarians.add((Librarian) A);

            }

        }
    }

    public static void addNewEmp(String name, String surname, Date date, String phoneNumber, int ID, String email, String Username, String Password, int Salary) throws IOException, ClassNotFoundException {

        if (!((new File("src/main/resources/Employee.dat")).exists())) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"));
                 FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat")
            ) {

                Librarian newLibrarian = new Librarian(name, surname, date, phoneNumber, ID, email, Username, Password, Salary);
                Librarians.add(newLibrarian);
                output.writeObject(newLibrarian);


            }
        } else {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("src/main/resources/Employee.dat", true))) {
                Librarian newLib = new Librarian(name, surname, date, phoneNumber, ID, email, Username, Password, Salary);
                noHeader.writeObject(newLib);
                Librarians.add(newLib);
            }


        }
    }


    public static ArrayList<Librarian> searchBt(TextField tfSearchBar) throws IOException, ClassNotFoundException {

        for(int i = 0; i< Librarians.size(); i++) {
            Librarian A= (Librarian) Librarians.get(i);
            if ((Pattern.compile(tfSearchBar.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getLibrarianProperties()).find())) {
                searchedEmp.add(A);

            }
        }
        return searchedEmp;
    }


    public static Stage NoInfoAdded() {
        Stage newStage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label info = new Label("No selected employee to be deleted ");

        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 20px;");
        bPane.setCenter(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(l -> newStage.close());

        newStage.setTitle("No employee Error");
        newStage.setScene(scene);

        newStage.setResizable(false);
        return newStage;

    }

    public Stage EmpInfo(Librarian lib)  {
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
        BorderPane.setAlignment(close,Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(e -> stage.close());

        stage.setTitle("Employee Info");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.show();
        return  stage;
    }

    public static Stage noAddedInfo() {
        Stage newStage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label info = new Label("Not enough info has been added for new employee entry  ");

        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 20px;");
        bPane.setCenter(info);
        BorderPane.setAlignment(info, Pos.CENTER);
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(l -> newStage.close());

        newStage.setTitle("No employee Error");
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        return newStage;

    }
    public static Stage AreYouSureDeletion(Librarian Lib) {
        Stage newStage = new Stage();

        //Pane

        VBox Main = new VBox();
        GridPane YesNo = new GridPane();

        //Scene
        Scene scene = new Scene(Main);

        //messageLabel
        Label info = new Label("Are you sure you want to delete "+Lib.getName()+" "+Lib.getSurname()+" from employees  ");

        //buttons
        Button btAreYouSureYes= new Button("Yes");
        Button btAreYouSureNo= new Button("No");
        btAreYouSureNo.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        btAreYouSureYes.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
        //arrangements

        YesNo.add(btAreYouSureNo,3,0);
        YesNo.add(btAreYouSureYes,0,0);
        btAreYouSureYes.setAlignment(Pos.BOTTOM_LEFT);
        btAreYouSureNo.setAlignment(Pos.BOTTOM_RIGHT);
        Main.getChildren().addAll(info,YesNo);
        Main.setPadding(new Insets(10,10,10,10));
        //actions
        btAreYouSureNo.setOnAction(l -> newStage.close());
        btAreYouSureYes.setOnAction(e -> {


            try {
                deleteEmp((Librarian) AllEmp.getSelectionModel().getSelectedItem());

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (Exception ex) {
                Stage X = NoInfoAdded();
                X.show();
            }
            AllEmp.getItems().clear();
            for (Librarian employee : Librarians) {
                AllEmp.getItems().add(employee);
            }
            newStage.close();
        });
        Main.setStyle("-fx-font-size: 20px;");
        newStage.setTitle("Delete");
        newStage.setScene(scene);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        return newStage;

    }

    public static Stage DuplicateInfo(int i) {
        Stage newStage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label ID = new Label("Duplicate ID found on registry ");
        Label Username= new Label("Duplicate Username found on registry");

        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 20px;");
        if (i==1){
            bPane.setCenter(ID);
            BorderPane.setAlignment(ID, Pos.CENTER);
        }
        else if(i==2) {
            bPane.setCenter(Username);
            BorderPane.setAlignment(Username, Pos.CENTER);
        }
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(l -> newStage.close());

        newStage.setTitle("No employee Error");
        newStage.setScene(scene);

        newStage.setResizable(false);
        return newStage;

    }
    public static Stage AccessDiff() throws IOException, ClassNotFoundException {
        Button btSearchAcc= new Button("Search");
        TextField searchAcc= new TextField();
        Stage AccessMod = new Stage();

        TableColumn<Person, String> LibNameCol = new TableColumn<Person, String>("Name");
        LibNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        TableColumn<Person, String> LibSurnCol = new TableColumn<Person, String>("Surname");
        LibSurnCol.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        TableColumn<Person, String> LibUserNameCol = new TableColumn<Person, String>("Username");
        LibUserNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));
        TableColumn<Librarian, Integer> Id = new TableColumn<Librarian, Integer>("LibrarianId");
        Id.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("librarianID"));
        TableColumn<Person, Boolean> LibExtraCol = new TableColumn<Person, Boolean>("Manager Access");
        LibExtraCol.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("managerAccess"));

        TableColumn<Person, String> ManNameCol = new TableColumn<Person, String>("Name");
        ManNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        TableColumn<Person, String> ManSurnCol = new TableColumn<Person, String>("Surname");
        ManSurnCol.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        TableColumn<Person, String> ManUserNameCol = new TableColumn<Person, String>("Username");
        ManUserNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));
        TableColumn<Person, Boolean> ManExtraCol = new TableColumn<Person, Boolean>("Librarian Access");
        ManExtraCol.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("LibrarianAccess"));

        tbChangeAccessMan.getColumns().addAll(ManNameCol, ManSurnCol, ManUserNameCol,ManExtraCol);
        tbChangeAccessMan.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbChangeAccessMan.setMinWidth(900);
        tbChangeAccessMan.setMaxHeight(300);
        for (int i = 0; i < Managers.size(); i++) {
            tbChangeAccessMan.getItems().add(Managers.get(i));
        }
        tbChangeAccessLib.getColumns().addAll(LibNameCol, LibSurnCol, LibUserNameCol, Id,LibExtraCol);
        tbChangeAccessLib.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tbChangeAccessLib.setMinWidth(900);
        tbChangeAccessLib.setMaxHeight(300);
        for (int i = 0; i < Librarians.size(); i++) {
            tbChangeAccessLib.getItems().add(Librarians.get(i));
        }
        Button btRemoveManAcc = new Button("Remove Manager Access");
        Button btRemoveLibAcc = new Button("Remove Librarian Access");
        Button btCloseAccess= new Button("Close");
        Button btGiveManager = new Button("Give Manager Access");
        Button btGiveLib = new Button("Give Librarian Access");

        btGiveManager.setOnAction(l ->
        {          try {
            Librarian Lali = (Librarian) tbChangeAccessLib.getSelectionModel().getSelectedItem();
            ModifyLibAccess(Lali,1);
        }
        catch(Exception ex){
            Stage X= WrongEmployeeButton(1);
            X.show();

        }
            tbChangeAccessLib.getItems().clear();


            for (int i = 0; i < Librarians.size(); i++) {
                tbChangeAccessLib.getItems().add(Librarians.get(i));
            }
        });
        btCloseAccess.setOnAction(e -> {

            tbChangeAccessLib.getItems().clear();
            tbChangeAccessLib.getColumns().clear();
            tbChangeAccessMan.getItems().clear();
            tbChangeAccessMan.getColumns().clear();
            AccessMod.close();
        });
        btRemoveManAcc.setOnAction(l->
                {          try {
                    Librarian Lali = (Librarian) tbChangeAccessLib.getSelectionModel().getSelectedItem();
                    ModifyLibAccess(Lali,2);
                }
                catch(Exception ex){
                    Stage X= WrongEmployeeButton(1);
                    X.show();

                }
                    tbChangeAccessLib.getItems().clear();


                    for (int i = 0; i < Librarians.size(); i++) {
                        tbChangeAccessLib.getItems().add(Librarians.get(i));
                    }
                }

                );

        btRemoveLibAcc.setOnAction(l->
        {  try {
            Manager Lali = (Manager) tbChangeAccessMan.getSelectionModel().getSelectedItem();
            ModifyManAccess(Lali,2);
        }
        catch(Exception ex){
            Stage X= WrongEmployeeButton(2);
            X.show();

        }

        tbChangeAccessMan.getItems().clear();


        for (int i = 0; i < Managers.size(); i++) {
            tbChangeAccessMan.getItems().add(Managers.get(i));
        }
    });
        btGiveLib.setOnAction(l ->
        {
            try {
                Manager Lali = (Manager) tbChangeAccessMan.getSelectionModel().getSelectedItem();
                ModifyManAccess(Lali,1);
            }
            catch(Exception ex){
                Stage X= WrongEmployeeButton(2);
                X.show();

            }

            tbChangeAccessMan.getItems().clear();


            for (int i = 0; i < Managers.size(); i++) {
                tbChangeAccessMan.getItems().add(Managers.get(i));
            }
        });

        btSearch.setOnAction(e -> {

            tbChangeAccessLib.getItems().clear();
            try {
                searchedEmp = searchBt(searchAcc);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            for (Librarian lib : searchedEmp) {
                AllEmp.getItems().add(lib);
            }
            searchedEmp.clear();
            searchAcc.clear();
        });
         btCloseAccess.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
         btGiveManager.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
        btGiveLib.setStyle("-fx-background-color: darkgreen;-fx-text-fill: white;");
         btRemoveLibAcc.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
         btRemoveManAcc.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
         HBox searchingNodes = new HBox();
        searchingNodes.getChildren().addAll(searchAcc,btSearchAcc,btCloseAccess);
        searchingNodes.setAlignment(Pos.CENTER);
        HBox tbLibAndButtons = new HBox();
        tbLibAndButtons.getChildren().addAll(tbChangeAccessLib,btGiveManager,btRemoveManAcc);
        tbLibAndButtons.setAlignment(Pos.CENTER);
        HBox tbManAndButtons = new HBox();
        tbManAndButtons.getChildren().addAll(tbChangeAccessMan,btGiveLib,btRemoveLibAcc);

        searchAcc.setPrefWidth(400);
        searchAcc.setPromptText("\t Enter Name/Surname/LibrarianID/Username");

        VBox AllHboxes= new VBox();
        AllHboxes.getChildren().addAll(searchingNodes,tbLibAndButtons,tbManAndButtons);
        Scene curr = new Scene(AllHboxes);

        AccessMod.setScene(curr);
        AccessMod.setResizable(false);
        return AccessMod;

    }
    public static void getManager() throws IOException, ClassNotFoundException {
        try (
                FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)
        ) {

            while (fInput.available() > 0) {

                Person A = (Person) input.readObject();
                if(A instanceof Manager) {
                    Managers.add((Manager) A);
                }
            }

        }
    }
    public static void getAdmin() throws IOException, ClassNotFoundException {
        try (
                FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)
        ) {

            while (fInput.available() > 0) {

                Person A = (Person) input.readObject();
                if(A instanceof Administrator) {
                    Admin.add((Administrator) A);
                }
            }

        }
    }
    public static Stage WrongEmployeeButton(int i) {
        Stage newStage = new Stage();

        //Pane
        BorderPane bPane = new BorderPane();

        //Scene
        Scene scene = new Scene(bPane);

        //messageLabel
        Label ID = new Label("Manager selected, you cannot give manager access ");
        Label Username= new Label("Librarian selected, you cannot give librarian access");

        //buttons
        Button close = new Button("Close");
        close.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        //arrangements
        bPane.setPadding(new Insets(10));
        bPane.setStyle("-fx-font-size: 20px;");
        if (i==1){
            bPane.setCenter(ID);
            BorderPane.setAlignment(ID, Pos.CENTER);
        }
        else if(i==2) {
            bPane.setCenter(Username);
            BorderPane.setAlignment(Username, Pos.CENTER);
        }
        bPane.setBottom(close);
        BorderPane.setAlignment(close, Pos.CENTER_RIGHT);

        //actions
        close.setOnAction(l -> newStage.close());

        newStage.setTitle("Error");
        newStage.setScene(scene);

        newStage.setResizable(false);
        return newStage;

    }
    public static void ModifyLibAccess(Librarian B,int K) throws IOException {
if(K==1) {
    for (int i = 0; i < Librarians.size(); i++) {
        Librarian A =  Librarians.get(i);
        if (A.getLibrarianID() == B.getLibrarianID()) {

            A.setHasManagerAccess(true);
        }
    }
} else if (K==2) {  for (int i = 0; i < Librarians.size(); i++) {
    Librarian A =  Librarians.get(i);
    if (A.getLibrarianID() == B.getLibrarianID()) {

        A.setHasManagerAccess(false);
    }
}

}

        FileOutputStream newOutput = new FileOutputStream("src/main/resources/Employee.dat");
        ObjectOutputStream newOut = new ObjectOutputStream(newOutput);

        for (int i = 0; i < Librarians.size(); i++) {
            Librarian A =  (Librarians.get(i));

            newOut.writeObject(A);
        }
        for(int i = 0; i < Admin.size(); i++){
            Administrator A = (Administrator) Admin.get(i);
            newOut.writeObject(A);
        }
        for(int i = 0; i < Managers.size(); i++){
            Manager A = (Manager) Managers.get(i);
            newOut.writeObject(A);
        }
    }
    public static void ModifyManAccess(Manager B,int K) throws IOException {
if(K==1) {
    for (int i = 0; i < Managers.size(); i++) {
        Manager A = Managers.get(i);
        if (A.getName().contentEquals(B.getName())) {

            A.setHasLibrarianAccess(true);
        }
    }
} else if (K==2) {for (int i = 0; i < Managers.size(); i++) {
    Manager A = Managers.get(i);
    if (A.getName().contentEquals(B.getName())) {

        A.setHasLibrarianAccess(false);
    }
}

}
        FileOutputStream newOutput = new FileOutputStream("src/main/resources/Employee.dat");
        ObjectOutputStream newOut = new ObjectOutputStream(newOutput);

        for (int i = 0; i < Librarians.size(); i++) {
            Librarian A =  (Librarians.get(i));

            newOut.writeObject(A);
        }
        for(int i = 0; i < Admin.size(); i++){
            Administrator A = (Administrator) Admin.get(i);
            newOut.writeObject(A);
        }
        for(int i = 0; i < Managers.size(); i++){
            Manager A = (Manager) Managers.get(i);
            newOut.writeObject(A);
        }
    }

}






