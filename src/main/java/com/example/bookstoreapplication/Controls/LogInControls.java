package com.example.bookstoreapplication.Controls;
import com.example.bookstoreapplication.Models.*;
import com.example.bookstoreapplication.Views.LogInWindow;

import java.io.*;


public class LogInControls {
    /**
     * Takes as args username password of log in to check if it is valid or not.
     * @param userName;
     * @param password;
     * @return accessLevel or -1 if user not found;
     * @throws IOException;
     * @throws ClassNotFoundException;
     */
    public static int checkLogIn(String userName, String password) throws IOException, ClassNotFoundException {
        try (FileInputStream finput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(finput)
        ) {
            while (finput.available() > 0) {
                Person A = (Person) input.readObject();
                if (A.getUserName().contentEquals(userName)) {
                    if (A.getPassword().contentEquals(password))
                        return A.getAccessLevel();
                    }
                }
            return -1;
        }
    }
    public static boolean checkAccess() throws IOException, ClassNotFoundException {
        try (FileInputStream fInput = new FileInputStream("src/main/resources/Employee.dat");
             ObjectInputStream input = new ObjectInputStream(fInput)
        ) {
            while (fInput.available() > 0) {
                Person A;
                A = (Person)input.readObject();
                if(A instanceof Librarian) {
                    Librarian B = (Librarian) A;
                    if (B.getUserName().contentEquals(LogInWindow.user)) {
                        if (B.getPassword().contentEquals(LogInWindow.pass)) {
                            return B.getManagerAccess();
                        }

                    }
                }
                if(A instanceof Manager){
                    Manager B = (Manager) A;
                    if (B.getUserName().contentEquals(LogInWindow.user)) {
                        if (B.getPassword().contentEquals(LogInWindow.pass)) {
                            return B.getLibrarianAccess();
                        }
                    }
                }
                if(A instanceof Administrator) {
                    Administrator B = (Administrator) A;
                    if (B.getUserName().contentEquals(LogInWindow.user)) {
                        if (B.getPassword().contentEquals(LogInWindow.pass)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
