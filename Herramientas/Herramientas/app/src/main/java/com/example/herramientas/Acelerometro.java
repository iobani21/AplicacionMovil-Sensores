package com.example.herramientas;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {

    private long last_update = 0, last_movement = 0;
    private float prevX = 0, prevY = 0, prevZ = 0;
    private float curX = 0, curY = 0, curZ = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acelerometro);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            long current_time = event.timestamp;

            curX = event.values[0];
            curY = event.values[1];
            curZ = event.values[2];

            if (prevX == 0 && prevY == 0 && prevZ == 0) {
                last_update = current_time;
                last_movement = current_time;
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
            }

            long time_difference = current_time - last_update;
            if (time_difference > 0) {
                float movement = Math.abs((curX + curY + curZ) - (prevX - prevY - prevZ)) / time_difference;
                int limit = 1500;
                float min_movement = 1E-6f;

                last_movement = current_time;
            }
            prevX = curX;
            prevY = curY;
            prevZ = curZ;
            last_update = current_time;
        }


        ((TextView) findViewById(R.id.txtAX)).setText("--> " + curX);
        ((TextView) findViewById(R.id.txtAY)).setText("--> " + curY);
        ((TextView) findViewById(R.id.txtAZ)).setText("--> " + curZ);

    }

    @Override
    protected void onResume() {


        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);


        if(sensors == null){
            Toast.makeText(getApplicationContext(), "No hay Sensor Acelerometro", Toast.LENGTH_SHORT).show();

        }
        else{

            Toast.makeText(getApplicationContext(), "Hay Sensor de Acelerometro", Toast.LENGTH_SHORT).show();
            if (sensors.size() > 0) {
                sm.registerListener(this, sensors.get(0), SensorManager.SENSOR_DELAY_GAME);
            }
        }



    }
    @Override
    protected void onStop() {
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
        super.onStop();
    }

}
