package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class InfoActivity extends AppCompatActivity {
    CardView hotel;
    CardView madeInRwanda;
    CardView parks;
    CardView beach;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        hotel = findViewById(R.id.info_hotelrw);
        madeInRwanda= findViewById(R.id.info_maderw);
        parks = findViewById(R.id.info_park);
        beach = findViewById(R.id.info_beach);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHotel = new Intent(InfoActivity.this,HotelsActivity.class);
                startActivity(goToHotel);


            }
        });

        parks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPark = new Intent(InfoActivity.this,Parks.class);
                startActivity(goToPark);

            }
        });

        madeInRwanda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMaderw= new Intent(InfoActivity.this,MadeInRwanda.class);
                startActivity(goToMaderw);

            }
        });

        beach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBeach = new Intent(InfoActivity.this,Beach.class);
                startActivity(goToBeach);

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