package com.example.petrusapplication.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="productID")
    private int id;
    private String name;
    private String description;
    private String imageName;
    private int quantity;
    public Product(@NonNull int id, String name, String description,String imageName,int quantity)
    {
        this.id=id;
        this.name = name;
        this.description=description;
        this.imageName=imageName;
        this.quantity=quantity;
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
}
