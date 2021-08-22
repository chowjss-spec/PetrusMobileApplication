package com.example.petrusapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServiceDao {
    @Query("SELECT * FROM service_table")
    LiveData<List<Service>> getAllServices();

    //Query by location
    @Query("SELECT * FROM service_table WHERE location = :filterValue")
    LiveData<List<Service>> getAllServicesByLocationFilter(String filterValue);

    //Query by category
    @Query("SELECT * FROM service_table WHERE category = :filterValue")
    LiveData<List<Service>> getAllServicesByCategoryFilter(String filterValue);

    //Query by species
    @Query("SELECT * FROM service_table WHERE species = :filterValue")
    LiveData<List<Service>> getAllServicesBySpeciesFilter(String filterValue);

    @Query("DELETE FROM service_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Service service);
}
