package com.example.bookstoreapplication.Controls;
import com.example.bookstoreapplication.Models.Bill;
import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;
import com.example.bookstoreapplication.Models.Person;

import java.io.*;
import  java.util.ArrayList;
import java.util.Date;
import java.util.*;

import static com.example.bookstoreapplication.Views.LogInWindow.tfPass;
import static com.example.bookstoreapplication.Views.LogInWindow.tfUsrN;


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

    public static Bill getBill(ArrayList Books,int Price) throws IOException, ClassNotFoundException {
      Bill A= new Bill(Books,date,getCurrentLib(),Price);
      return A;
    }

    public static int getCurrentLib() throws IOException, ClassNotFoundException {
        String Username= tfUsrN.getText();
        String Password= tfPass.getText();
        int LibId = 0;

        try (FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Person A;
                A = (Person)input.readObject();
                if(A instanceof Librarian) {
                    if (A.getUserName().contentEquals(Username)) {
                        if (A.getPassword().contentEquals(Password)) {
                            LibId =((Librarian)A).getLibrarianID();
                        }

                    }
                }
            }
            return LibId;
        }
    }

}
