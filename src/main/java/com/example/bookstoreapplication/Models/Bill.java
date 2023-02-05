package com.example.bookstoreapplication.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678;
    public static int billNumber;
    private int numberOfBooks;
    private ArrayList<String> books = new ArrayList<String>();
    private double totalPrice=0;
    private Date date;
    private int librarianID;
    Bill(){

    }

    /**
     * Enter librarian ID books and date to print the order bill
     * @param books;
     * @param date;
     * @param librarianID;
     */
    Bill( ArrayList<String> books, Date date,int librarianID,double newPrice){
        this.numberOfBooks = books.size();
        this.date = date;
        this.books = books;
        this.librarianID = librarianID;
<<<<<<< HEAD:src/main/java/com/example/bookstoreapplication/Bill.java
        this.totalPrice= newPrice;
=======
        this.numberOfBooks = books.size();
>>>>>>> main:src/main/java/com/example/bookstoreapplication/Models/Bill.java
    }






}

