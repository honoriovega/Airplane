package com.bignerdranch.android.airplane.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "User")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int mId;

    @ColumnInfo(name = "username")
    public String mUsername;

    @ColumnInfo(name = "password")
    public String mPassword;

    public User(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public int getmId() {
        return mId;
    }


    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}