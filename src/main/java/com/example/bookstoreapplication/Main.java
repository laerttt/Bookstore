package com.example.bookstoreapplication;

import com.example.bookstoreapplication.Controls.BookControls;
import com.example.bookstoreapplication.Models.*;
import com.example.bookstoreapplication.NoHeader.NoHeader;
import com.example.bookstoreapplication.Views.LogInWindow;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
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
//        try(ObjectOutputStream n = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"))){
//            for(int i=1;i<10000;i++)
//                n.writeObject(new Book("Book "+i, "Author "+i, "Category "+i,
//                        20,String.valueOf((long)(100*((Math.random()+1)*1000000000))),
//                        "Supplier "+i,new Date(),500,700,1000));
//        }
        Person a = new Administrator("Admin","Admin",new Date());
        a.setUserName("admin");
        a.setPassword("123");
        Person m = new Manager("Manager","Manager",new Date());
        m.setUserName("man");
        m.setPassword("123");
        Person l = new Librarian("Librarian","Librarian",new Date(),1);
        l.setUserName("lib");
        l.setPassword("123");
        try(ObjectOutputStream n = new ObjectOutputStream(new FileOutputStream("src/main/resources/Employee.dat"))){
            n.writeObject(a);
            n.writeObject(m);
            n.writeObject(l);
        }
        ArrayList<Person> p = new ArrayList<>();
        try (FileInputStream finput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(finput)
        ) {
            System.out.print(finput.available());
            while (finput.available() > 0) {
                p.add((Person) input.readObject());
            }
        }
        System.out.println(p);
        launch();
//    }
    }
}