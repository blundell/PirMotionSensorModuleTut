package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class AndroidThingsActivity extends Activity implements MovementSensor.Listener {

    private PirMovementSensor driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Gpio gpioPin = new PeripheralManagerService().openGpio("BCM18");
            driver = new PirMovementSensor(gpioPin, this);
        } catch (IOException e) {
            throw new IllegalStateException("Can't open GPIO - can't create app.", e);
        }
        driver.startup();
    }

    @Override
    public void onMovement() {
        Log.d("TUT", "MOVEMENT DETECTED");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        driver.shutdown();
    }
}
