package com.example.visitrwanda;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Booking.class}, version = 2, exportSchema = false)
public abstract class BookingDatabase extends RoomDatabase {
    public abstract BookingDao bookingDao();
}
