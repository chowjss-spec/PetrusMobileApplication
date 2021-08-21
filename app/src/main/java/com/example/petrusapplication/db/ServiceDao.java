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

    @Query("DELETE FROM service_table")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Service service);
}
