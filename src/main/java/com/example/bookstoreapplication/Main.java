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
        ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"));
        FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
        Book A =  new Book();
        Book B = new Book();
   output.writeObject(A);
        output.writeObject(B);

    }
}