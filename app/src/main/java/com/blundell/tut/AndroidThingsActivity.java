package com.blundell.tut;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManagerService;

import java.io.IOException;

public class AndroidThingsActivity extends Activity implements MotionSensor.Listener {

    private PirMotionSensor motionSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Gpio bus = new PeripheralManagerService().openGpio("BCM18");
            motionSensor = new PirMotionSensor(bus, this);
        } catch (IOException e) {
            throw new IllegalStateException("Can't open GPIO - can't create app.", e);
        }
        motionSensor.startup();
    }

    @Override
    public void onMovement() {
        Log.d("TUT", "MOVEMENT DETECTED");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        motionSensor.shutdown();
    }
}
