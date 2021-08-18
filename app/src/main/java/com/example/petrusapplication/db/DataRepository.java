package com.example.petrusapplication.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.petrusapplication.db.PetrusDatabase;
import com.example.petrusapplication.db.Product;
import com.example.petrusapplication.db.ProductDao;

import java.util.List;

public class DataRepository {
    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;
    public DataRepository(Application application) {
        PetrusDatabase db = PetrusDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    void insert(Product product) {
        PetrusDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insert(product);
        });
    }
}
