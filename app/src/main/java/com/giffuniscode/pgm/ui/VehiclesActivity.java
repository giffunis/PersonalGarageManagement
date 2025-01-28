package com.giffuniscode.pgm.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.ui.adapters.RvVehiclesAdapter;

import java.util.ArrayList;
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
        adapter = new RvVehiclesAdapter(this, vehiclesGenerator());
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    private List<Vehicle> vehiclesGenerator(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("0123 BCD", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/kia.png"));
        vehicles.add(new Vehicle("9999 ZZZ", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/mitsubishi.png"));
//        vehicles.add(new Vehicle("0000 KKK", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/nissan.png"));

        return vehicles;
    }
}