package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;

import static com.example.bookstoreapplication.LogInControls.checkLogIn;

public class LogInWindow extends Application {
    static int StageChooser = 0;
    public static  TextField Username = new TextField();
    static TextField Password = new TextField();

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
// Pane
        GridPane Pane1 = new GridPane();
        Pane1.setPadding(new Insets(20, 40, 40, 40));
        Pane1.setHgap(6);
        Pane1.setVgap(6);
// Username text
        Label txt = new Label("Enter your username :");
        txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));

        // Password text
        Label txt2 = new Label("Enter your password :");
        txt2.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15));
        ;

// Pass and User Textfield


        Button btAdd = new Button("Log In");


        btAdd.setOnAction(e -> {
            try {
                StageChooser = checkLogIn(Username.getText(), Password.getText());
                System.out.println(StageChooser);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        GridPane.setHalignment(btAdd, HPos.RIGHT);

Username.setPromptText("Username");
        Password.setPromptText("Username");
        // Adding to pane
        Pane1.add(Username, 1, 0);
        Pane1.add(Password, 1, 1);
        Pane1.add(txt, 0, 0);
        Pane1.add(txt2, 0, 1);
        Pane1.add(btAdd, 1, 3);


        Scene scene = new Scene(Pane1);
        stage.setScene(scene);
        stage.show();

        System.out.println(StageChooser);

    }

    public static void main(String[] args) {
        launch();
    }}


