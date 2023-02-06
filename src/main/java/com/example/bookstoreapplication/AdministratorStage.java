package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        FileOutputStream fOutput = new FileOutputStream("src/main/resources/Employee.dat");
        ObjectOutputStream Output = new ObjectOutputStream(fOutput);
        Date date = null;
        Output.writeObject(new Librarian("Lali","Lali",date,"0697722030",3,"Lali@gmail.com"));
        Output.writeObject(new Librarian("Keli","Lali",date,"0697722030",6,"Lali@gmail.com"));
System.out.print(toBeDeleted);
        getAllEmp();
        Stage x= deleteEmployee();
        x.show();
    }

    public Stage deleteEmployee() {

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

        return stage;
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


}
