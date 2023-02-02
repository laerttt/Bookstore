package com.example.bookstoreapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements Serializable {
    public static int billNumber;
    private int numberOfBooks;
    private ArrayList<String> books = new ArrayList<String>();
    private int totalPrice=0;
    private Date date;
    Bill(){

    }
    Bill( ArrayList<String> books, Date date){
        this.numberOfBooks = books.size();
        this.date = date;
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder bill = new StringBuilder();
        bill.append("BillNo: ");
        bill.append(billNumber);
        bill.append("\n");
        bill.append("Data: ");
        bill.append(this.date);
        bill.append("\n");
        for(int i=0;i<this.books.size();i++){
            bill.append(books.get(i));
            bill.append("\t");
            i++;
            bill.append(books.get(i));
            bill.append("L");
            bill.append("\n");
            this.totalPrice+= Integer.parseInt(books.get(i));
        }
        bill.append("Totali:\t");
        bill.append(this.totalPrice);
        bill.append("L");
        return bill.toString();
    }
}

