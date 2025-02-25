package com.giffuniscode.pgm.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;

public class VehicleActivity extends AppCompatActivity {

    public static final String VEHICLE = "vehicle";
    public static final String MESSAGE = "msg"; // Key for the return message
    public static final int ACTIVITY_RESULT_CODE = 1001; // Key to identify this activity on the call activity

    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        vehicle = intent.getExtras() != null ? Vehicle.fromJson(intent.getStringExtra(VEHICLE)) : new Vehicle();
        populateView();
    }

    private void populateView()
    {
        EditText licensePlate = findViewById(R.id.ac_vehicle_licensePlate);
        licensePlate.setText(vehicle.getLicensePlate());

        EditText manufacturer = findViewById(R.id.ac_vehicle_manufacturer);
        manufacturer.setText(vehicle.getManufacturer());

        EditText model = findViewById(R.id.ac_vehicle_model);
        model.setText(vehicle.getModel());

        EditText firstRegistration = findViewById(R.id.ac_vehicle_date);
        firstRegistration.setText(vehicle.getFirstRegistration().toString());
    }

    public void saveVehicle(View view) {
//        EditText username = findViewById(R.id.ac_staff_username);
//        staff.setUsername(username.getText().toString());
//
//        EditText password = findViewById(R.id.ac_staff_password);
//        staff.setPassword(password.getText().toString());
//
//        EditText telephone = findViewById(R.id.ac_staff_telephone);
//        staff.setPhoneNumber(telephone.getText().toString());
//
//        long rows = staff.getId() != 0 ? staffRepository.update(staff) : staffRepository.add(staff);
//
//        Intent intent = new Intent();
//        if(staff.getId() != 0 && rows != 0){
//            intent.putExtra(MESSAGE, "Actualizado");
//        } else if(staff.getId() == 0 && rows != 0) {
//            intent.putExtra(MESSAGE, "Guardado");
//        } else{
//            intent.putExtra(MESSAGE, "Error al guardar");
//        }
//
//        returnToParentActivity(intent);
    }

    public void deleteVehicle(View view){
//        Intent intent = new Intent();
//
//        if(staffRepository.delete(staff) != 0){
//            intent.putExtra(MESSAGE, "Eliminado");
//        } else {
//            intent.putExtra(MESSAGE, "Error al borrar");
//        }
//
//        returnToParentActivity(intent);
    }

}