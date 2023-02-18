package com.example.bookstoreapplication.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678;
    public int billNumber;
    private int numberOfBooks;
    private ArrayList<Book> books = new ArrayList<Book>();
    private int totalPrice;
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
    public Bill(ArrayList<Book> books, Date date, int librarianID, int newPrice){
        this.numberOfBooks = books.size();
        this.date = date;
        this.books = books;
        this.librarianID = librarianID;
        this.totalPrice= newPrice;
        this.numberOfBooks = books.size();
    }
    public int getTotalPrice(){
        return this.totalPrice;

    }
    public int getBillNumber() {
        return this.billNumber;
    }
    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }
    public int getNumberOfBooks() {
        return this.numberOfBooks;
    }
    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }
    public Date getDate() {
        return this.date;
    }
    public int getLibrarianID() {
        return librarianID;
    }
    public ArrayList<Book> getBooks(){
        return books;
    }
    public String getBillProperties(){
        StringBuilder s = new StringBuilder();
        for (Book book : books){
            s.append("\n"+book.getTitle()+" - "+book.getAuthor()+"\nPrice\t"+book.getSellingPrice());
        }
        return "Bill Number: "+this.billNumber+
                "\nLibrarian ID: "+this.librarianID+
                "\nDate: "+this.date+
                "\n\nBooks:"+
                s+
                "\n\nTotal Price: "+this.totalPrice;

    }

}

