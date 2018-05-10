package com.bignerdranch.android.airplane.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;




@Entity(tableName = "Flight")
public class Flight {


    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "flightNumber")
    public String flightNumber;

    @ColumnInfo(name = "departure")
    public String departure;

    @ColumnInfo(name = "arrival")
    public String arrival;

    @ColumnInfo(name = "flightCapacity")
    public int flightCapacity;

    @ColumnInfo(name = "departureTime")
    public String departureTime;

    @ColumnInfo(name = "price")
    public double price;

    public Flight(String flightNumber, String departure, String arrival,
                  String departureTime, int flightCapacity,double price) {

        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.departureTime = departureTime;
        this.flightCapacity = flightCapacity;
        this.price = price;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return mId;
    }

}