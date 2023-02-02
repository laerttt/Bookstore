package com.example.bookstoreapplication;

import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;

public class Manager extends Person implements Serializable {

    private int salary;
    public Manager(){
        super();
    }
    public Manager(String name, String surname, Date date) {
        super(name, surname, date, role.MANAGER);
    }
    public Manager(String name, String surname, Date date, String phoneNumber, String email, int salary){
        super(name, surname, date, role.MANAGER);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.salary = salary;
    }


    //getters
    public int getSalary(){
        return this.salary;
    }
    @Override
    protected String getUserName() {
        return super.userName;
    }

    @Override
    protected String getPassword() {
        return super.password;
    }
    @Override
    public String getEmail(){
        return super.email;
    }
    @Override
    public String getPhoneNumber(){
        return super.phoneNumber;
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
    protected int getAccessLevel() {
        return super.accessLevel;
    }


    //setters
    public void setSalary(int salary){
        this.salary = salary;
    }
    @Override
    public void setPhoneNumber(String number) throws InputMismatchException {
        try {                            //if the number entered matches the albanian number tamplate
            if (number.matches("\\+355\\s6[7-9]\\s\\d{2}\\s\\d{2}\\s\\d{3}"))
                super.phoneNumber = number;
            else {
                throw new InputMismatchException();
            }
        }
        catch(InputMismatchException ex){
            System.out.print("Invalid Phone Number");
        }
    }
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
    protected void setUserName(String userName) {
        super.userName = userName;
    }

    @Override
    protected void setPassword(String password) {
        super.password = password;
    }
}
