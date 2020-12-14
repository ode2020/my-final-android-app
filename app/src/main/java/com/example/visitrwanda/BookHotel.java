package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class BookHotel extends AppCompatActivity {
    Button btn_insert, bnt_view;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_hotel);

       // BookingRepository bookingRepository = new BookingRepository(getApplicationContext());
        btn_insert = (Button)findViewById(R.id.btn_insert);
        bnt_view= (Button)findViewById(R.id.btn_view);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent(BookHotel.this,InsertActivity.class);
                startActivity(is);
            }
        });

        bnt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv = new Intent(BookHotel.this,ViewActivity.class);
                startActivity(iv);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }
}