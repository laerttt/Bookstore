package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

    }

    public static void main(String[] args) {
        Date date = new Date();
        Person laert = new Librarian("Laert", "Huti", date,4);
    }
}