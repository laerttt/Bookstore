package com.example.bookstoreapplication.Models;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 12345678;

    private String ISBN;
    private String title;
    public String author;
    private String category;
    private String supplier;
    private int purchasedPrice;
    private int originalPrice;
    private int sellingPrice;
    private int stock;
    private Date purchasedDate;
    private int bought=0;
    private int sold=0;
    public Book(){
    }
    public Book(String Title, String Author, String Category, int quantity, String ISBN, String Supplier, Date purchasedDate, int purchasedPrice, int originalPrice, int sellingPrice){
        this.title = Title;
        this.author = Author;
        this.category = Category;
        this.supplier = Supplier;
        this.purchasedDate = purchasedDate;
        this.purchasedPrice = purchasedPrice;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        this.stock = quantity;
        this.ISBN = ISBN;
    }
public Book(String Title,String Author,int sellingPrice){
        this.title=Title;
        this.author=Author;
        this.sellingPrice=sellingPrice;
}
    // setters
    public void addBought(int x){
        this.bought += x;
    }
    public void addSold(int x){
        this.sold += x;
    }
    public void setSellingPrice(int x){
        this.sellingPrice = x;
    }
    public void setOriginalPrice(int x){
        this.originalPrice = x;
    }
    public void setPurchasedPrice(int x){
        this.purchasedPrice = x;
    }
    public void setSupplier(String x){
        this.supplier = x;
    }
    public void setStock(int x){
        this.stock = x;
    }
    public void setCategory(String x){
        this.category = x;
    }
    // getters
    public int getBought(){
        return this.bought;
    }
    public int getSold(){
        return this.sold;
    }
    public Date getPurchasedDate(){
        return purchasedDate;
    }
    public int getSellingPrice(){
        return this.sellingPrice;
    }
    public int getOriginalPrice(){
        return this.originalPrice;
    }
    public int getPurchasedPrice(){
        return this.purchasedPrice;
    }
    public String getSupplier(){
        return this.supplier;
    }
    public String getCategory(){
        return this.category;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getTitle(){
        return this.title;
    }
    public String getISBN(){
        return this.ISBN;
    }
    public int getStock(){
        return this.stock;
    }

    public void addStock(int x){
        this.stock+=x;
        this.bought+=x;
    }
    public void removeStock(int x){this.stock-=x; this.sold+=x;}
    public String getBookProperties(){
        return this.title+" - " +this.author
                +"\nCategory: "+this.category+
                "\nISBN: "+this.ISBN+
                "\nIn Stock: "+this.stock+
                "\nSupplier: "+this.supplier+
                "\nSelling Price: "+this.sellingPrice;
    }
    public String getBookSearchProperties(){
        return this.title+this.author +this.category+this.supplier+this.ISBN;
    }
}

