package com.example.bookstoreapplication.Views;

import com.example.bookstoreapplication.Controls.LogInControls;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class LogInWindow extends Application {
    public static String user;
    public static String pass;
    @Override
    public void start(Stage LogInStage) throws Exception {
        //stages
        Stage warningStage = new Stage();

        //create pane and stylize
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(50,20,10,20));
        gridPane.setStyle("-fx-font-size: 15px;");
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20,20,20,20));

        //labels
        Label lbUserName = new Label("UserName:");
        Label lbPassword = new Label("Password:");

        //text
        Text warningText = new Text("Invalid Username/Password!");
        warningText.setStyle("-fx-font-size: 15px;");

        //textFields
        TextField tfUsrN = new TextField();
        PasswordField tfPass = new PasswordField();
        tfUsrN.setPromptText("UserName");
        tfPass.setPromptText("Password");

        //buttons
        Button btcloseWarning = new Button("Close");
        btcloseWarning.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
        Button btLogIn = new Button("Log In");
        btLogIn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
        //listner

        //events
        btcloseWarning.setOnAction(e->warningStage.close());
        btLogIn.setOnAction(e-> {
            user = tfUsrN.getText();
            pass = tfPass.getText();
            tfUsrN.clear();
            tfPass.clear();
            switch (log(user,pass)){
                case 1 -> {LibrarianStage l = new LibrarianStage();
                    try {
                        l.start(new Stage());
                        LogInStage.close();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case 2 -> {ManagerView l = new ManagerView();
                    try {
                        l.start(new Stage());
                        LogInStage.close();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case -1 ->{
                    warningStage.show() ;
                }
            }
        });
        tfPass.setOnKeyPressed(e -> {

            if(e.getCode() == KeyCode.ENTER) {
                user = tfUsrN.getText();
                pass = tfPass.getText();
                tfUsrN.clear();
                tfPass.clear();
                switch (log(user,pass)) {
                    case 1 -> {
                        LibrarianStage l = new LibrarianStage();
                        try {
                            l.start(new Stage());
                            LogInStage.close();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case 2 -> {
                        ManagerView l = new ManagerView();
                        try {
                            l.start(new Stage());
                            LogInStage.close();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case 3 ->{
                        AdministratorStage a = new AdministratorStage();
                        try {
                            a.start(new Stage());
                            LogInStage.close();
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    case -1 -> {
                        warningStage.show();
                    }
                }
            }
        });
        //arrangements
        gridPane.add(lbUserName,0,0);
        gridPane.add(tfUsrN,1,0);
        gridPane.add(lbPassword,0,1);
        gridPane.add(tfPass,1,1);
        gridPane.add(btLogIn,1,2);
        GridPane.setHalignment(btLogIn,HPos.RIGHT);

        borderPane.setBottom(btcloseWarning);
        borderPane.setCenter(warningText);
        BorderPane.setAlignment(btcloseWarning,Pos.CENTER_RIGHT);
        //scene
        Scene warningScene = new Scene(borderPane,220,100);
        Scene scene = new Scene(gridPane);

        warningStage.setResizable(false);
        warningStage.setScene(warningScene);
        warningStage.setTitle("Invalid input");
        warningStage.initOwner(LogInStage);
        warningStage.initModality(Modality.APPLICATION_MODAL);
        LogInStage.setScene(scene);
        LogInStage.setResizable(false);
        LogInStage.setTitle("Bookstore(Log In)");
        LogInStage.show();

    }
    public static int log(String u, String p) {
        int x = -2;
        try {
            x = LogInControls.checkLogIn(u, p);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return x;

    }
}


