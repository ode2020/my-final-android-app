package com.example.visitrwanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectedHotActivity extends AppCompatActivity {
    TextView tvHotel;
    Button btnHotelSelect;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_hot);
        btnHotelSelect = findViewById(R.id.hotSelect_bt);

        tvHotel = findViewById(R.id.selecteHot);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnHotelSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectBtn = new Intent(SelectedHotActivity.this, BookHotel.class);
                startActivity(selectBtn);
            }
        });


        Intent intent2 = getIntent();

        if (intent2.getExtras() !=null){
            HotModel hotModel = (HotModel) intent2.getSerializableExtra("data");

            tvHotel.setText(hotModel.getHotelName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbarmenu,menu);
        return true;
    }
}