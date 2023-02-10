package com.example.bookstoreapplication.Views;


import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;
import com.example.bookstoreapplication.NoHeader.NoHeader;
import com.example.bookstoreapplication.Views.LogInWindow;
import com.example.bookstoreapplication.Views.ManagerView;
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
    public static ArrayList <Librarian> Employees = new ArrayList<>();
    public static Button btManagerView= new Button("Manager View");
    public static Button btLibrarianView =  new Button("Librarian View");
    public static Button btAdd = new Button("Add Employee");
    public static Button btSearch = new Button("Search");
    public static Button btLogOut = new Button("Log Out");
    public static TextField search = new TextField();
    public static ArrayList<Librarian> searchedEmp = new ArrayList<>();

    @Override
    public void start(Stage AdminStage) throws IOException, ClassNotFoundException {

        getAllEmp();

       //Table Creation

        TableColumn<Librarian, String> NameCol = new TableColumn<Librarian, String>("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("name"));
        TableColumn<Librarian, String> SurnCol = new TableColumn<Librarian, String>("Surname");
        SurnCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("surname"));
        TableColumn<Person, String> UserNameCol = new TableColumn<Person, String>("Username");
        UserNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("userName"));
        TableColumn<Librarian, Integer> Id = new TableColumn<Librarian, Integer>("LibrarianID");
        Id.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("librarianID"));
        TableColumn<Librarian, String> PHN = new TableColumn<Librarian, String>("Phone Number");
        PHN.setCellValueFactory(new PropertyValueFactory<Librarian, String>("phoneNumber"));
        AllEmp.getColumns().addAll(NameCol, SurnCol,UserNameCol,Id, PHN);
        AllEmp.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        AllEmp.setMinWidth(900);
        AllEmp.setMaxHeight(375);
        for (int i = 0; i < Employees.size(); i++) {
            AllEmp.getItems().add(Employees.get(i));
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
            AdminStage.close();
        });
        btManagerView.setOnAction(e -> {
            ManagerView L = new ManagerView();
            try {
                L.start(new Stage());
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
        tfPhoneNumber.setPromptText("Phone Number");
        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");
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

                try (FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                     ObjectInputStream input = new ObjectInputStream(fInput)
                ) {
                    while (fInput.available() > 0) {
                        Librarian A = (Librarian) input.readObject();
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
                            for (int i = 0; i < Employees.size(); i++) {
                                AllEmp.getItems().add(Employees.get(i));
                            }
                            break;
                        }

                    }
                }

            } catch (Exception ex) {

                 Stage X= noAddedInfo();
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

                for (int i = 0; i < Employees.size(); i++) {
                    Librarian A = (Librarian) Employees.get(i);
                    if (A.getLibrarianID() == B.getLibrarianID()) {

                        Employees.remove(A);
                    }
                }

            FileOutputStream newOutput = new FileOutputStream("src/main/resources/Employee.dat");
            ObjectOutputStream newOut = new ObjectOutputStream(newOutput);

            for (int i = 0; i < Employees.size(); i++) {
                Librarian A =  (Employees.get(i));

                newOut.writeObject(A);
            }

    }

    public static void getAllEmp() throws IOException, ClassNotFoundException {
        try (
                FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
                ObjectInputStream input = new ObjectInputStream(fInput)
        ) {

            while (fInput.available() > 0) {

                Person A = (Person) input.readObject();
                if (A instanceof Librarian)

                    Employees.add((Librarian) A);

            }

        }
    }

    public static void addNewEmp(String name, String surname, Date date, String phoneNumber, int ID, String email, String Username, String Password, int Salary) throws IOException, ClassNotFoundException {

       if (!((new File("src/main/resources/Employee.dat")).exists())) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"));
                 FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat")
            ) {

                Librarian newLibrarian = new Librarian(name, surname, date, phoneNumber, ID, email, Username, Password, Salary);
                Employees.add(newLibrarian);
                output.writeObject(newLibrarian);


            }
        } else {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("src/main/resources/Employee.dat", true))) {
                Librarian newLib = new Librarian(name, surname, date, phoneNumber, ID, email, Username, Password, Salary);
                noHeader.writeObject(newLib);
                Employees.add(newLib);
            }


        }
    }


    public static ArrayList<Librarian> searchBt(TextField tfSearchBar) throws IOException, ClassNotFoundException {

            for(int i =0;i< Employees.size();i++) {
                Librarian A= (Librarian) Employees.get(i);
                if ((Pattern.compile(tfSearchBar.getText(), Pattern.CASE_INSENSITIVE).matcher(A.getLibrarianProperty()).find())) {
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
        Label info = new Label(lib.getLibrarianProperty());

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
            for (Librarian employee : Employees) {
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
}






