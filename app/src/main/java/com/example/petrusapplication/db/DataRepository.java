package com.example.petrusapplication.db;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.petrusapplication.db.PetrusDatabase;
import com.example.petrusapplication.db.Product;
import com.example.petrusapplication.db.ProductDao;
import com.example.petrusapplication.db.Service;
import com.example.petrusapplication.db.ServiceDao;

import java.util.List;

public class DataRepository {
    private ProductDao mProductDao;
    private ServiceDao mServiceDao;
    private LiveData<List<Product>> mAllProducts;
    private LiveData<List<Service>> mAllServices;
    public DataRepository(Application application) {
        PetrusDatabase db = PetrusDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mServiceDao = db.serviceDao();
        mAllProducts = mProductDao.getAllProducts();
        mAllServices = mServiceDao.getAllServices();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    public LiveData<List<Service>> getAllServices() {
        return mAllServices;
    }

    public LiveData<List<Service>> getAllServicesByFilter(String filterField, String filterValue) {
        if(filterField.equals("location")){
            return mServiceDao.getAllServicesByLocationFilter(filterValue);
        }else if (filterField.equals("category")){
            return mServiceDao.getAllServicesByCategoryFilter(filterValue);
        }else if (filterField.equals("species")){
            return mServiceDao.getAllServicesBySpeciesFilter(filterValue);
        }
        return mAllServices;
    }

    void insert(Product product) {
        PetrusDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insert(product);
        });
    }

    void insert(Service service) {
        PetrusDatabase.databaseWriteExecutor.execute(() -> {
            mServiceDao.insert(service);
        });
    }
}
