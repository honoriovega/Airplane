package com.bignerdranch.android.airplane.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.bignerdranch.android.airplane.Models.Flight;

import java.util.List;



@Dao
public interface FlightDao {

    @Insert
    public void addFlight(Flight flight);

    @Query("SELECT * FROM Flight WHERE flightNumber = :flightNumber")
    public Flight getFlight(String flightNumber);

    @Query("SELECT * FROM Flight WHERE arrival = :arrival AND departure =:departure AND flightCapacity >= :flightCapacity")
    public List<Flight> getFlights(String arrival, String departure, int flightCapacity);

    @Query("SELECT * FROM Flight")
    public List<Flight> getAllFlights();

    @Update
    public void updateUser(Flight flight);

    @Delete
    public void deleteUser(Flight flight);

}
