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

    /**
     * Add books to library
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
                                   int originalPrice, int sellingPrice) throws IOException, ClassNotFoundException{
        boolean found = false;
        if(!((new File("Books.dat")).exists())) {
            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Books.dat"));
                 FileInputStream fInput = new FileInputStream("Books.dat")
            ) {
                System.out.println(fInput.available());
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                output.writeObject(newBook);
                System.out.println("try 1");
                found = true;
            }
        }
        else {

            ArrayList<Book> books = new ArrayList<Book>();
            try (FileInputStream fInput = new FileInputStream("Books.dat");
                 ObjectInputStream input = new ObjectInputStream(fInput);
            ) {
                System.out.println("try2 creating arraylist");
                System.out.println(fInput.available()+"i");
                while (fInput.available() > 0) {
                    Book A = (Book) input.readObject();
                    books.add(A);
                }
                for(Book book : books){
                    System.out.println("Checking ISBN...");
                    if(book.getISBN().contentEquals(ISBN)){
                        book.addStock(quantity);
                        found = true;
                        System.out.println("Stock added");
                    }
                }
            }
            try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Books.dat"))){
                for(Book book : books){
                    output.writeObject(book);
                    System.out.println("updating...");
                }
                System.out.println("try 2 finished");
            }
        }
        if(!found) {
            try (NoHeader noHeader = new NoHeader(new FileOutputStream("Books.dat", true));) {
                Book newBook = new Book(Title, Author, Category, quantity, ISBN, Supplier, purchasedDate, purchasedPrice, originalPrice, sellingPrice);
                noHeader.writeObject(newBook);
                System.out.println("try 3");
            }
        }
    }

}
