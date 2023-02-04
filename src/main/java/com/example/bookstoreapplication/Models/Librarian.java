package com.example.bookstoreapplication.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;

public class Librarian extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678;

    private int salary;
    private int librarianID;
    public Librarian(){
        super();

    }
    public Librarian(String name, String surname, Date date, int ID) {
        super(name, surname, date, role.LIBRARIAN);
        this.librarianID = ID;
    }

    public Librarian(String name, String surname, Date date, String phoneNumber, int ID, String email) {
        super(name, surname, date, role.LIBRARIAN);
        this.librarianID = ID;
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
    }

    //getters
    @Override
    public String getUserName() {
        return super.userName;
    }
    @Override
    public String getPassword() {
        return super.password;
    }
    @Override
    public String getEmail() {
        return super.email;
    }

    public int getLibrarianID() {
        return this.librarianID;
    }
    @Override
    public String getPhoneNumber() {
        return super.phoneNumber;
    }

    public int getSalary() {
        return this.salary;
    }

    @Override
    protected String getName() {
        return super.name;
    }

    @Override
    protected String getSurname() {
        return super.surname;
    }

    @Override
    protected Date getBirthDate() {
        return super.birthDate;
    }
    @Override
    public int getAccessLevel() {
        return super.accessLevel;
    }

    //setters
    @Override
    public void setEmail(String email) throws InputMismatchException {
        try {                            //if the number entered matches the albanian number tamplate
            if (email.matches("\\w*@\\w*.com"))
                super.email = email;
            else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.print("Invalid Email Address");
        }
    }

    @Override
    public void setPhoneNumber(String number) throws InputMismatchException {
        try {                            //if the number entered matches the albanian number tamplate
            if (number.matches("\\+355\\s6[7-9]\\s\\d{2}\\s\\d{2}\\s\\d{3}"))
                super.phoneNumber = number;
            else {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException ex) {
            System.out.print("Invalid Phone Number");
        }
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    protected void setName(String name) {
        super.name = name;
    }

    @Override
    protected void setSurname(String surname) {
        super.surname = surname;
    }

    @Override
    protected void setBirthDate(Date birthDate) {
        super.birthDate = birthDate;
    }
    @Override
    public void setUserName(String userName) {
        super.userName = userName;
    }
    @Override
    public void setPassword(String password) {
        super.password = password;
    }



    @Override
    public String toString(){
        return this.getName()+" "+this.getUserName()+" "+this.getPassword();
    }
}
