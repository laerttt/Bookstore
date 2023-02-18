package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Models.*;
import com.example.bookstoreapplication.Views.LogInWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

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
    }
}