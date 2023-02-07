package com.example.bookstoreapplication.Controls;

import com.example.bookstoreapplication.Models.Bill;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class BillControls {
    public static ArrayList<Bill> getAllBills() throws IOException, ClassNotFoundException {
        ArrayList<Bill> bills = new ArrayList<>();
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Bills.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ){
            while (fInput.available() > 0) {
                bills.add((Bill) input.readObject());
            }
        }
        return bills;
    }
}
