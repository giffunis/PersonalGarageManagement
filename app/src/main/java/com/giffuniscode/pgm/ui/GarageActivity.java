package com.giffuniscode.pgm.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.ui.adapters.RvVehiclesAdapter;

import java.util.ArrayList;
import java.util.List;

public class GarageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvVehiclesAdapter adapter;

    private List<Vehicle> vehicles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().set;

        layoutManager = new GridLayoutManager(this, 2);
        vehicles = vehiclesGenerator(); // TODO: Llamar al servicio que devuelve los vehículos del usuario

        adapter = new RvVehiclesAdapter(this, vehicles);
        adapter.setOnItemClickListener(onItemClickListener);

        recyclerView = findViewById(R.id.recycleViewVehicles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true; // Para indicar que está visible.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            //TODO:: Llamar a la activity que almacena la configuración de la app
            return true;
        }
        if (id == R.id.menu_buscar){
            // TODO: Llamar a la función de buscar en la lista
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        vehicles = this.vehiclesRepository.findAll();
        vehicles = vehiclesGenerator(); //TODO: Llamar al servicio que devuelve los vehículos del usuario
        adapter.updateRecycleView(vehicles);
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int pos = recyclerView.getChildAdapterPosition(v);
            Toast.makeText(getApplicationContext(), String.format("Seleccionada la pos %s", pos), Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(v.getContext(), VehicleActivity.class);
//            intent.putExtra(Vehicle.ID, vehicles.get(pos).getId());
//            activityResultLaunch.launch(intent);
        }
    };

    /**
     * Función temporal para generar vehículos
     * @return Listado ficticio de vehículos
     */
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