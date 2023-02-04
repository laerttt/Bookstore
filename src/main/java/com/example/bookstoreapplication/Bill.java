package com.example.bookstoreapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements Serializable {
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
        this.totalPrice= newPrice;
    }






}

