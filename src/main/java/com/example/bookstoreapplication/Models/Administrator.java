package com.example.bookstoreapplication.Models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;

public class Administrator extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678;
    private int salary;
    public Administrator(){
        super();
    }
    public Administrator(String name, String surname, Date date,String Username,String Password) {
        super(name, surname, date, role.ADMINISTRATOR,Username,Password);
    }

    public Administrator(String name, String surname, Date date, String phoneNumber, String email,String Username,String Password) {
        super(name, surname, date, role.ADMINISTRATOR,Username,Password);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }




    //getters
    @Override
    public String getPassword() {
        return super.password;
    }
    @Override
    public String getUserName() {
        return super.userName;
    }

    @Override
    public String getEmail() {
        return super.email;
    }
    @Override
    public String getPhoneNumber() {
        return super.phoneNumber;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public String getSurname() {
        return super.surname;
    }

    @Override
    public Date getBirthDate() {
        return super.birthDate;
    }

    @Override
    public int getAccessLevel() {
        return super.accessLevel;
    }

    //setters
    @Override
    public void setEmail(String email) throws InputMismatchException {
        try {                            //if the number entered matches the albanian number tamplate
            if (email.matches("\\w*@\\w*.com"))
                super.email = email;
            else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            Stage o = new Stage();
            Text t = new Text("Invalid Email Address");
            t.setStyle("-fx-font-size: 15px;");
            Button b = new Button("Close");
            b.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
            BorderPane p = new BorderPane();
            p.setPadding(new Insets(10,10,10,10));
            p.setBottom(b);
            p.setCenter(t);
            BorderPane.setAlignment(b, Pos.CENTER_RIGHT);
            Scene s = new Scene(p,220,100);
            o.setScene(s);
            o.setResizable(false);
            o.initModality(Modality.APPLICATION_MODAL);
            o.show();
            b.setOnAction(z -> o.close());
        }
    }

    @Override
    public void setPhoneNumber(String number) throws InputMismatchException {
        try {                            //if the number entered matches the albanian number tamplate
            if (number.matches("\\+355\\s6[7-9]\\s\\d{2}\\s\\d{2}\\s\\d{3}"))
                super.phoneNumber = number;
            else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            Stage o = new Stage();
            Text t = new Text("Invalid Phone Number");
            t.setStyle("-fx-font-size: 15px;");
            Button b = new Button("Close");
            b.setStyle("-fx-background-color: darkred;-fx-text-fill: white;");
            BorderPane p = new BorderPane();
            p.setPadding(new Insets(10,10,10,10));
            p.setBottom(b);
            p.setCenter(t);
            BorderPane.setAlignment(b, Pos.CENTER_RIGHT);
            Scene s = new Scene(p,220,100);
            o.setScene(s);
            o.setResizable(false);
            o.initModality(Modality.APPLICATION_MODAL);
            o.show();
            b.setOnAction(z -> o.close());
        }
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void setName(String name) {
        super.name = name;
    }

    @Override
    public void setSurname(String surname) {
        super.surname = surname;
    }

    @Override
    public void setBirthDate(Date birthDate) {
        super.birthDate = birthDate;
    }

    @Override
    public void setUserName(String userName) {
        super.userName = userName;
    }

    @Override
    public void setPassword(String password) {
        super.password = password;
    }
}

