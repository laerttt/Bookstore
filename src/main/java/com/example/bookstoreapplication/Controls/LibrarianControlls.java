package com.example.bookstoreapplication.Controls;
import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;
import com.example.bookstoreapplication.Views.LogInWindow;

import java.io.*;
import  java.util.ArrayList;
import java.util.Date;



public class LibrarianControlls {
    static ObjectInputStream Input;

    static {
        try {
            Input = new ObjectInputStream(new FileInputStream ("src/main/resources/Employee.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static double totalPrice=0;
static Date date= new Date();
public static ArrayList<Book> Books = new ArrayList<>();


    static double getTotalPrice(){
       return totalPrice;
    }
    /**
     *
     * @return Used on getBillButton and in Table view Bill input
     * @throws IOException
     * @throws ClassNotFoundException
     */



    public static int getCurrentLib() throws IOException, ClassNotFoundException {
        int LibId = 0;

        try (FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Person A;
                A = (Person)input.readObject();
                if(A instanceof Librarian) {
                    Librarian B = (Librarian) A;
                    System.out.println(B.getLibrarianSearchProperties());
                    if (B.getUserName().contentEquals(LogInWindow.user)) {
                        if (B.getPassword().contentEquals(LogInWindow.pass)) {
                            LibId = B.getLibrarianID();
                        }

                    }
                }
            }
            return LibId;
        }
    }
    public static ArrayList getAllLibrarians() throws IOException, ClassNotFoundException {
        ArrayList libs = new ArrayList<>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Person A= (Person) input.readObject();
                if((A) instanceof Librarian)
                libs.add(A);
            }
        }
        return libs;
    }
}
