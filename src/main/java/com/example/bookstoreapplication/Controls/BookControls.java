package com.example.bookstoreapplication.Controls;

import com.example.bookstoreapplication.Models.Book;
import com.example.bookstoreapplication.NoHeader.NoHeader;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class BookControls {
    /**
     * gets books from file checks if they are 5 or more in stock
     *
     * @return ArrayList or books that are low in stock
     * @throws IOException;
     * @throws ClassNotFoundException;
     */
    public static ArrayList<Book> getLowStock() throws IOException, ClassNotFoundException {
        ArrayList<Book> lowBooks = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            System.out.println(fInput.available());
            while (fInput.available() > 0) {
                Book A;
                A = (Book) input.readObject();
                if (A.getStock() <= 5)
                    lowBooks.add(A);
            }
        }
        System.out.println(lowBooks);
        return lowBooks;
    }

    /**
     * Add books to library
     *
     * @param Title;
     * @param Author;
     * @param Category;
     * @param quantity;
     * @param ISBN;
     * @param Supplier;
     * @param purchasedDate;
     * @param purchasedPrice;
     * @param originalPrice;
     * @param sellingPrice;
     * @throws IOException;
     * @throws ClassNotFoundException;
     */
    public static void addBooks(String Title, String Author,
                                String Category, int quantity, String ISBN,
                                String Supplier, Date purchasedDate, int purchasedPrice,
                                int originalPrice, int sellingPrice) throws IOException, ClassNotFoundException {
        boolean found = false;
        if (!((new File("src/main/resources/Books.dat")).exists())) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"));
                 FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat")
            ) {
                System.out.println(fInput.available());
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                output.writeObject(newBook);
                System.out.println("try 1");
                found = true;
            }
        } else {

            ArrayList<Book> books = new ArrayList<Book>();
            try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
                 ObjectInputStream input = new ObjectInputStream(fInput);
            ) {
                System.out.println("try2 creating arraylist");
                System.out.println(fInput.available() + "i");
                while (fInput.available() > 0) {
                    Book A = (Book) input.readObject();
                    books.add(A);
                }
                for (Book book : books) {
                    System.out.println("Checking ISBN...");
                    if (book.getISBN().contentEquals(ISBN)) {
                        book.addStock(quantity);
                        found = true;
                        System.out.println("Stock added");
                    }
                }
            }
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"))) {
                for (Book book : books) {
                    output.writeObject(book);
                    System.out.println("updating...");
                }
                System.out.println("try 2 finished");
            }
        }
        if (!found) {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("src/main/resources/Books.dat", true));) {
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                noHeader.writeObject(newBook);
                System.out.println("try 3");
            }
        }
    }

    public static void addStock(String ISBN, int quantity) throws IOException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput);
        ) {
            System.out.println("try2 creating arraylist");
            System.out.println(fInput.available() + "i");
            while (fInput.available() > 0) {
                Book A = (Book) input.readObject();
                books.add(A);
            }
            for (Book book : books) {
                System.out.println("Checking ISBN...");
                if (book.getISBN().contentEquals(ISBN)) {
                    book.addStock(quantity);
                    System.out.println("Stock added");
                    break;
                }
            }
        }
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"))) {
            for (Book book : books) {
                output.writeObject(book);
                System.out.println("updating...");
            }
            System.out.println("try 2 finished");
        }
    }

    public static ArrayList<Book> getBooks() throws IOException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            System.out.println(fInput.available());
            while (fInput.available() > 0) {
                books.add((Book) input.readObject());
            }
        }
        System.out.println(books);
        return books;
    }
}