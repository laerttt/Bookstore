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
        Person laert = new Librarian("Laert", "Huti", date,4);
        laert.setUserName("laertHuti");
        laert.setPassword("123");
        Person kelvin = new Manager("kelvin", "gjik", date);
        kelvin.setUserName("kelvingj");
        kelvin.setPassword("123");
        Person ang = new Administrator("angel", "l", date);
        ang.setUserName("angell");
        ang.setPassword("123");
        ArrayList<Person> employee = new ArrayList<>();
        employee.add(laert);
        employee.add(kelvin);
        employee.add(ang);
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Employee.dat"))
        ){
            for (Person person : employee)
                output.writeObject(person);
        }
        System.out.println(LogInControls.checkLogIn("laetHuti","123"));
    }
}