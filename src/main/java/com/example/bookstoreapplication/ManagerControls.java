package com.example.bookstoreapplication;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ManagerControls {
    /**
     * gets books from file checks if they are 5 or more in stock
     *
     * @return ArrayList or books that are low in stock
     * @throws IOException;
     * @throws ClassNotFoundException;
     */
    public static ArrayList<Book> getLowStock() throws IOException, ClassNotFoundException {
        ArrayList<Book> lowBooks = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Book A = new Book();
                A = (Book) input.readObject();
                if (A.getStock() >= 5)
                    lowBooks.add(A);
            }
        }
        return lowBooks;
    }

    public static boolean addBooks(String Title, String Author, String Category, int quantity, String ISBN, String Supplier, Date purchasedDate, int purchasedPrice, int originalPrice, int sellingPrice) throws IOException, ClassNotFoundException{
        try (FileInputStream fInput = new FileInputStream("Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput);
             NoHeader output = new NoHeader(new FileOutputStream("Books.dat", true));
        ) {
            while (fInput.available() > 0) {
                Book A = new Book();
                A = (Book) input.readObject();
                if (A.getISBN().contentEquals(ISBN)) {
                    A.addStock(quantity);
                    return true;
                }
            }
            Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
            output.writeObject(newBook);
        }
        return true;
    }
}
