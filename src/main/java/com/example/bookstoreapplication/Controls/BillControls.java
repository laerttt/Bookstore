package com.example.bookstoreapplication.Controls;

import com.example.bookstoreapplication.Models.Bill;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
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
    public static void saveTxt(Bill bill, Stage stage) throws IOException {
        FileChooser fch = new FileChooser();
        fch.setInitialFileName("Bill"+bill.getBillNumber()+".txt");
        File file = fch.showSaveDialog(stage);

        if(file!=null){
            try(FileWriter output = new FileWriter(file.getAbsolutePath());){
                output.write(bill.getBillProperties());
            }

        }
    }
    public static int billCounter() throws IOException {
        int x;
        try(FileInputStream input = new FileInputStream("src/main/resources/Count.dat")){
            x = input.read();
        }
        x++;
        try(FileOutputStream output = new FileOutputStream("src/main/resources/Count.dat")){
            output.write(x);
        }
        return x;
    }
}
