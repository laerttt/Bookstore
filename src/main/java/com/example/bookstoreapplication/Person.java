package com.example.bookstoreapplication;

import java.io.Serializable;
import java.util.Date;

public abstract class Person implements Serializable {
    protected String name;
    protected String surname;
    protected Date birthDate;
    protected String phoneNumber;
    protected String email;
    protected String userName;
    protected String password;
    protected enum role{
        LIBRARIAN,
        MANAGER,
        ADMINISTRATOR,
        ROOT
    }
    protected int accessLevel = -1;


    protected Person(){
    }
    /**
     * Constructor for library employee
     * @param name;
     * @param surname;
     * @param date;
     * @param role;
     */
    protected Person(String name, String surname, Date date, role role){
        this.name = name;
        this.surname = surname;
        this.birthDate = date;
        switch(role){
            case LIBRARIAN -> this.accessLevel = 1;
            case MANAGER -> this.accessLevel = 2;
            case ADMINISTRATOR -> this.accessLevel = 3;
            case ROOT -> this.accessLevel = 0;
        }
    }
    //getters
    protected abstract int getAccessLevel();
    protected abstract String getUserName();
    protected abstract String getPassword();
    protected abstract String getEmail();
    protected abstract String getPhoneNumber();
    protected abstract String getName();
    protected abstract String getSurname();
    protected abstract Date getBirthDate();
    //setters
    protected abstract void setEmail(String email);
    protected abstract void setPhoneNumber(String number);
    protected abstract void setName(String name);
    protected abstract void setSurname(String surname);
    protected abstract void setBirthDate(Date birthDate);
    protected abstract void setUserName(String userName);
    protected abstract void setPassword(String password);
}