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

import com.android.volley.VolleyError;
import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.core.models.Response;
import com.giffuniscode.pgm.core.services.PgmService;
import com.giffuniscode.pgm.ui.adapters.RvVehiclesAdapter;

import java.util.ArrayList;
import java.util.List;

public class GarageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RvVehiclesAdapter adapter;

    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutManager = new GridLayoutManager(this, 2);
        adapter = new RvVehiclesAdapter(this, vehicles);
        adapter.setOnItemClickListener(onItemClickListener);

        recyclerView = findViewById(R.id.recycleViewVehicles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        // Llamamos al servicio que nos devuelve los vehículos almacenados en el servidor.
        GetVehiclesFromServer();
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
        GetVehiclesFromServer(); // Volvemos a actualizar la lista de vehículos desde el servidor
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
     * Obtiene los vehículos del servidor
     * @return Listado ficticio de vehículos
     */
    private void GetVehiclesFromServer(){
        PgmService.GetVehicles(this, vehicleListRequestSuccessListener(), errorListener());
    }

    private com.android.volley.Response.Listener<Response> vehicleListRequestSuccessListener() {
        return new com.android.volley.Response.Listener<Response>() {
            @Override
            public void onResponse(Response response) {
                try {
                    vehicles = new ArrayList<>();
                    vehicles.addAll(response.value);
                    adapter.updateRecycleView(vehicles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private com.android.volley.Response.ErrorListener errorListener() {
        return new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }
}