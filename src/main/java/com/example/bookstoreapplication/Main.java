package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Date date = new Date();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Books.dat"));
//        ManagerControls.addBooks("kari","keli","romanc",1, "6967","botimeBOli", date, 690,100,1000);
//        System.out.println("added");
                Book A = new Book();
        A = (Book) in.readObject();
        System.out.println(A.bookProperties());


    }
}