package com.giffuniscode.pgm.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Response;
import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.pgm.core.models.Vehicle;
import com.giffuniscode.pgm.core.services.PgmService;
import com.giffuniscode.pgm.ui.dialogs.DatePickerFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class VehicleActivity extends AppCompatActivity {

    public static final String VEHICLE = "vehicle";
    public static final String MESSAGE = "msg"; // Key for the return message
    public static final int ACTIVITY_RESULT_CODE = 1001; // Key to identify this activity on the call activity
    private PgmService pgmService;
    private Vehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.ac_vehicle_fab_take_photo);
        fab.setOnClickListener(this::takeFoto);

        pgmService = new PgmService(this);

        Intent intent = getIntent();
        vehicle = intent.getExtras() != null ? Vehicle.fromJson(intent.getStringExtra(VEHICLE)) : new Vehicle();
        populateView();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_vehicle, menu);
        return true; // Para indicar que está visible.
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_sharring){
            sharingLaunch(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sharingLaunch(View v){

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");

        // (Optional) Here you're setting the title of the content
        sendIntent.putExtra(Intent.EXTRA_TITLE, "Datos del vehículo " + vehicle.getLicensePlate());
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Datos del vehículo " + vehicle.getLicensePlate());

        sendIntent.putExtra(Intent.EXTRA_TEXT, "Aquí tienes los datos de mi vehículo: " + pgmService.getShareLink(vehicle));
        sendIntent.putExtra(Intent.EXTRA_EMAIL, "Aquí tienes los datos de mi vehículo: " + pgmService.getShareLink(vehicle));

        // Show the Sharesheet
        startActivity(Intent.createChooser(sendIntent, null));
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

        if(vehicle.getPhotoUrl() != null && !vehicle.getPhotoUrl().isEmpty()){
            ImageView imageView = findViewById(R.id.ac_vehicle_picture);
            imageView.setImageBitmap(getImageFromByte(vehicle.getPhotoUrl()));
        }

    }

    private void returnToParentActivity(Intent intent) {
        setResult(ACTIVITY_RESULT_CODE, intent);
        finish();
    }

    public void saveVehicle(View view) {

        EditText licensePlate = findViewById(R.id.ac_vehicle_licensePlate);
        vehicle.setLicensePlate(licensePlate.getText().toString());

        EditText manufacturer = findViewById(R.id.ac_vehicle_manufacturer);
        vehicle.setManufacturer(manufacturer.getText().toString());

        EditText model = findViewById(R.id.ac_vehicle_model);
        vehicle.setModel(model.getText().toString());

        // vehicle.firstRegistrationDate ya está actualizado por el evento
        // vehicle.photoUrl ya está actualizado por el evento

        boolean updateOperation = vehicle.getId() != 0;

        if(updateOperation) {
            pgmService.updateVehicle(vehicle, successUpdateListener(), errorListener());
        } else {
            pgmService.addVehicle(vehicle, successUpdateListener(), errorListener());
        }
    }

    public void deleteVehicle(View view){
        pgmService.deleteVehicle(vehicle, successDeleteListener(), errorListener());
    }

    private Response.Listener<PgmService.PgmBoolResponse> successDeleteListener() {
        return response -> {
            Intent intent = new Intent();
            try {
                if(response.errors.isEmpty() && response.value){
                    intent.putExtra(MESSAGE, "Eliminado");
                } else {
                    intent.putExtra(MESSAGE, "Error al borrar");
                }
            } catch (Exception e) {
                e.printStackTrace();
                intent.putExtra(MESSAGE, "Error al borrar");
            } finally {
                returnToParentActivity(intent);
            }
        };
    }

    private Response.Listener<PgmService.PgmResponse> successUpdateListener() {
        return response -> {
            Intent intent = new Intent();
            try {
                if(response.errors.isEmpty()){
                    intent.putExtra(MESSAGE, "Guardado");
                } else {
                    intent.putExtra(MESSAGE, "Error al guardar");
                }
            } catch (Exception e) {
                e.printStackTrace();
                intent.putExtra(MESSAGE, "Error al guardar");
            } finally {
                returnToParentActivity(intent);
            }
        };
    }

    private Response.ErrorListener errorListener() {
        return error -> error.printStackTrace();
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

                // Actualizamos el dto
                vehicle.setFirstRegistration(calendar.getTime());

                // Actualizamos la vista.
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

    public void takeFoto(View view)
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

                    // Actualizamos el dto
                    vehicle.setPhotoUrl(getByteFromImage(imageBitmap));

                    // Actualizamos la vista.
                    ImageView imageView = findViewById(R.id.ac_vehicle_picture);
                    imageView.setImageBitmap(imageBitmap);
                }
                else if (result.getResultCode() == RESULT_CANCELED) {
                    Toast.makeText(getApplicationContext(), "Captura cancelada", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Captura fallida", Toast.LENGTH_LONG).show();
                }
            });

    public static String getByteFromImage(Bitmap bmp){

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

    public static Bitmap getImageFromByte(String encodeString){

        Bitmap decodedByte = null;

        try{
            byte[] decodedString = Base64.decode(encodeString, Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return decodedByte;
    }
}
