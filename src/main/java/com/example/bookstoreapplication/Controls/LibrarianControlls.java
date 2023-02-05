package com.example.bookstoreapplication.Controls;
import com.example.bookstoreapplication.Models.Bill;
import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.Models.Librarian;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import  java.util.ArrayList;
import java.util.Date;
import java.util.*;


public class LibrarianControlls {
    static ObjectInputStream Input;

    static {
        try {
            Input = new ObjectInputStream(new FileInputStream ("Employee.dat"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static double totalPrice=0;
static Date date= new Date();
public static ArrayList<Book> Books = new ArrayList<>();

    static double bookTotal() throws IOException, ClassNotFoundException {

       Book a = (Book) (Input.readObject());
       totalPrice = a.getSellingPrice();
       return totalPrice+totalPrice;
    }
    static double getTotalPrice(){

       return totalPrice;
    }
    /**
     *
     * @return Used on getBillButton and in Table view Bill input
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static Bill getBill(ArrayList Books) throws IOException, ClassNotFoundException {
      Bill A= new Bill(Books,date,33333,getTotalPrice());
        return A;
    }



public static String getBookTitle(int i){

        Book A= (Book)(Books.get(i));

        return A.getTitle();
}
    public static int getBookPrice(int i){

        Book A= (Book)(Books.get(i));

        return A.getSellingPrice();
    }
    public static String getBookAuthor(int i){

        Book A= (Book)(Books.get(i));

        return A.getAuthor();
    }


}
