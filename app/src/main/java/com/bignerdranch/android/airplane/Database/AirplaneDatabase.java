package com.bignerdranch.android.airplane.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.bignerdranch.android.airplane.Models.Flight;
import com.bignerdranch.android.airplane.Models.User;


@Database(entities = {User.class,Flight.class}, version = 1, exportSchema = false)
public abstract class AirplaneDatabase extends RoomDatabase {
    private static AirplaneDatabase INSTANCE;


    public abstract UserDao userDao();
    public abstract FlightDao flightDao();



    private static final Object sLock = new Object();
    public static AirplaneDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AirplaneDatabase.class, "airplane.db").
                        allowMainThreadQueries()
                        .build();

                // add default users
                INSTANCE.userDao().addUser(new User("alice5","csumb100"));
                INSTANCE.userDao().addUser(new User("brian77","123ABC"));
                INSTANCE.userDao().addUser(new User("chris21","CHRIS21"));

                // add default flights
                INSTANCE.flightDao().addFlight(new Flight("Otter101",
                        "Monterey",	"Los Angeles",	"10:00(AM)",
                        10,150.00));


                INSTANCE.flightDao().addFlight(new Flight("Otter102",
                                "Los Angeles","Monterey",
                                "1:00(PM)",10,150.00));


                INSTANCE.flightDao().addFlight(new Flight("Otter201",
                        "Monterey","Seattle","11:00(AM)",
                        5,200.50));

                INSTANCE.flightDao().addFlight(new Flight("Otter205",
                        "Monterey","Seattle",	"3:00(PM)",
                        15,150.00));

                INSTANCE.flightDao().addFlight(new Flight("Otter202",
                        "Seattle",	"Monterey",	"2:00(PM)",
                        5,200.50));




            }
            return INSTANCE;
        }
    }
}