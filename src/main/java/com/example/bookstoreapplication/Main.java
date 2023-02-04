package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Models.Book;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Date date = new Date();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/Books.dat"));
        BookControls.addBooks("kari","keli","romanc",1, "6967","botimeBOli", date, 690,100,1000);
        System.out.println("added");
                Book A;
        A = (Book) in.readObject();
        System.out.println(A.bookProperties());
//        Person laert = new Librarian("laert", "huti", date, 4);
//        laert.setPassword("boli123");
//        laert.setUserName("kelvinGjikola");
//        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"));
//        o.writeObject(laert);
//        System.out.println(LogInControls.checkLogIn("kelvinGJ", "nukjombol>:("));
//        LogInWindow.launch();
    }
}