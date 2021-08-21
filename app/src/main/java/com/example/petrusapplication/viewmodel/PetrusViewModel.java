package com.example.petrusapplication.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.petrusapplication.db.DataRepository;
import com.example.petrusapplication.db.Product;
import com.example.petrusapplication.db.Service;

import java.util.List;

public class PetrusViewModel extends AndroidViewModel {

    private DataRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private final LiveData<List<Product>> mAllProducts;
    private final LiveData<List<Service>> mAllServices;

    public PetrusViewModel(Application application) {
        super(application);
        mRepository = new DataRepository(application);
        mAllProducts = mRepository.getAllProducts();
        mAllServices = mRepository.getAllServices();
    }

    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }
    public LiveData<List<Service>> getAllServices() {
        return mAllServices;
    }

}
