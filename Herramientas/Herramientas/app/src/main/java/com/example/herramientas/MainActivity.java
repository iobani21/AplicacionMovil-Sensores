package com.example.herramientas;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public  boolean onOptionsItemSelected(MenuItem menuItem){
     switch (menuItem.getItemId()){
         case R.id.Inf:
             finish();
             break;

         case R.id.Salir:
             finish();
             System.exit(0);
             break;
     }

        return true;
    }



    public void Acelerometro (View view){
        Intent siguiente;
        siguiente = new Intent(this,Acelerometro.class );
        startActivity(siguiente);
    }

    public void Brujula (View view){
        Intent siguiente;
        siguiente = new Intent(this,Brujula.class);
        startActivity(siguiente);
    }

    public void Camara(View view){
        Intent siguiente;
        siguiente = new Intent(this,CameraActivity.class);
        startActivity(siguiente);
    }

    public void gps(View view){
        Intent siguiente;
        siguiente = new Intent(this,GPS.class);
        startActivity(siguiente);
    }

    public void submenu(View view){
        Intent siguiente;
        siguiente = new Intent(this,submenu.class);
        startActivity(siguiente);
    }







}