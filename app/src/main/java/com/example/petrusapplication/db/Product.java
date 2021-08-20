package com.example.petrusapplication.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class Product implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="productID")
    private int id;
    private String name;
    private String description;
    private String imageName;
    private int quantity;
    private String email;
    public Product(@NonNull int id, String name, String description,String imageName,int quantity,String email)
    {
        this.id=id;
        this.name = name;
        this.description=description;
        this.imageName=imageName;
        this.quantity=quantity;
        this.email=email;
    }
    @NonNull
    public int getId() {
        return this.id;
    }
    @NonNull
    public String getName() {
        return this.name;
    }
    @NonNull
    public String getDescription() {
        return this.description;
    }
    @NonNull
    public String getImageName() {
        return this.imageName;
    }
    public int getQuantity(){return this.quantity;}
    public String getEmail() {
        return this.email;
    }
}
