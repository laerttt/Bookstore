package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Manager;
import com.example.bookstoreapplication.Models.Person;
import com.example.bookstoreapplication.Views.LogInWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        LogInWindow Log = new LogInWindow();
        try {
            Log.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        launch();
//        Date date = new Date();
//        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/Books.dat"));
//        BookControls.addBooks("kari","keli","romanc",1, "6967","botimeBOli", date, 690,100,1000);
//        System.out.println("added");
//                Book A;
//        A = (Book) in.readObject();
//        System.out.println(A.bookProperties());
//        Person laert = new Manager("kelvin", "gjikola",date);
//        laert.setPassword("boli123");
//        laert.setUserName("kelvinGjikola");
//        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"));
//        o.writeObject(laert);
//        System.out.println(LogInControls.checkLogIn("kelvinGJ", "nukjombol>:("));
//        LogInWindow.launch();
    }
}