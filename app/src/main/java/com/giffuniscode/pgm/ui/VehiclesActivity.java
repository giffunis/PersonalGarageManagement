package com.giffuniscode.pgm.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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

    @Override
    protected void onResume() {
        super.onResume();
//        vehicles = this.vehiclesRepository.findAll();
        adapter.updateRecycleView(vehiclesGenerator());
    }

    private List<Vehicle> vehiclesGenerator(){
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("0123 BCD", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/kia.png"));
        vehicles.add(new Vehicle("9999 ZZZ", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/mitsubishi.png"));
        vehicles.add(new Vehicle("0000 KKK", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/nissan.png"));
        vehicles.add(new Vehicle("2222 BCD", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/opel.png"));
        vehicles.add(new Vehicle("3333 ZZZ", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/renault.png"));
        vehicles.add(new Vehicle("4444 KKK", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/peugeot.png"));
        vehicles.add(new Vehicle("5555 BCD", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/hyundai.png"));
        vehicles.add(new Vehicle("6666 ZZZ", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/citroen.png"));
        vehicles.add(new Vehicle("7777 KKK", "https://raw.githubusercontent.com/giffunis/car-logos-dataset/master/logos/optimized/toyota.png"));

        return vehicles;
    }
}