package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;
import com.example.bookstoreapplication.NoHeader.NoHeader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class AdministratorStage extends Application {
   Button DeleteEmp= new Button("Delte Selected Employees");
    public static TableView AllEmp = new TableView<>();
    public static ArrayList Employees = new ArrayList<>();
    public static ArrayList toBeDeleted = new ArrayList<>();
    public static Button btAdd= new Button("Add Employee");
    public  static ArrayList newEmp= new ArrayList<>();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        Stage stage = new Stage();

        TableColumn<Librarian, String> NameCol = new TableColumn<Librarian, String>("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("name"));
        TableColumn<Librarian, String> SurnCol = new TableColumn<Librarian, String>("Surname");
        SurnCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("surname"));
        TableColumn<Librarian, Integer> Id = new TableColumn<Librarian, Integer>("LibrarianID");
        Id.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("librarianID"));

        TableColumn<Librarian, String> PHN = new TableColumn<Librarian, String>("Phone Number");
        PHN.setCellValueFactory(new PropertyValueFactory<Librarian, String>("phoneNumber"));
        AllEmp.getColumns().addAll(NameCol, SurnCol,Id,PHN);
        for(int i=0;i<Employees.size();i++) {
            AllEmp.getItems().add(Employees.get(i));
        }
        AllEmp.setOnMouseClicked(e -> {
            toBeDeleted.add((Librarian) AllEmp.getSelectionModel().getSelectedItem());
        });
        DeleteEmp.setOnAction(e->{
            try {
                try {
                    deleteEmp();
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        GridPane Pane= new GridPane();
        Pane.add(AllEmp,0,0);
        Pane.add(DeleteEmp,0,1);
        Scene scene = new Scene(Pane);

        stage.setScene(scene);

    }
public static GridPane addingEmpPane(){

        GridPane AddPane= new GridPane();
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
    TextField tfBirthdate = new TextField();
    tfBirthdate.setPromptText("Birthdate");
    TextField tfsearchEmp = new TextField();
    tfsearchEmp.setPrefWidth(500);
    tfsearchEmp.setPromptText("\t\t\t\t\t\t\t Enter Name/Surname/LibrarianID/Username");
    btAdd.setOnAction(k -> {
        try {
            addNewEmp(tfName.getText(),
                    tfSurname.getText(),
                    new Date(),
                    tfPhoneNumber.getText(),
                    Integer.parseInt(tfLibrarianID.getText()),
                    tfEmail.getText(),
                    tfPassword.getText(),
                    tfUsername.getText(),
                    Integer.parseInt(tfSalary.getText()));
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
                tfName.clear();
                tfSurname.clear();
                tfPhoneNumber.clear();
                tfBirthdate.clear();
                tfEmail.clear();
                tfLibrarianID.clear();
                tfPhoneNumber.clear();
                tfUsername.clear();
                tfPassword.clear();
                tfsearchEmp.clear();


    });












    //Labels
    Label lbTitle = new Label("Name:");
    Label lbAuthor = new Label("Surname:");
    Label lbCategory = new Label("LibrarianID:");
    Label lbQuantity = new Label("Email:");
    Label lbISBN = new Label("Username:");
    Label lbSupplier = new Label("Password:");
    Label lbPurchasePrice = new Label("Salary");
    Label lbSellPrice = new Label("BirthDate:");
    Label lbOrgPrice = new Label("Phone Number:");




    return AddPane;
    }



    public static void deleteEmp() throws IOException, ClassNotFoundException {
        try (FileOutputStream fOutput = new FileOutputStream("src/main/resources/Employee.dat");
             ObjectOutputStream Output = new ObjectOutputStream(fOutput);
             FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {

            for(int j=0;j<toBeDeleted.size();j++) {
    for (int i = 0; i < Employees.size(); i++) {
        Librarian A = (Librarian) Employees.get(i);
        Librarian B = (Librarian) toBeDeleted.get(j);
        if (A.getLibrarianID() == B.getLibrarianID()) {
            Employees.remove(i);
        }
    }
}

            fInput.close();
            FileOutputStream newOutput = new FileOutputStream("src/main/resources/Employee.dat");
            ObjectOutputStream newOut = new ObjectOutputStream(newOutput);
            FileInputStream naur = new FileInputStream("src/main/resources/Employee.dat");
            ObjectInputStream naaur = new ObjectInputStream(naur);
            for (int i = 0; i < Employees.size(); i++) {
               Librarian A=(Librarian) (Employees.get(i));
               newOut.writeObject(A);
            }

        Librarian A= (Librarian) naaur.readObject();
            System.out.print(A);
        }
    }
    public static void getAllEmp() throws IOException, ClassNotFoundException {
        try (
             FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            System.out.print("b");
            while (fInput.available() > 0) {
                System.out.print("b");
                Person A= (Person) input.readObject();
                if(A instanceof Librarian)
                Employees.add(A);
                System.out.print("a");
            }

        }
    }

    public static void addNewEmp(String name, String surname, Date date, String phoneNumber, int ID, String email,String Username, String Password,int Salary) throws IOException, ClassNotFoundException {
        boolean found = false;
        if (!((new File("src/main/resources/Employee.dat")).exists())) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"));
                 FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat")
            ) {

                Librarian newLibrarian = new Librarian(name,surname,date,phoneNumber,ID,email,Username,Password,Salary);
                output.writeObject(newLibrarian);

                found = true;
            }
        } else {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("src/main/resources/Librarian.dat", true));) {
            Librarian newLib = new Librarian(name,surname,date,phoneNumber,ID,email,Username,Password,Salary);
            noHeader.writeObject(newLib);

            }


            }
        }
    }







