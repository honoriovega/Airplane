package com.bignerdranch.android.airplane.Database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bignerdranch.android.airplane.Models.User;

import java.util.List;



@Dao
public interface UserDao {

    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM User WHERE username = :username")
    public User getUser(String username);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    public User getUser(String username, String password);

    @Query("SELECT * FROM User")
    public List<User> getAllUsers();

    @Update
    public void updateUser(User libraryUser);

    @Delete
    public void deleteUser(User libraryUser);

}
