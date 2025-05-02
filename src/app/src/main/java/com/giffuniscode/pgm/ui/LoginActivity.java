package com.giffuniscode.pgm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.infraestructure.data.repositories.UserDbRepository;
import com.giffuniscode.pgm.core.repositories.IUserRepository;
import com.giffuniscode.pgm.core.models.User;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity {

    private String username;
    private String password;
    IUserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        userRepository = new UserDbRepository(this);
    }

    public void goToSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        getDataFromView();
        if(login()){
            Intent intent = new Intent(this, GarageActivity.class);
            startActivity(intent);
        } else {
            Snackbar.make(view, "Usuario y/o contraseña incorrectos", Snackbar.LENGTH_LONG).show();
        }
    }

    private Boolean login(){
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    private void getDataFromView(){
        EditText usernameView = findViewById(R.id.ac_login_txt_input_username);
        username = usernameView.getText().toString();

        EditText passwordView = findViewById(R.id.ac_login_txt_input_password);
        password = passwordView.getText().toString();
    }
}