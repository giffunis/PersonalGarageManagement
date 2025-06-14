package com.giffuniscode.pgm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.core.services.PgmService;
import com.giffuniscode.pgm.ui.adapters.RvVehiclesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class GarageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvVehiclesAdapter adapter;
    private List<Vehicle> vehicles = new ArrayList<>();
    private PgmService pgmService;

    private final ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == VehicleActivity.ACTIVITY_RESULT_CODE) {
                    GetVehiclesFromServer();
                    assert result.getData() != null;
                    String message = result.getData().getStringExtra(VehicleActivity.MESSAGE);
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        layoutManager = new GridLayoutManager(this, 2);
        adapter = new RvVehiclesAdapter(this, vehicles);
        adapter.setOnItemClickListener(onItemClickListener);

        recyclerView = findViewById(R.id.recycleViewVehicles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        //Inicializamos el servicio que hace las peticiones al servidor.
        pgmService = new PgmService(this);

        FloatingActionButton fab = findViewById(R.id.ac_garage_fab_add_vehicle);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), VehicleActivity.class);
            activityResultLaunch.launch(intent);
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true; // Para indicar que está visible.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            settingsLaunch(null);
            return true;
        }
        if (id == R.id.menu_refresh){
            GetVehiclesFromServer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Volvemos a actualizar la lista de vehículos desde el servidor
        GetVehiclesFromServer();
    }

    private final View.OnClickListener onItemClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int pos = recyclerView.getChildAdapterPosition(v);
            Intent intent = new Intent(v.getContext(), VehicleActivity.class);
            intent.putExtra(VehicleActivity.VEHICLE, Vehicle.toJson(vehicles.get(pos)));
            activityResultLaunch.launch(intent);
        }
    };

    /**
     * Obtiene los vehículos del servidor
     **/
    private void GetVehiclesFromServer(){
        pgmService.getVehicles(vehicleListRequestSuccessListener(), errorListener());
    }

    private void settingsLaunch(View view)
    {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private Response.Listener<PgmService.PgmArrayResponse> vehicleListRequestSuccessListener() {
        return response -> {
            try {
                vehicles = new ArrayList<>();
                vehicles.addAll(response.value);
                adapter.updateRecycleView(vehicles);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vehicles = new ArrayList<>();
                adapter.updateRecycleView(vehicles);
                Snackbar.make(requireViewById(R.id.main), "No se pudo descargar los datos", Snackbar.LENGTH_LONG).show();
                error.printStackTrace();
            }
        };
    }
}