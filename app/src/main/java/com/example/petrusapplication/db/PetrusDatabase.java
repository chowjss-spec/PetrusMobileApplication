package com.example.petrusapplication.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
abstract class PetrusDatabase extends RoomDatabase {
    abstract ProductDao productDao();

    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile PetrusDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PetrusDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PetrusDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PetrusDatabase.class, "petrus_database")
                            .addCallback(petrusDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static PetrusDatabase.Callback petrusDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                ProductDao dao = INSTANCE.productDao();
                dao.deleteAll();

                Product product = new Product(1,"Steel Toothed Hairbrush","Specially made for grooming long haired animals","photo1",15,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(2,"Circular Comb","Specially made for grooming short haired animals","photo2",37,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(3,"Silicon finger toothbrush","Effective at removing plague","photo3",4,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(4,"360 degrees toothbrush","Comfortable and easy to use","photo4",12,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(5,"Peanut butter treats","Tasty and delicious","photo5",87,"petsupplies@petrus.net");
                dao.insert(product);
            });
        }
    };
}
