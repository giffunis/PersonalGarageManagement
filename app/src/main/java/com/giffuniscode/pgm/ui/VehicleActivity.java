package com.giffuniscode.pgm.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.core.services.PgmService;
import com.giffuniscode.pgm.ui.dialogs.DatePickerFragment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class VehicleActivity extends AppCompatActivity {

    public static final String VEHICLE = "vehicle";
    public static final String MESSAGE = "msg"; // Key for the return message
    public static final int ACTIVITY_RESULT_CODE = 1001; // Key to identify this activity on the call activity
    private PgmService pgmService;
    private Vehicle vehicle;
    private Uri picturePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pgmService = new PgmService(this);

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
        String date = DateFormat.getDateInstance().format(vehicle.getFirstRegistration());
        firstRegistration.setText(date);
    }

    public void saveVehicle(View view) {


        EditText licensePlate = findViewById(R.id.ac_vehicle_licensePlate);
        vehicle.setLicensePlate(licensePlate.getText().toString());

        EditText manufacturer = findViewById(R.id.ac_vehicle_manufacturer);
        vehicle.setManufacturer(manufacturer.getText().toString());

        EditText model = findViewById(R.id.ac_vehicle_model);
        vehicle.setModel(model.getText().toString());

        pgmService.Update(vehicle, successListener(), errorListener());
//        EditText firstRegistration = findViewById(R.id.ac_vehicle_date);
//        vehicle.setFirstRegistration(firstRegistration.getText().toString());
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

        pgmService.DeleteVehicle(vehicle, successListener(), errorListener());
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

    private Response.Listener<PgmService.PgmResponse> successListener() {
        return new Response.Listener<PgmService.PgmResponse>() {
            @Override
            public void onResponse(PgmService.PgmResponse response) {
                try {
//                    vehicles = new ArrayList<>();
//                    vehicles.addAll(response.value);
//                    adapter.updateRecycleView(vehicles);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Response.ErrorListener errorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

    public void onClickFirstRegistration(View view) {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(vehicle.getFirstRegistration().getTime());
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONDAY, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                String date = DateFormat.getDateInstance().format(new Date(calendar.getTimeInMillis()));

                EditText firstRegistration = findViewById(R.id.ac_vehicle_date);
                firstRegistration.setText(date);
            }
        });

        // Le pasamos la fecha precargada del vehículo
        Bundle args = new Bundle();
        args.putLong(DatePickerFragment.INITIAL_DATE, vehicle.getFirstRegistration().getTime());
        datePickerFragment.setArguments(args);

        // Mostramos el fragment
        datePickerFragment.show(this.getSupportFragmentManager(), "datePicker");
    }

    public void TakeFoto(View view)
    {
        // Create the camera_intent ACTION_IMAGE_CAPTURE it will open the camera for capture the image
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Start the activity with camera_intent, and request pic id
        activityResultLaunch.launch(intent);
    }

    private final ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    assert result.getData() != null;
                    Bundle extras = result.getData().getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(imageBitmap);
                    vehicle.setPhotoUrl(getFileToByte(imageBitmap));

                }
                else if (result.getResultCode() == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Captura cancelada", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Captura fallida", Toast.LENGTH_LONG).show();
                }
            });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }

    // Put the image file path into this method
    public static String getFileToByte(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }

    public static String getFileToByte(Bitmap bmp){

        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }
}
