package com.example.petrusapplication.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "service_table")
public class Service implements Serializable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name="serviceID")
    private int id;
    private String name;
    private String description;
    private float price;
    private String imageName;
    private String email;
    private String listingDate;
    private String category;
    private String species;
    private String location;

    public Service(@NonNull int id, String name, String description, float price, String imageName,  String email, String listingDate, String category, String species, String location)
    {
        this.id=id;
        this.name = name;
        this.description=description;
        this.imageName=imageName;
        this.price=price;
        this.email=email;
        this.listingDate=listingDate;
        this.category=category;
        this.species=species;
        this.location=location;
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

    @NonNull
    public float getPrice(){return this.price;}

    @NonNull
    public String getEmail() {
        return this.email;
    }

    @NonNull
    public String getListingDate() {
        return listingDate;
    }

    public String getCategory() {
        return category;
    }

    public String getSpecies() {
        return species;
    }

    @NonNull
    public String getLocation() {
        return location;
    }
}
