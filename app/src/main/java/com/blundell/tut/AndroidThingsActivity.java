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

        Gpio bus = openMotionSensorGpioBus();
        motionSensor = new PirMotionSensor(bus, this);
        motionSensor.startup();
    }

    private Gpio openMotionSensorGpioBus() {
        Gpio bus;
        try {
            // BCM18 is the GPIO pin I have the sensor connected to on my raspberry pi
            bus = new PeripheralManagerService().openGpio("BCM18");
        } catch (IOException e) {
            throw new IllegalStateException("Can't open GPIO - can't create app.", e);
        }
        return bus;
    }

    @Override
    public void onMovement() {
        Log.d("TUT", "MOVEMENT DETECTED");
    }

    @Override
    protected void onDestroy() {
        motionSensor.shutdown();
        super.onDestroy();
    }
}
