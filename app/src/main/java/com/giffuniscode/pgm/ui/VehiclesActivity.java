package com.giffuniscode.pgm.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.ui.adapters.RvVehiclesAdapter;

import java.util.List;

public class VehiclesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvVehiclesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vehicles);
        recyclerView = findViewById(R.id.recycleViewVehicles);
        adapter = new RvVehiclesAdapter(this, new List<Vehicle>());

        recyclerView.setAdapter(adapter);

    }
}