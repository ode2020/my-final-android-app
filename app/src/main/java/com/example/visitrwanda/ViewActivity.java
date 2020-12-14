package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewActivity extends AppCompatActivity {
     private static RecyclerView.Adapter adapter;
     private RecyclerView.LayoutManager layoutManager;
     private  static RecyclerView recyclerView;
     ArrayList<Booking>bookingArrayList,bookingArrayList_search;
     EditText edt_search;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        recyclerView = (RecyclerView)findViewById(R.id.booking_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        edt_search = (EditText)findViewById(R.id.edt_search);

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = s.toString();
                filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        new LoadDataTask().execute();
    }
    class LoadDataTask extends AsyncTask<Void, Void, Void>
    {
        BookingRepository bookingRepository;
        List<Booking> bookingList;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bookingRepository = new BookingRepository(getApplicationContext());
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bookingList = bookingRepository.getBookings();
            bookingArrayList = new ArrayList<>();
            bookingArrayList_search = new ArrayList<>();


            for (int i=0; i<bookingList.size(); i++)
            {
                bookingArrayList.add(bookingList.get(i));
                bookingArrayList_search.add(bookingList.get(i));
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

             customAdapter = new CustomAdapter(bookingArrayList, ViewActivity.this);
            recyclerView.setAdapter(customAdapter);
        }
    }

    //----Create Filter method----
    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());
        Log.d("filter", charText+"");

        bookingArrayList.clear();

        if (charText.length() ==0)
        {
            bookingArrayList.addAll(bookingArrayList_search);
            Log.d("laod data","All");
        }
        else
        {
            Log.d("load data", "Filtered");
            for (Booking booking:bookingArrayList_search)
            {
                if (booking.client_name.toLowerCase(Locale.getDefault()).contains(charText)
                || String.valueOf(booking.idno).toLowerCase(Locale.getDefault()).contains(charText))
                {
                     bookingArrayList.add(booking);
                }
            }
        }
        customAdapter.notifyDataSetChanged();
    }
    //---end Filter method---

    @Override
    protected void onRestart() {
        super.onRestart();
        new LoadDataTask().execute();
    }
}