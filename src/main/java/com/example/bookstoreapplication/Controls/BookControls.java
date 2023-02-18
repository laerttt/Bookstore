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
            while (fInput.available() > 0) {
                Book A;
                A = (Book) input.readObject();
                if (A.getStock() <= 5)
                    lowBooks.add(A);
            }
        }
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
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                newBook.addBought(quantity);
                output.writeObject(newBook);
                found = true;
            }
        } else {

            ArrayList<Book> books = new ArrayList<>();
            try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
                 ObjectInputStream input = new ObjectInputStream(fInput);
            ) {
                while (fInput.available() > 0) {
                    Book A = (Book) input.readObject();
                    books.add(A);
                }
                for (Book book : books) {
                    if (book.getISBN().contentEquals(ISBN)) {
                        book.addStock(quantity);
                        found = true;
                    }
                }
            }
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"))) {
                for (Book book : books) {
                    output.writeObject(book);
                }
            }
        }
        if (!found) {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("src/main/resources/Books.dat", true));) {
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                newBook.addBought(quantity);
                noHeader.writeObject(newBook);
            }
        }
    }

    public static void addStock(String ISBN, int quantity) throws IOException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput);
        ) {
            while (fInput.available() > 0) {
                Book A = (Book) input.readObject();
                books.add(A);
            }
            for (Book book : books) {
                if (book.getISBN().contentEquals(ISBN)) {
                    book.addStock(quantity);
                    break;
                }
            }
        }
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/Books.dat"))) {
            for (Book book : books) {
                output.writeObject(book);
            }
        }
    }

    public static ArrayList<Book> getBooks() throws IOException, ClassNotFoundException {
        ArrayList<Book> books = new ArrayList<Book>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                books.add((Book) input.readObject());
            }
        }
        return books;
    }
    public static void removeBook(Book book){
        ArrayList<Book> books = new ArrayList<>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Books.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)) {
            while(fInput.available()>0){
                Book A = (Book)input.readObject();
                if(A.getISBN().contentEquals(book.getISBN()))
                    A.removeStock(1);
                books.add(A);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try(FileOutputStream fOutput = new FileOutputStream("src/main/resources/Books.dat");
            ObjectOutputStream output = new ObjectOutputStream(fOutput)){
            for(Book bok : books){
                output.writeObject(bok);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}