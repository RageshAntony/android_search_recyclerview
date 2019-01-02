package com.example.admin.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Model> models;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    public SearchView searchView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (recyclerViewAdapter != null) {
                    recyclerViewAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        models = new ArrayList<>();
        models.add(new Model("rajesh"));
        models.add(new Model("deva"));
        models.add(new Model("merlin"));
        models.add(new Model("antony"));
        models.add(new Model("giri"));
        models.add(new Model("guru"));
        System.out.println("get the position " + models.get(0).getName());
        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(), models);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}
