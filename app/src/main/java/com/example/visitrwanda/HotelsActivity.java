package com.example.visitrwanda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class HotelsActivity extends AppCompatActivity implements HotAdapter.SelectedHotel{
    Toolbar toolbar;
    RecyclerView recyclerView;

    List<HotModel>hotModelList = new ArrayList<>();

    String[] names ={"Radison Blue", "Marriot", "Kinigi", "Bisate", "Nyungwe House", "Serena", "Avila", "Morial"};

    HotAdapter hotAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        recyclerView = findViewById(R.id.recyclerHotel);
        toolbar = findViewById(R.id.toolbarHotel);

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        for (String s:names){
            HotModel hotModel = new HotModel(s);

            hotModelList.add(hotModel);
        }
        hotAdapter = new HotAdapter(hotModelList, this);
        recyclerView.setAdapter(hotAdapter);
    }

    @Override
    public void selectedHotel(HotModel hotModel) {
        startActivity(new Intent(HotelsActivity.this, SelectedHotActivity.class).putExtra("data",hotModel));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem menuItem = menu.findItem(R.id.search_view);

        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search_view){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}