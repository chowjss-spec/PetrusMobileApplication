package com.example.petrusapplication.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class, Service.class}, version = 1, exportSchema = false)
abstract class PetrusDatabase extends RoomDatabase {
    abstract ProductDao productDao();
    abstract ServiceDao serviceDao();

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

                Product product = new Product(1,"Steel Toothed Hairbrush","Specially made for grooming long haired animals","Photo1",15,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(2,"Circular Comb","Specially made for grooming short haired animals","Photo2",37,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(3,"Silicon finger toothbrush","Effective at removing plague","Photo3",4,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(4,"360 degrees toothbrush","Comfortable and easy to use","Photo4",12,"petsupplies@petrus.net");
                dao.insert(product);
                product = new Product(5,"Peanut butter treats","Tasty and delicious","Photo5",87,"petsupplies@petrus.net");
                dao.insert(product);

                ServiceDao serviceDao = INSTANCE.serviceDao();
                serviceDao.deleteAll();
                Service service = new Service(1,"Pet Sitting","Great if you want your pet to stay at a pet sitter's home!",50,"service1","vpummery0@myspace.com","5/2/2021","Sitting","Mixed","Buona Vista");
                serviceDao.insert(service);
                service = new Service(1,"Pet Sitting","Great if you want your pet to stay at a pet sitter's home!",50,"service1","vpummery0@myspace.com","5/2/2021","Sitting","Mixed","Buona Vista");
                serviceDao.insert(service);
                service = new Service(2,"House Sitting","Perfect if you want a pet sitter to stay in your home!",100,"service2","bkernock1@usda.gov","7/23/2021","Sitting","Mixed","Redhill");
                serviceDao.insert(service);
                service = new Service(3,"Home Visits","Short drop-in visits for play dates and pet feeding",50,"service3","wrisbie2@ask.com","8/29/2020","Visits","Mixed","Ang Mo Kio");
                serviceDao.insert(service);
                service = new Service(4,"Day Care","Choose to drop off your pet to stay with a pet sitter during the day!",50,"service4","cmelody3@bravesites.com","12/11/2020","Care","Mixed","Clementi");
                serviceDao.insert(service);
                service = new Service(5,"Pet Taxi","Pick up and drop off for appointments or pet stays ",80,"service5","vhablot4@mozilla.org","6/19/2021","Taxi","Mixed","Clementi");
                serviceDao.insert(service);
                service = new Service(6,"Daily Dog Walking","Fun and active walks for your dog!",30,"service6","hgaines5@elpais.com","3/27/2021","Walking","Dog","Queenstown");
                serviceDao.insert(service);
                service = new Service(7,"Dog Training","4 weeks 1 hour daily course with positive reinforcement trainers ",80,"service7","callcott6@g.co","6/1/2021","Training","Dog","Bukit Batok");
                serviceDao.insert(service);
                service = new Service(8,"Dog Washing","Deflead and smelling lovely!",30,"service8","scardew7@qq.com","7/14/2021","Washing","Dog","Buona Vista");
                serviceDao.insert(service);
                service = new Service(9,"24hr Supervision ","Care for pets with special needs",50,"service9","lhadfield8@shinystat.com","6/15/2021","Care","Mixed","Clementi");
                serviceDao.insert(service);
                service = new Service(10,"Grooming and Clipping","For basic grooming and tidy ups",40,"service10","nrought9@goo.ne.jp","11/4/2020","Grooming","Mixed","Ang Mo Kio");
                serviceDao.insert(service);
                service = new Service(11,"Boarding","Great for overnight care at the sitter's home",50,"service11","belcouxa@dailymotion.com","6/24/2021","Boarding","Mixed","Bradell");
                serviceDao.insert(service);
                service = new Service(12,"Pet Photography","Capture moments of your pet in action!",150,"service12","hyankishinb@furl.net","10/17/2020","Photography","Mixed","Tanjong Pagar");
                serviceDao.insert(service);
                service = new Service(13,"Dog Boarding","Perfect for overnight care at provider's home!",50,"service13","jdimmickm@instagram.com","11/23/2020","Boarding","Dog","Tanjong Pagar");
                serviceDao.insert(service);
                service = new Service(14,"Puppy Sitting","Need a helping hand to look after your puppy while you are away? Look no further!",50,"service14","pharmen@issuu.com","8/23/2020","Sitting","Dog","Bugis");
                serviceDao.insert(service);
                service = new Service(15,"Rabbit Grooming","Body check, nail clipping,  shaping and trimming of fur and many more!",45,"service15","eboyeto@newyorker.com","3/3/2021","Grooming","Rabbit","Bugis");
                serviceDao.insert(service);
            });
        }
    };
}
