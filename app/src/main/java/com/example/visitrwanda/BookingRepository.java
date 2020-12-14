package com.example.visitrwanda;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class BookingRepository {
    private static BookingDatabase bookingDatabase;
    private static Context context;
    private String DB_NAME = "bookingdb";


    public BookingRepository(Context context) {
        this.context = context;
        bookingDatabase = Room.databaseBuilder(context, BookingDatabase.class, DB_NAME).build();

        //Toast.makeText(context, "Database created...", Toast.LENGTH_LONG).show();
    }

    //---InsertTask---
    public static void InsertTask(final Booking booking)
    {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                bookingDatabase.bookingDao().insertTask(booking);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, booking.client_name+" has booked successfully", Toast.LENGTH_LONG).show();
            }
        }.execute();
    }
    //---end InsertTask---

    //---Get data task----
    public List<Booking> getBookings() {
        List<Booking>bookingList = bookingDatabase.bookingDao().getAll();
        return bookingList;
    }
    //----end---

    //---Update task----
    public void UpdateTask(final Booking booking)
    {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                bookingDatabase.bookingDao().updateTask(booking);
                return null;

            }
        }.execute();
    }
    //---end of the update task-----


    //---Delete task----
    public void DeleteTask(final Booking booking)
    {
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                bookingDatabase.bookingDao().deleteTask(booking);
                return null;

            }
        }.execute();
    }

    //---End of Delete task----
}
