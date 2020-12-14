package com.example.visitrwanda;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookingDao {

    @Insert
    Long insertTask(Booking booking);

    @Update
    void updateTask(Booking booking);

    @Delete
    void deleteTask(Booking booking);
    @Query("select * from booking order by idno asc")
    List<Booking> getAll();
}
