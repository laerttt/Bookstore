package com.example.bookstoreapplication;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

    }

    public static void main(String[] args) {
        Date date = new Date();
//        Person laert = new Librarian("Laert", "Huti", date,4);

        ArrayList<String> test = new ArrayList<>();
        test.add("Përse me tërheq seksi i njëjtë\nAutori: Kelvin Gjikola");
        test.add("3000");
        test.add("Boli dhe sufllaqja. Si të ushqehemi shëndetshëm?\nAutori: Kelvin Gjikola");
        test.add("2000");
        test.add("Java coding dhe sufllaqet e Oles\nAutori: Kelvin Gjikola");
        test.add("4500");
        test.add("Ndihmë u bera gej\nAutori: Kelvin Gjikola");
        test.add("2250");
        Bill bill = new Bill(test, date);
        System.out.print(bill.toString());

    }
}