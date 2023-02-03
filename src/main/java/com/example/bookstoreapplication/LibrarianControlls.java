package com.example.bookstoreapplication;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import  java.util.ArrayList;
import java.util.Date;
import java.util.*;

import static com.example.bookstoreapplication.LogInWindow.Username;



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
public static ArrayList Books = new ArrayList<>();

    static double bookTotal() throws IOException, ClassNotFoundException {

       Book a = (Book) (Input.readObject());
       totalPrice = a.getSellingPrice();
       return totalPrice+totalPrice;
    }

    /**
     * Each time button Add Book To Bill is pressed
     * @param A
     */
   static void addBooksToBill(Book A){
        Books.add(A);

   }


    static double getTotalPrice(){

       return totalPrice;
    }

static int billLibrarianId() throws IOException, ClassNotFoundException {

    boolean Cond=true;
    int LibId;
    while(Cond){

            Librarian A =(Librarian) (Input.readObject());
            if(A.getUserName()==Username.getText()){
             LibId=A.getLibrarianID();
                return LibId;
            }

        }

    return -1;
    }

    /**
     *
     * @return Used on getBillButton and in Table view Bill input
     * @throws IOException
     * @throws ClassNotFoundException
     */

    static Bill getBill() throws IOException, ClassNotFoundException {
      Bill A= new Bill(Books,date,billLibrarianId());
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
