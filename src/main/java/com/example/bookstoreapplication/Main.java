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
ObjectOutputStream Outut= new ObjectOutputStream(new FileOutputStream("Books.dat"));


Outut.writeObject(new Book("Laert Byca","bIg Gay", "ok",5,"23409587","s", new Date(), 3245,345,345));
    }
}