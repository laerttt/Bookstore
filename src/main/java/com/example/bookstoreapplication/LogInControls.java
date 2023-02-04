package com.example.bookstoreapplication;

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
    static int checkLogIn(String userName, String password) throws IOException, ClassNotFoundException {
        try (FileInputStream finput = new FileInputStream("Employee.dat");
             ObjectInputStream input = new ObjectInputStream(finput)
        ) {
            while (finput.available() > 0) {
                new Librarian();
                Person A;
                A = (Person) input.readObject();
                if (A.getUserName().contentEquals(userName)) {
                    if (A.getPassword().contentEquals(password))
                        return A.getAccessLevel();
                    }
                }
            return -1;
        }
    }
}
